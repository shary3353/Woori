package com.woori.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.woori.member.dao.ListDAO;
import com.woori.member.dao.MemberDAO;
import com.woori.member.dto.BlackListDTO;
import com.woori.member.dto.CustomerDTO;
import com.woori.member.dto.CustomerListDTO;
import com.woori.member.dto.ReportListDTO;
import com.woori.member.dto.SellerDTO;
import com.woori.member.dto.SellerListDTO;

public class MemberService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public MemberService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void cList() throws ServletException, IOException {
		int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		ListDAO dao = new ListDAO();
		HashMap<String, Object> map = dao.cList(group);
		
		req.setAttribute("cList", map.get("cList"));
		req.setAttribute("maxCustomerPage", map.get("maxCustomerPage"));
		req.setAttribute("currPage", group);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_CustomerList.jsp");
		dis.forward(req, resp);
	}

	public void sList() throws ServletException, IOException {
		int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		
		ListDAO dao = new ListDAO();
		HashMap<String, Object> map = dao.sList(group);
		
		req.setAttribute("sList", map.get("sList"));
		req.setAttribute("currPage", group);
		req.setAttribute("maxSellerPage", map.get("maxPage"));
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_SellerList.jsp");
		dis.forward(req, resp);
	}

	public void bList() throws ServletException, IOException {
		int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		ListDAO dao = new ListDAO();
		HashMap<String, Object> map = dao.bList(group);
		
		req.setAttribute("bList", map.get("bList"));
		req.setAttribute("maxBlackPage", map.get("maxBlackPage"));
		req.setAttribute("currPage", group);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_BlackList.jsp");
		dis.forward(req, resp);
	}

	public void rList() throws ServletException, IOException {
		int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		
		ListDAO dao = new ListDAO();
		HashMap<String, Object> map = dao.rList(group);
		
		req.setAttribute("rList", map.get("rList"));
		req.setAttribute("maxReportPage", map.get("maxReportPage"));
		req.setAttribute("currPage", group);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
		dis.forward(req, resp);
	}
	
    public void cSearch() throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String inputC = req.getParameter("cSearch");
        System.out.println("검색할 구매자 : "+inputC);
        
        ListDAO dao = new ListDAO();
        ArrayList<CustomerListDTO> searchedC = dao.cSearch(inputC);
        
        req.setAttribute("cList", searchedC);
        RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_CustomerList.jsp");
        dis.forward(req, resp);
    }

    public void sSearch() throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        String inputS = req.getParameter("sSearch");
        System.out.println("검색할 판매자 : "+inputS);
        
        ListDAO dao = new ListDAO();
        ArrayList<SellerListDTO> searchedS = dao.sSearch(inputS);
        
        req.setAttribute("sList", searchedS);
        RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_SellerList.jsp");
        dis.forward(req, resp);
    }

    public void bSearch() throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String inputB = req.getParameter("bSearch");
        String bSearchOption = req.getParameter("bSearchOption");
        
        if(bSearchOption.equals("100")) {                //판매자 검색
            System.out.println("검색할 판매자 id : "+inputB);
            ListDAO dao = new ListDAO();
            ArrayList<BlackListDTO> searchedB = dao.bSellerSearch(inputB);
            req.setAttribute("bList", searchedB);
        }else if(bSearchOption.equals("200")) {    //구매자 검색
            System.out.println("검색할 구매자 id : "+inputB);
            ListDAO dao = new ListDAO();
            ArrayList<BlackListDTO> searchedB = dao.bCustomerSearch(inputB);
            req.setAttribute("bList", searchedB);
        }
        
        RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_BlackList.jsp");
        dis.forward(req, resp);
    }

    public void rSearch() throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		
        String inputR = req.getParameter("rSearch");
        String rSearchOption = req.getParameter("rSearchOption");
        
        if(rSearchOption.equals("100")) {                //신고자ID 검색
            System.out.println("검색할 신고자 id : "+inputR);
            ListDAO dao = new ListDAO();
            HashMap<String, Object> map = dao.rReporterSearch(inputR, group);
            req.setAttribute("rList", map.get("rList"));
            req.setAttribute("currPage", group);
            req.setAttribute("maxReportPage", map.get("maxReportPage"));
        }else if(rSearchOption.equals("200")) {    //신고대상자ID 검색
            System.out.println("검색할 신고대상자 id : "+inputR);
            ListDAO dao = new ListDAO();
            HashMap<String, Object> map = dao.rTargetSearch(inputR, group);
            req.setAttribute("rList", map.get("rList"));
            req.setAttribute("currPage", group);
            req.setAttribute("maxReportPage", map.get("maxReportPage"));
        }
        RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
        dis.forward(req, resp);
    }
    
    //블랙리스트에 추가시 필요한 데이터 - id, admin_id, reason
  	public void sBlackRegist() throws ServletException, IOException {
  		req.setCharacterEncoding("utf-8");
  		HashMap<String, Object> map = new HashMap<>();
  		String sid = req.getParameter("id");
  		//String admin_id = (String) req.getSession().getAttribute("loginID");
  		String admin_id = "admin001";	//테스트용
  		String reason = req.getParameter("bReason");
  		System.out.println(sid+" / "+reason);
  		
  		//1. s_blacklist 에 해당 sid가 있는지 확인
  		MemberDAO dao = new MemberDAO();
  		boolean  success = false;
  		int stack = 1;
  		if(dao.checkSID(sid)) {
  			//1-1. 해당 sid가 블랙리스트에 존재하면(true) update blacklist isblack=1, stack+1, reason, admin_id
  			dao = new MemberDAO();
  			success = dao.sBlackUpdate(sid, admin_id, reason);
  			dao = new MemberDAO();
  			stack = dao.getSellerNewStack(sid);
  		}else {								
  			//1-2. 해당 sid가 블랙리스트에 없으면(false) insert into blacklist
  			dao = new MemberDAO();
  			success = dao.sBlackRegist(sid, admin_id, reason);
  		}
  		if(success) {	//작성 성공 시
  			System.out.println("해당 판매자를 블랙리스트에 등록하였습니다.");
  			map.put("addBlack", success);
  			map.put("newStack", stack);
  			Gson gson = new Gson();
  			String json = gson.toJson(map);
  			System.out.println(json);
  			resp.getWriter().print(json);
  		}
  	}
  	public void cBlackRegist() throws ServletException, IOException {
  		req.setCharacterEncoding("utf-8");
  		HashMap<String, Object> map = new HashMap<>();
  		String cid = req.getParameter("id");
  		//String admin_id = (String) req.getSession().getAttribute("loginID");
  		String admin_id = "admin001";	//테스트용
  		String reason = req.getParameter("bReason");
  		System.out.println(cid+" / "+reason);
  		
  		//1. c_blacklist 에 해당 cid가 있는지 확인
  		MemberDAO dao = new MemberDAO();
  		boolean  success = false;
  		int stack = 1;
  		if(dao.checkCID(cid)) {
  			//1-1. 해당 cid가 블랙리스트에 존재하면(true) update blacklist isblack=1, stack+1, reason, admin_id
  			dao = new MemberDAO();
  			success = dao.cBlackUpdate(cid, admin_id, reason);
  			dao = new MemberDAO();
  			stack = dao.getCustomerNewStack(cid);
  		}else {								
  			//1-2. 해당 cid가 블랙리스트에 없으면(false) insert into blacklist
  			dao = new MemberDAO();
  			success = dao.cBlackRegist(cid, admin_id, reason);
  		}
  		if(success) {	//작성 성공 시
  			System.out.println("해당 구매자를 블랙리스트에 등록하였습니다.");
  			map.put("addBlack", success);
  			map.put("newStack", stack);
  			Gson gson = new Gson();
  			String json = gson.toJson(map);
  			System.out.println(json);
  			resp.getWriter().print(json);
  		}
  	}
  	
	public void blackCancel() throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		System.out.println("블랙리스트 취소 할 아이디 : "+id);
		if(id.indexOf("-")>0) {	//-를 포함할 경우 판매자
			MemberDAO dao = new MemberDAO();
			if(dao.sBlackCancel(id)) {
				System.out.println(id+"의 블랙리스트 취소완료");
			}
		}else {							//그렇지 않을 경우 구매자
			MemberDAO dao = new MemberDAO();
			if(dao.cBlackCancel(id)) {
				System.out.println(id+"의 블랙리스트 취소완료");
			}
		}
		resp.sendRedirect("./bList");
		
	}


	public void sPfpDatail() throws ServletException, IOException { //판매자메인 - 판매자 회원정보 상세보기
		req.getSession().setAttribute("loginId","123-12-12345");//test용
		String sid = (String)req.getSession().getAttribute("loginId");
		System.out.println(sid); //로그인한 아이디 확인
		
		MemberDAO dao = new MemberDAO();
		SellerDTO dto = dao.sPfpDetail(sid);
		System.out.println(dto);//해당판매자정보
		req.setAttribute("detail", dto);
		RequestDispatcher dis = req.getRequestDispatcher("./S_profile.jsp");
		dis.forward(req, resp);
	}

	public void sPfpUpdateForm() throws ServletException, IOException{//판매자메인 - 판매자 수정폼 보기
		String sid = req.getParameter("sid");
		System.out.println("수정할 sid :" + sid);
		
		MemberDAO dao = new MemberDAO();
		SellerDTO dto = dao.sPfpDetail(sid);
		req.setAttribute("detail", dto);
		RequestDispatcher dis = req.getRequestDispatcher("./S_profileRevise.jsp");
		dis.forward(req, resp);
	}

	public void sPfpUpdate() throws ServletException, IOException{//판매자메인 - 판매자 회원정보 수정
		String sid = req.getParameter("sid");
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String storeCall = req.getParameter("storeCall");
		System.out.println("변경정보:"+sid+"/"+name+"/"+pw+"/"+email+"/"+phone+"/"+storeCall);

		SellerDTO dto = new SellerDTO();
		dto.setSid(sid);
		dto.setName(name);
		dto.setPw(pw);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setStore_call(storeCall);
		
		MemberDAO dao = new MemberDAO();
		dao.sPfpUpdate(dto);
		resp.sendRedirect("sPfpDetail?sid="+dto.getSid());
	}

	public void cDetail() throws ServletException, IOException {
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String cid = (String) req.getSession().getAttribute("loginId");
		System.out.println("상세보기할 cid : " + cid );
		
		MemberDAO dao = new MemberDAO();
		CustomerDTO dto = new CustomerDTO();
		dto = dao.cDetail(cid);
		req.setAttribute("list", dto);
		RequestDispatcher dis = req.getRequestDispatcher("./C_MyInfo.jsp");
		dis.forward(req, resp);
	}

	public void cUpdateForm() throws ServletException, IOException {
		String cid = req.getParameter("cid");
		String pw = req.getParameter("pw");
		System.out.println("cid / pw : " + cid +" / "+ pw);
		
		MemberDAO dao = new MemberDAO();
		CustomerDTO dto = dao.cUpadateForm(cid, pw);
		
		String page = "cDetail"; 
		if(dto != null) {
			page = "./C_MyInfoModify.jsp";
			req.setAttribute("list", dto);
		}
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

	public void cUpdateInfo() throws ServletException, IOException {
		String cid = req.getParameter("cid");
		String pw = req.getParameter("pw");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		System.out.println(cid+" / "+pw+" / "+email+" / "+phone);
		CustomerDTO dto = new CustomerDTO();
		dto.setCid(cid);
		dto.setPw(pw);
		dto.setEmail(email);
		dto.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		int success = 0;
		success = dao.cUpdateInfo(dto);
		
		String page = "cUpadateForm";
		if(success>0) {
			page = "cDetail";
		}
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}



	
	

}
