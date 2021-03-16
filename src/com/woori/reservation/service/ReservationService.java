package com.woori.reservation.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.reservation.dao.ReservationDAO;
import com.woori.reservation.dto.ReservationDTO;

public class ReservationService {

	//공통
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;
	
	public ReservationService(HttpServletRequest req, HttpServletResponse resp) {
		this.req =req;
		this.resp =resp;
	}

	public void sReservationList() throws ServletException, IOException { //판매자 예약내역 리스트
		//로그인검사 추가예정
		req.getSession().setAttribute("loginId","123-12-12345");//test용
		String sid = (String)req.getSession().getAttribute("loginId");
		System.out.println("판매자"+ sid +"의 예약 내역입니다."); //로그인한 아이디 확인& 판매자 확인
		
		String pageParam = req.getParameter("page");
		System.out.println("page : "+pageParam);
		
		int group = 1;
		if(pageParam != null) {
			group = Integer.parseInt(pageParam);
		}
		
		ReservationDAO dao = new ReservationDAO();
		HashMap<String, Object> map = dao.sReservationList(sid, group);
		
		req.setAttribute("maxPage", map.get("maxPage"));
		req.setAttribute("list", map.get("list"));
		req.setAttribute("currPage", group);
		
		dis = req.getRequestDispatcher("S_Reservationlist.jsp"); //S_Reservationlist.jsp로 이동
		dis.forward(req, resp);//값보냄
	}

	public void updateResevationStatus() throws ServletException, IOException {//판매자 예약현황변경
		//로그인검사 추가예정
		req.getSession().setAttribute("loginId","123-12-12345");//test용
		String sid = (String)req.getSession().getAttribute("loginId");
		System.out.println("판매자"+ sid +"가 예약 현황을 변경합니다."); //로그인한 아이디 확인& 판매자 확인
		
		int r_idx = Integer.parseInt(req.getParameter("r_idx"));
		int rs_idx = Integer.parseInt(req.getParameter("reservationState"));
		System.out.println("변경예약:"+r_idx+"/"+rs_idx);
		
		ReservationDAO dao = new ReservationDAO();
		dao.updateResevationStatus(r_idx, rs_idx);
		
		resp.sendRedirect("./sReservationList");//예약리스트로 이동
	}

	public void cReservationList() throws ServletException, IOException {
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String cid = (String) req.getSession().getAttribute("loginId");
		System.out.println(cid + " 의 예약내역 불러오기");
		String pageParam = req.getParameter("page");
		System.out.println("이동하고 싶은 page : " + pageParam);
		int group = 1;
		if (pageParam != null) {
			group = Integer.parseInt(pageParam);
		}
		ReservationDAO dao = new ReservationDAO();
		HashMap<String, Object> map = dao.cReservationList(group, cid);

		req.setAttribute("maxPage", map.get("maxPage"));
		req.setAttribute("list", map.get("list"));
		req.setAttribute("currPage", group);

		RequestDispatcher dis = req.getRequestDispatcher("./C_ReservationList.jsp");
		dis.forward(req, resp);
	}

}
