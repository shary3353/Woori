package com.woori.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.dao.ListDAO;
import com.woori.member.dto.BlackListDTO;
import com.woori.member.dto.CustomerDTO;
import com.woori.member.dto.CustomerListDTO;
import com.woori.member.dto.ReportListDTO;
import com.woori.member.dto.SellerDTO;
import com.woori.member.dto.SellerListDTO;

public class MemberService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public MemberService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void cList() throws ServletException, IOException {
		ListDAO dao = new ListDAO();
		ArrayList<CustomerListDTO> cList = new ArrayList<>();
		cList = dao.cList();
		
		req.setAttribute("cList", cList);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_CustomerList.jsp");
		dis.forward(req, resp);
	}

	public void sList() throws ServletException, IOException {
		ListDAO dao = new ListDAO();
		ArrayList<SellerListDTO> sList = new ArrayList<>();
		sList = dao.sList();
		
		req.setAttribute("sList", sList);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_SellerList.jsp");
		dis.forward(req, resp);
	}

	public void blackList() throws ServletException, IOException {
		ListDAO dao = new ListDAO();
		ArrayList<BlackListDTO> bList = new ArrayList<>();
		bList = dao.bList();
		
		req.setAttribute("bList", bList);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_BlackList.jsp");
		dis.forward(req, resp);
	}

	public void reportList() throws ServletException, IOException {
		int group = 1;
		String page = req.getParameter("page");
		
		if(page != null) {
			group = Integer.parseInt(page);
		}
		
		ListDAO dao = new ListDAO();
		HashMap<String, Object> map = dao.rList(group);
		
		req.setAttribute("rList", map.get("rList"));
		req.setAttribute("currPage", group);
		req.setAttribute("maxReportPage", map.get("maxReportPage"));
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
		dis.forward(req, resp);
	}


}
