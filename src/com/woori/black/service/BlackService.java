package com.woori.black.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.woori.black.dao.BlackDAO;
import com.woori.member.dto.BlackListDTO;

public class BlackService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public BlackService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	public void bList() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("adminLoginID");
		if(loginID != null) {
			int group = 1;
			String page = req.getParameter("page");
			
			if(page != null) {
				group = Integer.parseInt(page);
			}
			BlackDAO dao = new BlackDAO();
			HashMap<String, Object> map = dao.bList(group);
			
			req.setAttribute("bList", map.get("bList"));
			req.setAttribute("maxBlackPage", map.get("maxBlackPage"));
			req.setAttribute("currPage", group);
			RequestDispatcher dis = req.getRequestDispatcher("admin_BlackList.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}
	
	public void bSearch() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("adminLoginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			String inputB = req.getParameter("bSearch");
			String bSearchOption = req.getParameter("bSearchOption");
			
			if(bSearchOption.equals("100")) {                //판매자 검색
				System.out.println("검색할 판매자 id : "+inputB);
				BlackDAO dao = new BlackDAO();
				ArrayList<BlackListDTO> searchedB = dao.bSellerSearch(inputB);
				req.setAttribute("bList", searchedB);
				req.setAttribute("inputB", inputB);
			}else if(bSearchOption.equals("200")) {    //구매자 검색
				System.out.println("검색할 구매자 id : "+inputB);
				BlackDAO dao = new BlackDAO();
				ArrayList<BlackListDTO> searchedB = dao.bCustomerSearch(inputB);
				req.setAttribute("bList", searchedB);
				req.setAttribute("inputB", inputB);
			}
			
			RequestDispatcher dis = req.getRequestDispatcher("admin_BlackList.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
    }
	
    
    //블랙리스트에 추가시 필요한 데이터 - id, admin_id, reason
  	public void sBlackRegist() throws ServletException, IOException {
  		String loginID = (String)req.getSession().getAttribute("adminLoginID");
  		if(loginID != null) {
  			req.setCharacterEncoding("utf-8");
  			HashMap<String, Object> map = new HashMap<>();
  			String sid = req.getParameter("id");
  			String admin_id = (String) req.getSession().getAttribute("loginID");
  			
  			String reason = req.getParameter("bReason");
  			System.out.println(sid+" / "+reason);
  			
  			//1. s_blacklist 에 해당 sid가 있는지 확인
  			BlackDAO dao = new BlackDAO();
  			boolean  success = false;
  			int stack = 1;
  			if(dao.checkSID(sid)) {
  				//1-1. 해당 sid가 블랙리스트에 존재하면(true) update blacklist isblack=1, stack+1, reason, admin_id
  				dao = new BlackDAO();
  				success = dao.sBlackUpdate(sid, admin_id, reason);
  				dao = new BlackDAO();
  				stack = dao.getSellerNewStack(sid);
  			}else {								
  				//1-2. 해당 sid가 블랙리스트에 없으면(false) insert into blacklist
  				dao = new BlackDAO();
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
  		}else {
  			resp.sendRedirect("./admin_Login.jsp");
  		}
  	}
  	
  	public void cBlackRegist() throws ServletException, IOException {
  		String loginID = (String)req.getSession().getAttribute("adminLoginID");
  		if(loginID != null) {
  			req.setCharacterEncoding("utf-8");
  			HashMap<String, Object> map = new HashMap<>();
  			String cid = req.getParameter("id");
  			String admin_id = (String) req.getSession().getAttribute("loginID");
  			String reason = req.getParameter("bReason");
  			System.out.println(cid+" / "+reason);
  			
  			//1. c_blacklist 에 해당 cid가 있는지 확인
  			BlackDAO dao = new BlackDAO();
  			boolean  success = false;
  			int stack = 1;
  			if(dao.checkCID(cid)) {
  				//1-1. 해당 cid가 블랙리스트에 존재하면(true) update blacklist isblack=1, stack+1, reason, admin_id
  				dao = new BlackDAO();
  				success = dao.cBlackUpdate(cid, admin_id, reason);
  				dao = new BlackDAO();
  				stack = dao.getCustomerNewStack(cid);
  			}else {								
  				//1-2. 해당 cid가 블랙리스트에 없으면(false) insert into blacklist
  				dao = new BlackDAO();
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
  		}else {
  			resp.sendRedirect("./admin_Login.jsp");
  		}
  	}
  	
	public void blackCancel() throws ServletException, IOException{
		String loginID = (String)req.getSession().getAttribute("adminLoginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			String id = req.getParameter("id");
			System.out.println("블랙리스트 취소 할 아이디 : "+id);
			if(id.indexOf("-")>0) {	//-를 포함할 경우 판매자
				BlackDAO dao = new BlackDAO();
				if(dao.sBlackCancel(id)) {
					System.out.println(id+"의 블랙리스트 취소완료");
				}
			}else {							//그렇지 않을 경우 구매자
				BlackDAO dao = new BlackDAO();
				if(dao.cBlackCancel(id)) {
					System.out.println(id+"의 블랙리스트 취소완료");
				}
			}
			resp.sendRedirect("./bList");
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
		
	}


	
}
