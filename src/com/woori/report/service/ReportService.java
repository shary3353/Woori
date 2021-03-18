package com.woori.report.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.dao.ListDAO;
import com.woori.report.dao.AdminReportDAO;
import com.woori.report.dao.ReportDAO;
import com.woori.report.dto.ReportDTO;
import com.woori.wish.dao.WishDAO;

public class ReportService {

	// 공통
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;

	public ReportService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void sReportList() throws ServletException, IOException { // 판매자 신고내역 리스트
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
			
			System.out.println("판매자" + sid + "의 신고 내역입니다."); // 로그인한 아이디 확인& 판매자 확인
	
			String pageParam = req.getParameter("page");
			System.out.println("page : " + pageParam);
	
			int group = 1;
			if (pageParam != null) {
				group = Integer.parseInt(pageParam);
			}
	
			ReportDAO dao = new ReportDAO();
			HashMap<String, Object> map = dao.sReportList(sid, group);
	
			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);
	
			dis = req.getRequestDispatcher("S_Reportlist.jsp"); // S_Reportlist.jsp로 이동
			dis.forward(req, resp);// 값보냄
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void report() throws ServletException, IOException {
		String subejct = req.getParameter("subject");
		String reporter_id = req.getParameter("userName");
		String target_id = req.getParameter("sellerId");
		String category = req.getParameter("categorie");
		String content = req.getParameter("content");

		System.out.println(subejct + "/" + reporter_id + "/" + target_id + "/" + category + "/" + content);
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
		if (idx > 0) {
			page = "ReportDetail?idx=" + idx;
		}

		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);

	}

	public void detail() throws ServletException, IOException {
		String idx = req.getParameter("idx");
		System.out.println("idx : " + idx);
		ReportDAO dao = new ReportDAO();
		ReportDTO dto = new ReportDTO();
		dto = dao.detail(idx);

		req.setAttribute("dto", dto);
		dis = req.getRequestDispatcher("ReportDetail.jsp");
		dis.forward(req, resp);

	}

	public void cReportList() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			System.out.println(loginID + " 의 신고내역 불러오기");
			String pageParam = req.getParameter("page");
			System.out.println("이동하고 싶은 page : " + pageParam);
			int group = 1;
			if (pageParam != null) {
				group = Integer.parseInt(pageParam);
			}
			ReportDAO dao = new ReportDAO();
			HashMap<String, Object> map = dao.cReportList(group, loginID);
			
			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);
			
			RequestDispatcher dis = req.getRequestDispatcher("./C_ReportList.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}
	}

	public void sReportDetail() throws ServletException, IOException {//판매자 신고상세보기
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
			
			String idx = req.getParameter("idx");
			System.out.println("idx : " + idx);
			ReportDAO dao = new ReportDAO();
			ReportDTO dto = new ReportDTO();
			dto = dao.detail(idx);
	
			req.setAttribute("dto", dto);
			dis = req.getRequestDispatcher("S_ReportDetail.jsp");
			dis.forward(req, resp);
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void cReportDetail() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			String r_idx = req.getParameter("r_idx");
			System.out.println(r_idx +"번 신고 상세보기");
			ReportDAO dao = new ReportDAO();
			ReportDTO dto = dao.detail(r_idx);
			
			req.setAttribute("dto", dto);
			dis = req.getRequestDispatcher("../ServiceCenter/ReportDetail.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}
		
	}

	public void sReportForm() throws ServletException, IOException { //판매자 신고하기 폼 보여주기
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
			
			String target_id = req.getParameter("target_id");
			System.out.println("신고대상자 id :" +target_id);
			req.setAttribute("target_id", target_id);
			dis = req.getRequestDispatcher("./S_Report.jsp");
			dis.forward(req, resp);
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void sReport() throws ServletException, IOException {//판매자가 신고하기
		//로그인검사 추가예정
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
		
			String subejct = req.getParameter("subject");
			String reporter_id = req.getParameter("reporter_id");
			String target_id = req.getParameter("target_id");
			String category = req.getParameter("category");
			String content = req.getParameter("content");
			System.out.println("신고내용: "+subejct + "/" + reporter_id + "/" + target_id + "/" + category + "/" + content);
			
			ReportDTO dto = new ReportDTO();
			dto.setSubject(subejct);
			dto.setReporter_id(reporter_id);
			dto.setTarget_id(target_id);
			dto.setCategory(category);
			dto.setContent(content);
			
			ReportDAO dao = new ReportDAO();
			long r_idx = dao.report(dto);
			
			String page = "sReportForm?target_id"+target_id;
			if (r_idx > 0) {
				page = "sReportDetail?idx="+r_idx;
			}
	
			dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void cReportForm() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			String target_id = req.getParameter("t_id");
			System.out.println("신고자 : " + loginID);
			System.out.println("신고 대상자 : " + target_id);
			
			ReportDTO dto = new ReportDTO();
			dto.setReporter_id(loginID);
			dto.setTarget_id(target_id);
			
			req.setAttribute("dto", dto);
			dis = req.getRequestDispatcher("../ServiceCenter/Report.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}
		
	}

}
