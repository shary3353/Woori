package com.woori.main.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String page = "/C_ItemReservation";
		String msg = "예약이 되지않았습니다 다시 예약을 해주시길 바랍니다.";
		
		if(success = true) {
			page = "Consumer/C_ItemReservation.jsp";
			msg = "정상적으로 예약되었습니다.";
		}
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);	
	}
	
	
	public void AdminMain() {
		

	}

}
