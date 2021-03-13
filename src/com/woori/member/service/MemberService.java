package com.woori.member.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.dao.ListDAO;
import com.woori.member.dto.CustomerDTO;
import com.woori.member.dto.CustomerListDTO;
import com.woori.member.dto.SellerDTO;

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


	
	

}
