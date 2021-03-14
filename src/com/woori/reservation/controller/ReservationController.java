package com.woori.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.reservation.service.ReservationService;
/*
 요청 : 판매자 예약내역 리스트, 판매자 예약현황변경
 */
@WebServlet({"/Seller/sReservationList","/Seller/updateResevationStatus"})
public class ReservationController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("get request url : "+sub);
		
		dual(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("post request url : "+sub);
		
		dual(req,resp);
	}

	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("dual request url : "+sub);
		
		ReservationService service = new ReservationService(req, resp);
		switch(sub) {
		case "/Seller/sReservationList":
			System.out.println("판매자 예약내역 리스트 요청");
			service.sReservationList();
			break;
		case "/Seller/updateResevationStatus":
			System.out.println("판매자 예약현황 변경 요청");
			service.updateResevationStatus();
			break;
			
			
		}
		
	}
	
	

}
