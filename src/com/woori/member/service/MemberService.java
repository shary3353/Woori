package com.woori.member.service;

import java.io.IOException;
import java.util.ArrayList;

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
		ListDAO dao = new ListDAO();
		ArrayList<ReportListDTO> rList = new ArrayList<>();
		rList = dao.rList();
		
		req.setAttribute("rList", rList);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
		dis.forward(req, resp);
	}

	public void cSearch() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String inputC = req.getParameter("cSearch");
		System.out.println("검색할 구매자 : "+inputC);
		
		ListDAO dao = new ListDAO();
		ArrayList<CustomerListDTO> searchedC = dao.cSearch(inputC);
		
		req.setAttribute("cList", searchedC);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_CustomerList.jsp");
		dis.forward(req, resp);
	}

	public void sSearch() throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		String inputS = req.getParameter("sSearch");
		System.out.println("검색할 판매자 : "+inputS);
		
		ListDAO dao = new ListDAO();
		ArrayList<SellerListDTO> searchedS = dao.sSearch(inputS);
		
		req.setAttribute("sList", searchedS);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_SellerList.jsp");
		dis.forward(req, resp);
	}

	public void bSearch() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String inputB = req.getParameter("bSearch");
		String bSearchOption = req.getParameter("bSearchOption");
		
		if(bSearchOption.equals("100")) {				//판매자 검색
			System.out.println("검색할 판매자 id : "+inputB);
			ListDAO dao = new ListDAO();
			ArrayList<BlackListDTO> searchedB = dao.bSellerSearch(inputB);
			req.setAttribute("bList", searchedB);
		}else if(bSearchOption.equals("200")) {	//구매자 검색
			System.out.println("검색할 구매자 id : "+inputB);
			ListDAO dao = new ListDAO();
			ArrayList<BlackListDTO> searchedB = dao.bCustomerSearch(inputB);
			req.setAttribute("bList", searchedB);
		}
		
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_BlackList.jsp");
		dis.forward(req, resp);
	}

	public void rSearch() throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String inputR = req.getParameter("rSearch");
		String rSearchOption = req.getParameter("rSearchOption");
		
		if(rSearchOption.equals("100")) {				//신고자ID 검색
			System.out.println("검색할 신고자 id : "+inputR);
			ListDAO dao = new ListDAO();
			ArrayList<ReportListDTO> searchedR = dao.rReporterSearch(inputR);
			req.setAttribute("rList", searchedR);
		}else if(rSearchOption.equals("200")) {	//신고대상자ID 검색
			System.out.println("검색할 신고대상자 id : "+inputR);
			ListDAO dao = new ListDAO();
			ArrayList<ReportListDTO> searchedR = dao.rTargetSearch(inputR);
			req.setAttribute("rList", searchedR);
		}
		
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_ReportList.jsp");
		dis.forward(req, resp);
		
	}


}
