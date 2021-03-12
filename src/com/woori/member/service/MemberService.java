package com.woori.member.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.dao.CustomerDAO;
import com.woori.member.dao.SellerDAO;
import com.woori.member.dto.CustomerDTO;
import com.woori.member.dto.SellerDTO;

public class MemberService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public MemberService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void cList() {
		CustomerDAO dao = new CustomerDAO();
		ArrayList<CustomerDTO> cList = dao.cList();
		req.setAttribute("cList", cList);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_Customerlist");
	}

	public void sList() throws ServletException, IOException {
		SellerDAO dao = new SellerDAO();
		ArrayList<SellerDTO> sList = dao.sList();
		req.setAttribute("sList", sList);
		RequestDispatcher dis = req.getRequestDispatcher("Admin/admin_SellerList.jsp");
		dis.forward(req, resp);
	}
	
	

}
