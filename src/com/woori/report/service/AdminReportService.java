package com.woori.report.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.woori.report.dao.AdminReportDAO;

public class AdminReportService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public AdminReportService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	
	public void rList() throws ServletException, IOException {
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
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
		dis.forward(req, resp);
	}

    public void rSearch() throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		
        String inputR = req.getParameter("rSearch");
        String rSearchOption = req.getParameter("rSearchOption");
        
        if(rSearchOption.equals("100")) {                //신고자ID 검색
            System.out.println("검색할 신고자 id : "+inputR);
            AdminReportDAO dao = new AdminReportDAO();
            HashMap<String, Object> map = dao.rReporterSearch(inputR, group);
            req.setAttribute("rList", map.get("rList"));
            req.setAttribute("currPage", group);
            req.setAttribute("maxReportPage", map.get("maxReportPage"));
        }else if(rSearchOption.equals("200")) {    //신고대상자ID 검색
            System.out.println("검색할 신고대상자 id : "+inputR);
            AdminReportDAO dao = new AdminReportDAO();
            HashMap<String, Object> map = dao.rTargetSearch(inputR, group);
            req.setAttribute("rList", map.get("rList"));
            req.setAttribute("currPage", group);
            req.setAttribute("maxReportPage", map.get("maxReportPage"));
        }
        RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
        dis.forward(req, resp);
    }


	public void rStatusUpdate() throws ServletException, IOException {
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
	}


}
