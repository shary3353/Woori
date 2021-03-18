package com.woori.report.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.woori.report.dao.AdminReportDAO;
import com.woori.report.dto.ReportDTO;

public class AdminReportService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public AdminReportService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	
	public void rList() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			int group = 1;
			String page = req.getParameter("page");
			
			if(page != null) {
				group = Integer.parseInt(page);
			}
			
			AdminReportDAO dao = new AdminReportDAO();
			HashMap<String, Object> map = dao.rList(group);
			
			req.setAttribute("rList", map.get("rList"));
			req.setAttribute("maxReportPage", map.get("maxReportPage"));
			req.setAttribute("currPage", group);
			RequestDispatcher dis = req.getRequestDispatcher("admin_ReportList.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}

    public void rSearch() throws ServletException, IOException {
    	String loginID = (String)req.getSession().getAttribute("loginID");
    	if(loginID != null) {
    		req.setCharacterEncoding("utf-8");
    		int group = 1;
    		String page = req.getParameter("page");
    		
    		if(page != null) {
    			group = Integer.parseInt(page);
    		}
    		
    		String inputR = req.getParameter("inputR");
    		String rSearchOption = req.getParameter("rSearchOption");
    		
    		System.out.println(inputR+" / "+group+" / "+rSearchOption);
    		
    		if(rSearchOption.equals("100")) {                //신고자ID 검색
    			System.out.println("검색할 신고자 id : "+inputR);
    			AdminReportDAO dao = new AdminReportDAO();
    			HashMap<String, Object> map = dao.rReporterSearch(inputR, group);
    			req.setAttribute("rList", map.get("rList"));
    			req.setAttribute("currPage", group);
    			req.setAttribute("maxReportPage", map.get("maxReportPage"));
    			req.setAttribute("inputR", inputR);
    			req.setAttribute("rSearchOption", rSearchOption);
    		}else if(rSearchOption.equals("200")) {    //신고대상자ID 검색
    			System.out.println("검색할 신고대상자 id : "+inputR);
    			AdminReportDAO dao = new AdminReportDAO();
    			HashMap<String, Object> map = dao.rTargetSearch(inputR, group);
    			req.setAttribute("rList", map.get("rList"));
    			req.setAttribute("currPage", group);
    			req.setAttribute("maxReportPage", map.get("maxReportPage"));
    			req.setAttribute("inputR", inputR);
    			req.setAttribute("rSearchOption", rSearchOption);
    		}
    		RequestDispatcher dis = req.getRequestDispatcher("admin_ReportList.jsp");
    		dis.forward(req, resp);
    	}else {
    		resp.sendRedirect("./admin_Login.jsp");
    	}
    }


	public void rStatusUpdate() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			HashMap<String, Object> map = new HashMap<>();
			String r_idx = req.getParameter("r_idx");
			String rStatus = req.getParameter("rStatus");
			System.out.println(r_idx+" / "+rStatus);
			
			AdminReportDAO dao = new AdminReportDAO();
			boolean success = dao.rStatusUpdate(r_idx, rStatus);
			if(success) {
				System.out.println("해당 신고의 상태를 수정하였습니다.");
				map.put("updateReport", success);
				Gson gson = new Gson();
				String json = gson.toJson(map);
				System.out.println(json);
				resp.getWriter().print(json);
			}
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}


	public void rDetail() throws ServletException, IOException {
		String loginID = (String)req.getSession().getAttribute("loginID");
		if(loginID != null) {
			req.setCharacterEncoding("utf-8");
			int r_idx = Integer.parseInt(req.getParameter("r_idx"));
			System.out.println("요청받은 신고 번호 : "+r_idx);
			AdminReportDAO dao = new AdminReportDAO();
			ReportDTO selectedReport = dao.getReport(r_idx);
			if(selectedReport != null) {
				req.setAttribute("selectedReport", selectedReport);
			}
			RequestDispatcher dis = req.getRequestDispatcher("admin_ReportDetail.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("./admin_Login.jsp");
		}
	}


	
	
	
	
	
	
	
	
	
	
	
}
