package com.woori.member.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.woori.black.dao.BlackDAO;
import com.woori.member.dao.ListDAO;
import com.woori.member.dao.MemberDAO;
import com.woori.member.dto.CustomerDTO;
import com.woori.member.dto.CustomerListDTO;
import com.woori.member.dto.SellerDTO;
import com.woori.member.dto.SellerListDTO;
import com.woori.report.dao.AdminReportDAO;
import com.woori.report.dto.ReportDTO;
import com.woori.wish.dao.WishDAO;

public class MemberService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;
	String page = "";
	String msg = "";
	MemberDAO dao = null;

	public MemberService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	//구매자 회원가입
	public void cjoin() throws ServletException, IOException {
		String name = req.getParameter("name");
		String cid = req.getParameter("id");
		String pw = req.getParameter("pw");
		String Birthday = req.getParameter("birth");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String Phone = req.getParameter("phone");
		System.out.println(cid+"/"+pw+"/"+name+"/"+Birthday+"/"+gender+"/"+email+"/"+Phone);
		
		CustomerDTO dto = new CustomerDTO();
		dao = new MemberDAO();
		dto.setCid(cid);
		dto.setPw(pw);
		dto.setName(name);
		dto.setBirthday(Birthday);
		dto.setGender(gender);
		dto.setEmail(email);
		dto.setPhone(Phone);
		//실패했을 때
		msg="회원가입 실패";
		page="./C_regist.jsp";
		//성공했을때
		if(dao.cjoin(dto)>0) {
			boolean success = true;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cJoinSuccess", success);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			System.out.println(json);
			resp.getWriter().print(json);
		}
	}
	//판매자 회원가입
	public void sjoin() throws ServletException, IOException {
		String sid = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String Store_call = req.getParameter("store_call");
		String Birthday = req.getParameter("birth");
		String email = req.getParameter("email");
		String Phone = req.getParameter("phone");
		
		System.out.println(sid+"/"+pw+"/"+name+"/"+gender+"/"+Store_call+"/"+Birthday+"/"+email+"/"+Phone);
		
		SellerDTO dto = new SellerDTO();
		dao=new MemberDAO();
		dto.setSid(sid);
		dto.setPw(pw);
		dto.setName(name);
		dto.setGender(gender);
		dto.setStore_call(Store_call);
		dto.setBirthday(Birthday);
		dto.setEmail(email);
		dto.setPhone(Phone);
		
		//실패했을 때
		msg="회원가입 실패";
		
		//성공했을때
		if(dao.sjoin(dto)>0) {
			boolean success = true;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sJoinSuccess", success);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			System.out.println(json);
			resp.getWriter().print(json);
		}
		
	}

	public void overlay() throws IOException {// 중복체크
		String cid = req.getParameter("cid");
		boolean success = false;
		System.out.println("Cid :" + cid);
		MemberDAO dao = new MemberDAO();
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			success = dao.overlay(cid);
			System.out.println("아이디 사용여부 :" + success);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			dao.resClose();
			map.put("use", success);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			System.out.println(json);

			resp.getWriter().print(json);
		}
	}
	public void soverlay() throws IOException {// 중복체크
		String sid = req.getParameter("sid");
		boolean success = false;
		System.out.println("sid :" + sid);
		MemberDAO dao = new MemberDAO();
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			success = dao.soverlay(sid);
			System.out.println("아이디 사용여부 :" + success);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			dao.resClose();
			map.put("use", success);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			System.out.println(json);

			resp.getWriter().print(json);
		}
	}
	 
	/*public boolean C_Loginid(String cid, String pw) {
		 MemberDAO dao = MemberDAO.clogin(cid, pw);
	 if(dao == null)
		 return false;
	 else {
		 if(dao.pw().equals(pw))
			 return true;
		 else
			 return false;
	 }
	 }
	 */

	public void C_Loginid() throws ServletException, IOException {//구매자 로그인
		MemberDAO dao = new MemberDAO();
		String cid = req.getParameter("cid");
		String pw = req.getParameter("pw");
		System.out.println(cid + "/" + pw);
		
		page = "../Consumer/C_login.jsp";
		msg = "아이디 비밀번호를 다시 확인해 주세요!";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = dao.clogin(cid, pw);
		boolean success = (boolean)map.get("success");
		boolean isblack = (boolean)map.get("isblack");
		System.out.println("로그인 성공여부 :"+success+"/블락여부:"+isblack);
		
		if(!isblack) {//블락이 아니고 성공하면
			if (success) {
				page = "/C_main";
				msg = cid + " 님 반갑 습니다.";
				req.getSession().setAttribute("loginID", cid);
			}
		} else if(isblack) {//블락이면
			msg = "블랙리스트에 등록된 아이디입니다.";
		}
		
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

	public void S_Loginid() throws ServletException, IOException {//판매자 로그인
		MemberDAO dao = new MemberDAO();
		String sid = req.getParameter("sid");
		String pw = req.getParameter("pw");
		System.out.println(sid + "/" + pw);
		
		page = "../Consumer/C_login.jsp";
		msg = "아이디 비밀번호를 다시 확인해 주세요!";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = dao.slogin(sid, pw);
		boolean success = (boolean)map.get("success");
		boolean isblack = (boolean)map.get("isblack");
		System.out.println("로그인 성공여부 :"+success+"/블락여부:"+isblack);
		
		if(!isblack) {//블락이 아니고 성공하면
			if (success) {
				page = "/Seller/sItemList";
				msg = sid + " 님 반갑 습니다.";
				req.getSession().setAttribute("loginID", sid);
			}
		} else if(isblack) {//블락이면
			msg = "블랙리스트에 등록된 아이디입니다.";
		}
		
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);

	}

	public void cList() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			int group = 1;
			String page = req.getParameter("page");
			
			if (page != null) {
				group = Integer.parseInt(page);
			}
			ListDAO dao = new ListDAO();
			HashMap<String, Object> map = dao.cList(group);
			
			req.setAttribute("cList", map.get("cList"));
			req.setAttribute("maxCustomerPage", map.get("maxCustomerPage"));
			req.setAttribute("currPage", group);
			RequestDispatcher dis = req.getRequestDispatcher("admin_CustomerList.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}

	public void sList() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			int group = 1;
			String page = req.getParameter("page");
			
			if (page != null) {
				group = Integer.parseInt(page);
			}
			
			ListDAO dao = new ListDAO();
			HashMap<String, Object> map = dao.sList(group);
			
			req.setAttribute("sList", map.get("sList"));
			req.setAttribute("currPage", group);
			req.setAttribute("maxSellerPage", map.get("maxSellerPage"));
			RequestDispatcher dis = req.getRequestDispatcher("admin_SellerList.jsp");
			dis.forward(req, resp);	
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}

	public void cSearch() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			String inputC = req.getParameter("cSearch");
			System.out.println("검색할 구매자 : " + inputC);
			
			ListDAO dao = new ListDAO();
			ArrayList<CustomerListDTO> searchedC = dao.cSearch(inputC);
			
			req.setAttribute("cList", searchedC);
			req.setAttribute("inputC", inputC);
			RequestDispatcher dis = req.getRequestDispatcher("admin_CustomerList.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}

	public void sSearch() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			String inputS = req.getParameter("sSearch");
			System.out.println("검색할 판매자 : " + inputS);
			
			ListDAO dao = new ListDAO();
			ArrayList<SellerListDTO> searchedS = dao.sSearch(inputS);
			
			req.setAttribute("sList", searchedS);
			req.setAttribute("inputS", inputS);
			RequestDispatcher dis = req.getRequestDispatcher("admin_SellerList.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}

	public void sPfpDetail() throws ServletException, IOException { // 판매자메인 - 판매자 회원정보 상세보기
		// 로그인검사 추가됨.
		//req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
		  
			System.out.println(sid+"의 회원정보"); // 로그인한 아이디 확인
			MemberDAO dao = new MemberDAO();
			SellerDTO dto = dao.sPfpDetail(sid);
			System.out.println(dto);// 해당판매자정보
			req.setAttribute("detail", dto);
			RequestDispatcher dis = req.getRequestDispatcher("./S_profile.jsp");
			dis.forward(req, resp);
			
		} else { //로그인을 안 했으면 로그인페이지로 
			  resp.sendRedirect("../Consumer/C_login.jsp"); 
		}
	}

	public void sPfpUpdateForm() throws ServletException, IOException {// 판매자메인 - 판매자 수정폼 보기
		// 로그인검사 추가됨.
		//req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
		  
			System.out.println("수정할 sid :" + sid);
	
			MemberDAO dao = new MemberDAO();
			SellerDTO dto = dao.sPfpDetail(sid);
			req.setAttribute("detail", dto);
			RequestDispatcher dis = req.getRequestDispatcher("./S_profileRevise.jsp");
			dis.forward(req, resp);
		
		} else { //로그인을 안 했으면 로그인페이지로 
			  resp.sendRedirect("../Consumer/C_login.jsp"); 
		}
	}

	public void sPfpUpdate() throws ServletException, IOException {// 판매자메인 - 판매자 회원정보 수정
		// 로그인검사 추가됨.
		//req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String loginID = (String) req.getSession().getAttribute("loginID");
		if(loginID != null) {//로그인 여부 판별
			
			String sid = req.getParameter("sid");
			String name = req.getParameter("name");
			String pw = req.getParameter("pw");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String storeCall = req.getParameter("storeCall");
			System.out.println("변경정보:" + sid + "/" + name + "/" + pw + "/" + email + "/" + phone + "/" + storeCall);
	
			SellerDTO dto = new SellerDTO();
			dto.setSid(sid);
			dto.setName(name);
			dto.setPw(pw);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setStore_call(storeCall);
			
			boolean success = false;
			MemberDAO dao = new MemberDAO();
			if(dao.sPfpUpdate(dto)>0) {
				success = true;
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("update", success);
				Gson gson = new Gson();
				String json = gson.toJson(map);
				System.out.println(json);
				resp.getWriter().print(json);
				//resp.sendRedirect("sPfpDetail?sid=" + dto.getSid());
			};
			
		}else { //로그인을 안 했으면 로그인페이지로 
			  resp.sendRedirect("../Consumer/C_login.jsp"); 
		}
	}

	public void cDetail() throws ServletException, IOException {
		//req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			System.out.println("상세보기할 cid : " + loginID);

			MemberDAO dao = new MemberDAO();
			CustomerDTO dto = new CustomerDTO();
			dto = dao.cDetail(loginID);
			req.setAttribute("list", dto);
			RequestDispatcher dis = req.getRequestDispatcher("./C_MyInfo.jsp");
			dis.forward(req, resp);

		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("../Consumer/C_login.jsp");
			dis.forward(req, resp);
		}
	}

	public void cUpdateForm() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			String cid = req.getParameter("cid");
			String pw = req.getParameter("pw");
			System.out.println("cid / pw : " + cid + " / " + pw);

			MemberDAO dao = new MemberDAO();
			CustomerDTO dto = dao.cUpadateForm(cid, pw);

			String page = "cDetail";
			if (dto != null) {
				page = "./C_MyInfoModify.jsp";
				req.setAttribute("list", dto);
			}
			RequestDispatcher dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);

		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("../Consumer/C_login.jsp");
			dis.forward(req, resp);
		}
	}

	public void cUpdateInfo() throws ServletException, IOException {
		//req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			String cid = req.getParameter("cid");
			String pw = req.getParameter("pw");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			System.out.println(cid + " / " + pw + " / " + email + " / " + phone);
			CustomerDTO dto = new CustomerDTO();
			dto.setCid(cid);
			dto.setPw(pw);
			dto.setEmail(email);
			dto.setPhone(phone);

			MemberDAO dao = new MemberDAO();
			int success = 0;
			success = dao.cUpdateInfo(dto);

			String page = "cUpadateForm";
			if (success > 0) {
				page = "cDetail";
			}
			RequestDispatcher dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("../Consumer/C_login.jsp");
			dis.forward(req, resp);
		}
	}

	public void AdminCustomerDetail() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			HashMap<String, Object> map = new HashMap<>();
			int group = 1;
			String page = req.getParameter("page");
			
			if (page != null) {
				group = Integer.parseInt(page);
			}
			// 1. 상세보기할 cid 받기
			String cid = req.getParameter("id");
			
			// 2. Consumer 테이블에서 C 정보 받아오기
			MemberDAO mDao = new MemberDAO();
			CustomerDTO dto = mDao.getCustomer(cid);
			map.put("Admin_selectedCData", dto);
			
			// 3. C_BLACKLIST 테이블에서 isblack 받아오기
			BlackDAO bDao = new BlackDAO();
			int isBlack = bDao.getCBlack(cid);
			map.put("Admin_selectedCIsBlack", isBlack);
			
			// 4. REPORT 테이블에서 해당 cid가 target_id인 신고정보 받아오기
			AdminReportDAO rDao = new AdminReportDAO();
			ArrayList<ReportDTO> selectedCustomerRList = rDao.getRList(cid, group);
			rDao = new AdminReportDAO();
			int maxRPage = rDao.getMaxSelectedRPage(cid);
			map.put("Admin_selectedCRListCurrPage", group);
			map.put("Admin_maxRPage", maxRPage);
			map.put("Admin_selectedCRList", selectedCustomerRList);
			
			// 5. admin_CustomerDetail.jsp 로 포워딩
			req.setAttribute("Admin_CDetailData", map);
			RequestDispatcher dis = req.getRequestDispatcher("admin_CustomerDetail.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}

	public void AdminSellerDetail() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			HashMap<String, Object> map = new HashMap<>();
			int group = 1;
			String page = req.getParameter("page");
			
			if (page != null) {
				group = Integer.parseInt(page);
			}
			// 1. 상세보기할 sid 받기
			String sid = req.getParameter("id");
			
			// 2. Seller 테이블에서 C 정보 받아오기
			MemberDAO mDao = new MemberDAO();
			SellerDTO dto = mDao.getSeller(sid);
			map.put("Admin_selectedSData", dto);
			
			// 3. S_BLACKLIST 테이블에서 isblack 받아오기
			BlackDAO bDao = new BlackDAO();
			int isBlack = bDao.getSBlack(sid);
			map.put("Admin_selectedSIsBlack", isBlack);
			
			// 4. REPORT 테이블에서 해당 sid가 target_id인 신고정보 받아오기
			AdminReportDAO rDao = new AdminReportDAO();
			ArrayList<ReportDTO> selectedSellerRList = rDao.getRList(sid, group);
			rDao = new AdminReportDAO();
			int maxRPage = rDao.getMaxSelectedRPage(sid);
			
			map.put("Admin_selectedSRListCurrPage", group);
			map.put("Admin_maxRPage", maxRPage);
			map.put("Admin_selectedSRList", selectedSellerRList);
			
			// 5. admin_SellerDetail.jsp 로 포워딩
			req.setAttribute("Admin_SDetailData", map);
			RequestDispatcher dis = req.getRequestDispatcher("admin_SellerDetail.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}

	public void adminLogin() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String adminID = req.getParameter("userID");
		String adminPW = req.getParameter("userPW");
		System.out.println(adminID+" / "+adminPW);
		String page = "./Admin/admin_Login.jsp";
		MemberDAO dao = new MemberDAO();
		String msg = "아이디와 비밀번호를 확인해주세요.";
		if(dao.adminLogin(adminID, adminPW)) {
			System.out.println("관리자 로그인 성공");
			req.getSession().setAttribute("loginID", adminID);
			page = "AdminMain";
			msg = "";
		}
		req.setAttribute("msg", msg);
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

}
