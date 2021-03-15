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

	public void report() throws ServletException, IOException {
		String subejct = req.getParameter("subject");
		String reporter_id = req.getParameter("userName");
		String target_id = req.getParameter("sellerId");
		String category = req.getParameter("categorie");
		String content = req.getParameter("content");
		
		System.out.println(subejct + "/" + reporter_id + "/" + target_id + "/" + category + "/" + content  );
		ReportDTO dto = new ReportDTO();
		dto.setSubject(subejct);
		dto.setReporter_id(reporter_id);
		dto.setTarget_id(target_id);
		dto.setCategory(category);
		dto.setContent(content);
		String page = "Service/report.jsp";
		String msg = "신고 등록에 실패하였습니다.";
		ReportDAO dao = new ReportDAO();
		long idx = dao.report(dto);
			if(idx >0) {
				page = "Service/reportDetail?idx="+idx;
			}
		
		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
		
	}

	public void detail() throws ServletException, IOException {
		String idx = req.getParameter("idx");
		
		ReportDAO dao = new ReportDAO();
		ReportDTO dto = new ReportDTO();
		dto = dao.detail(idx);
		
		req.setAttribute("dto", dto);
		dis = req.getRequestDispatcher("ServiceCenter/ReportDetail.jsp");
		dis.forward(req, resp);
		
	}
	

}
