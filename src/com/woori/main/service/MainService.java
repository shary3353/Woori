package com.woori.main.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.woori.main.dao.MainDAO;
import com.woori.product.dto.ProductDTO;
import com.woori.reservation.dto.ReservationDTO;

public class MainService {
	
	//자주쓰는것들 인자값 선언
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;
	
	public MainService(HttpServletRequest req , HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void Cmain() throws ServletException, IOException {
		HashMap<String, Object> map = null;
		MainDAO dao = new MainDAO();
		for (int i = 1; i < 7; i++) {
			map = new HashMap<String, Object>();
			map = dao.Cmain(i);
			System.out.println(map);
			req.setAttribute("list"+i, map.get("list"+i));
		}
		dao.resClose();
		dis = req.getRequestDispatcher("Consumer/C_main.jsp");
		dis.forward(req, resp);
		
	}

	public void Citemdetail() throws ServletException, IOException {
		String pidx = req.getParameter("p_idx");
		System.out.println(pidx);
		MainDAO dao = new MainDAO();
		ProductDTO dto = dao.Citemdetail(pidx);
		System.out.println(dto);
		String page = "/";
		if(dto != null) {
			page="Consumer/C_ItemDetail.jsp";
			req.setAttribute("dto", dto);
		}
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

	public void CitemReservation() throws ServletException, IOException {
		String pidx = req.getParameter("p_idx");
		System.out.println(pidx);
		MainDAO dao = new MainDAO();
		ProductDTO dto = dao.Creservationdetail(pidx);
		System.out.println(dto);
		String page = "/";
		if(dto != null) {
			page="Consumer/C_ItemReservation.jsp";
			req.setAttribute("dto", dto);
		}
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

	public void CReservation() throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pidx = req.getParameter("p_idx");
		String sid = req.getParameter("sid");
		String cid = req.getParameter("cid");
		String visitdate = req.getParameter("visitdate");
		System.out.println(pidx+"/" +sid+"/"+cid+"/"+visitdate);
		MainDAO dao = new MainDAO();
		ReservationDTO dto = new ReservationDTO();
		boolean success = dao.Creservation(pidx,cid,visitdate);
		
		String page = "C_ItemReservation";
		String msg = "예약이 되지않았습니다 다시 예약을 해주시길 바랍니다.";
		if(success = true) {
			page = "Consumer/cReservationList";
			msg = "정상적으로 예약되었습니다.";
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);	
		dis.forward(req, resp);
	}
	
	
	public void AdminMain() throws IOException, ServletException {
		//로그인 세션의 아이디에 admin이 있는지 검사 
		//admin이 포함되어있을 경우에만 관리자 메인 페이지를 띄운다
		String loginID = (String) req.getSession().getAttribute("loginID");
		if(loginID != null) {
			if(loginID.contains("admin")) {
				System.out.println("관리자 로그인중입니다.");
				resp.sendRedirect("Admin/admin_Main.jsp");
			}else {
				String msg = "접근이 불가능한 페이지입니다.";
				req.setAttribute("msg", msg);
				RequestDispatcher dis = req.getRequestDispatcher("AdminLoginPage");
				dis.forward(req, resp);
			}
		}else {
			resp.sendRedirect("./Admin/admin_Login.jsp");
		}
		
	}

	public void Csearch() throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String searchname = "%"+req.getParameter("searchname")+"%";
		if(searchname.equals("%%")) {
			searchname = null;
		}
		String searchtitle = req.getParameter("searchname");
		System.out.println(searchname);
		MainDAO dao = new MainDAO();
		ArrayList<ProductDTO> list = dao.mainSearch(searchname);
		System.out.println(list);
		System.out.println("리스트 크기 : " + list.size());
		String msg = "현재 존재하는 게시글이 없습니다.";
		if(list != null && list.size()>0) {
			req.setAttribute("search", list);	
			msg = searchtitle;
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher("C_SearchList.jsp");
		dis.forward(req, resp);
		
	}

	public void likeconfirm() throws ServletException, IOException {
		boolean success = false;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cid = req.getParameter("cid");
		String pidx = req.getParameter("pidx");
		System.out.println(cid +"/" + pidx);
		MainDAO dao = new MainDAO();
		success =  dao.likeconfirm(cid,pidx);
		System.out.println("좋아요 가능여부 : " + success);
		map.put("use", success);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println(json);
		resp.getWriter().print(json);
	}
	
	public void Clikeplus() throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cid = req.getParameter("cid");
		String pidx = req.getParameter("pidx");
		System.out.println(cid+"/"+pidx);
		MainDAO dao = new MainDAO();
		int success = dao.likesTableAdd(cid,pidx);
		System.out.println("likes 테이블에 정상추가 : " + success);
		if(success >0) {
			dao = new MainDAO();
			boolean success1 = dao.likePlus(pidx);
			System.out.println("Product 테이블 like +1 " + success1);
			dao.resClose();
			map.put("plus", success1);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			System.out.println(json);
			resp.getWriter().print(json);
		}
	}

	public void Clikeminus() throws ServletException, IOException {
		
		boolean success1 = false;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cid = req.getParameter("cid");
		String pidx = req.getParameter("pidx");
		System.out.println(cid+"/"+pidx);
		MainDAO dao = new MainDAO();
		int success = dao.likesTableDel(cid,pidx);
		System.out.println("likes 테이블에 정상삭제 : " + success);
		if(success >0) {
			dao = new MainDAO();
			success1 = dao.likeMinus(pidx);
			System.out.println("Product 테이블 like -1 " + success1);
			map.put("minus", success1);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			System.out.println(json);
			resp.getWriter().print(json);
		}
	}


	public void NaviCategory() throws ServletException, IOException {
		String c_idx = req.getParameter("c_idx");
		String cate = "";
		System.out.println(c_idx);
		if(c_idx.equals("1")) {
			cate = "와인";
		}else if(c_idx.equals("2")) {
			cate ="위스키";
		}else if(c_idx.equals("3")) {
			cate ="꼬냑/브랜디";
		}else if(c_idx.equals("4")) {
			cate ="보드카";
		}else if(c_idx.equals("5")) {
			cate ="샴페인";
		}
		MainDAO dao = new MainDAO();
		ArrayList<ProductDTO> list =  dao.categorySearch(c_idx);
		System.out.println("리스트 크기 : " + list.size());
		String msg = "현재 존재하는 " +cate+ "이(가) 없습니다.";
		if(list != null && list.size()>0) {
			req.setAttribute("search", list);	
			msg=cate;
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher("C_SearchList");
		dis.forward(req, resp);
	}

}
