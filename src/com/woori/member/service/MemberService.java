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
	
	public void cjoin() throws ServletException, IOException {
		String name = req.getParameter("Cunsumername");
		String cid = req.getParameter("cid");
		String pw = req.getParameter("Pw");
		String Birthday = req.getParameter("Birthday");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String Phone = req.getParameter("Phone");
		System.out.println(cid+"/"+pw+"/"+name+"/"+Birthday+"/"+gender+"/"+email+"/"+Phone);
		
		CustomerDTO dto = new CustomerDTO();
		
		dto.setCid(cid);
		dto.setPw(pw);
		dto.setName(name);
		dto.setBirthday(Birthday);
		dto.setGender(gender);
		dto.setEmail(email);
		dto.setPhone(Phone);
		//실패했을 때
		msg="회원가입 실패";
		page="joinForm.jsp";
		//성공했을때
		if(dao.cjoin(dto)>0) {
			msg="회원가입 성공";
			page="index.jsp";
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

	public void overlay() throws IOException {// 중복체크
		String cid = req.getParameter("icd");
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

	public void Admin_Loginid() throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();

		String id = (String) req.getSession().getAttribute("adminId");
		String pw = req.getParameter("adminPw");
		System.out.println(id + "/" + pw);

		page = "index.jsp";
		msg = "아이디 비밀번호를 다시 확인해 주세요!";

		if (dao.login(id, pw)) {
			page = "/list";
			msg = id + " 님 반갑 습니다.";
			req.getSession().setAttribute("loginId", id);
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);

	}

	public void C_Loginid() throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String cid = req.getParameter("cId");
		String pw = req.getParameter("Pw");
		System.out.println(cid + "/" + pw);
		page = "C_login.jsp";
		msg = "아이디 비밀번호를 다시 확인해 주세요!";

		if (dao.clogin(cid, pw)) {
			page = "/clist";
			msg = cid + " 님 반갑 습니다.";
			req.getSession().setAttribute("logincId", cid);
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

	public void S_Loginid() throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String sid = req.getParameter("sId");
		String pw = req.getParameter("pw");
		System.out.println(sid + "/" + pw);
		page = "C_login.jsp";
		msg = "아이디 비밀번호를 다시 확인해 주세요!";

		if (dao.slogin(sid, pw)) {
			page = "/slist";
			msg = sid + " 님 반갑 습니다.";
			req.getSession().setAttribute("loginsId", sid);
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);

	}

	public void cList() throws ServletException, IOException {
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
	}

	public void sList() throws ServletException, IOException {
		int group = 1;
		String page = req.getParameter("page");

		if (page != null) {
			group = Integer.parseInt(page);
		}

		ListDAO dao = new ListDAO();
		HashMap<String, Object> map = dao.sList(group);

		req.setAttribute("sList", map.get("sList"));
		req.setAttribute("currPage", group);
		req.setAttribute("maxSellerPage", map.get("maxPage"));
		RequestDispatcher dis = req.getRequestDispatcher("admin_SellerList.jsp");
		dis.forward(req, resp);
	}

	public void cSearch() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String inputC = req.getParameter("cSearch");
		System.out.println("검색할 구매자 : " + inputC);

		ListDAO dao = new ListDAO();
		ArrayList<CustomerListDTO> searchedC = dao.cSearch(inputC);

		req.setAttribute("cList", searchedC);
		RequestDispatcher dis = req.getRequestDispatcher("admin_CustomerList.jsp");
		dis.forward(req, resp);
	}

	public void sSearch() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String inputS = req.getParameter("sSearch");
		System.out.println("검색할 판매자 : " + inputS);

		ListDAO dao = new ListDAO();
		ArrayList<SellerListDTO> searchedS = dao.sSearch(inputS);

		req.setAttribute("sList", searchedS);
		RequestDispatcher dis = req.getRequestDispatcher("admin_SellerList.jsp");
		dis.forward(req, resp);
	}

	public void sPfpDatail() throws ServletException, IOException { // 판매자메인 - 판매자 회원정보 상세보기
		// 로그인검사 추가예정
		req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		/*
		 * if(sid != null) {//로그인 여부 판별
		 * 
		 * } else { //로그인을 안 했으면 로그인페이지로 resp.sendRedirect("판매자로그인.jsp"); }
		 */
		System.out.println(sid); // 로그인한 아이디 확인

		MemberDAO dao = new MemberDAO();
		SellerDTO dto = dao.sPfpDetail(sid);
		System.out.println(dto);// 해당판매자정보
		req.setAttribute("detail", dto);
		RequestDispatcher dis = req.getRequestDispatcher("./S_profile.jsp");
		dis.forward(req, resp);
	}

	public void sPfpUpdateForm() throws ServletException, IOException {// 판매자메인 - 판매자 수정폼 보기
		// 로그인검사 추가예정
		req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		/*
		 * if(sid != null) {//로그인 여부 판별
		 * 
		 * } else { //로그인을 안 했으면 로그인페이지로 resp.sendRedirect("판매자로그인.jsp"); }
		 */
		System.out.println("수정할 sid :" + sid);

		MemberDAO dao = new MemberDAO();
		SellerDTO dto = dao.sPfpDetail(sid);
		req.setAttribute("detail", dto);
		RequestDispatcher dis = req.getRequestDispatcher("./S_profileRevise.jsp");
		dis.forward(req, resp);
	}

	public void sPfpUpdate() throws ServletException, IOException {// 판매자메인 - 판매자 회원정보 수정
		// 로그인검사 추가예정
		req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String loginID = (String) req.getSession().getAttribute("loginID");
		/*
		 * if(loginID != null) {//로그인 여부 판별
		 * 
		 * } else { //로그인을 안 했으면 로그인페이지로 resp.sendRedirect("판매자로그인.jsp"); }
		 */
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

		MemberDAO dao = new MemberDAO();
		dao.sPfpUpdate(dto);
		resp.sendRedirect("sPfpDetail?sid=" + dto.getSid());
	}

	public void cDetail() throws ServletException, IOException {
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String cid = (String) req.getSession().getAttribute("loginId");
		// String loginId = (String) req.getParameter().getAttribute("loginId");
		String loginId = cid;
		String msg = "";
		if (loginId != null) {
			System.out.println("상세보기할 cid : " + cid);

			MemberDAO dao = new MemberDAO();
			CustomerDTO dto = new CustomerDTO();
			dto = dao.cDetail(cid);
			req.setAttribute("list", dto);
			RequestDispatcher dis = req.getRequestDispatcher("./C_MyInfo.jsp");
			dis.forward(req, resp);

		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}
	}

	public void cUpdateForm() throws ServletException, IOException {
		// String loginId = (String) req.getParameter().getAttribute("loginId");
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String loginId = (String) req.getSession().getAttribute("loginId");
		String msg = "";
		if (loginId != null) {
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
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}
	}

	public void cUpdateInfo() throws ServletException, IOException {
		// String loginId = (String) req.getParameter().getAttribute("loginId");
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String loginId = (String) req.getSession().getAttribute("loginId");
		String msg = "";
		if (loginId != null) {
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
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}
	}

	public void AdminCustomerDetail() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HashMap<String, Object> map = new HashMap<>();
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
		ArrayList<ReportDTO> selectedCustomerRList = rDao.getRList(cid);
		map.put("Admin_selectedCRList", selectedCustomerRList);

		// 5. admin_CustomerDetail.jsp 로 포워딩
		req.setAttribute("Admin_CDetailData", map);
		RequestDispatcher dis = req.getRequestDispatcher("admin_CustomerDetail.jsp");
		dis.forward(req, resp);
	}

	public void AdminSellerDetail() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HashMap<String, Object> map = new HashMap<>();
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
		ArrayList<ReportDTO> selectedSellerRList = rDao.getRList(sid);
		map.put("Admin_selectedSRList", selectedSellerRList);

		// 5. admin_SellerDetail.jsp 로 포워딩
		req.setAttribute("Admin_SDetailData", map);
		RequestDispatcher dis = req.getRequestDispatcher("admin_SellerDetail.jsp");
		dis.forward(req, resp);
	}

}
