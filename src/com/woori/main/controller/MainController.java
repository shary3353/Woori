package com.woori.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.main.service.MainService;

@WebServlet({"/C_main","/C_itemDetail","/C_ItemReservation","/C_Reservation"})
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dual(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		dual(req, resp);
	}

	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println(sub);
		
		MainService service = new MainService(req, resp);
		switch (sub) {
		case "/C_main":
				System.out.println("Consumer Mainpage");
				service.Cmain();
			break;
			
		case "/C_itemDetail":
				System.out.println("물품 상세정보 페이지");
				service.Citemdetail();
			break;
		case "/C_ItemReservation":
			System.out.println("물품 예약 상세보기 페이지");
			service.CitemReservation();
		break;
		
		case "/C_Reservation":
			System.out.println("물품 예약 페이지");
			service.CReservation();
		}
	}
	
	
}
