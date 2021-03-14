package com.woori.report.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.report.dao.ReportDAO;
import com.woori.report.dto.ReportDTO;
import com.woori.reservation.dto.ReservationDTO;

public class ReportService {
	
	//공통
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;
	
	public ReportService(HttpServletRequest req, HttpServletResponse resp) {
		this.req =req;
		this.resp =resp;
	}

	public void sReportList() throws ServletException, IOException { //판매자 신고내역 리스트
		//로그인검사 추가예정
		req.getSession().setAttribute("loginId","123-12-12345");//test용
		String sid = (String)req.getSession().getAttribute("loginId");
		System.out.println("판매자"+ sid +"의 신고 내역입니다."); //로그인한 아이디 확인& 판매자 확인
		
		ReportDAO dao = new ReportDAO();
		ArrayList<ReportDTO> list = dao.sReportList(sid);
		System.out.println(list); //리스트 확인
		System.out.println("신고리스트 수:" + list.size()); //리스트 사이즈 확인
		
		req.setAttribute("list", list);
		dis = req.getRequestDispatcher("S_Reportlist.jsp"); //S_Reportlist.jsp로 이동
		dis.forward(req, resp);//값보냄
	}
	

}
