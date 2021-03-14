package com.woori.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		req.setAttribute("currPage", group);
		req.setAttribute("maxCustomerPage", map.get("maxCustomerPage"));
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

	public void blackList() throws ServletException, IOException {
		ListDAO dao = new ListDAO();
		ArrayList<BlackListDTO> bList = new ArrayList<>();
		bList = dao.bList();
		
		req.setAttribute("bList", bList);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_BlackList.jsp");
		dis.forward(req, resp);
	}

	public void reportList() throws ServletException, IOException {
		int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		
		ListDAO dao = new ListDAO();
		HashMap<String, Object> map = dao.rList(group);
		
		req.setAttribute("rList", map.get("rList"));
		req.setAttribute("currPage", group);
		req.setAttribute("maxReportPage", map.get("maxReportPage"));
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
		dis.forward(req, resp);
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


}
