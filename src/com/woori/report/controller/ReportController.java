package com.woori.report.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.report.service.ReportService;
/*
요청 : 판매자 신고내역 리스트
*/
@WebServlet({"/Seller/sReportList" , "/ServiceCenter/Report", "/ServiceCenter/ReportDetail", "/Consumer/cReportList"})
public class ReportController extends HttpServlet {

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

	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("dual request url : "+sub);
		
		ReportService  service = new ReportService(req,resp);
		
		switch(sub) {
		case "/Seller/sReportList":
			System.out.println("판매자 신고내역 리스트 요청");
			service.sReportList();
			break;
			
		case  "/ServiceCenter/Report":
			System.out.println("Request report");
			service.report();
		break;
		
		case "/ServiceCenter/ReportDetail":
			System.out.println("Request report Detail");
			service.detail();
			break;
		case "/Consumer/cReportList":
			System.out.println("Request Customer ReportList");
			service.cReportList();
			break;
		}
		
	}

	
}
