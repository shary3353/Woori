package com.woori.login.service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.woori.login.dao.A_LoginDAO;


public class A_LoginService {


	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public A_LoginService (HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		
		
	}
	
	public boolean login() {
		A_LoginDAO dao = new A_LoginDAO();
		String id =req.getParameter("userId");
		String pw = req.getParameter("userPw");
		System.out.println(id+"/"+pw);
		return dao.login(id,pw);
	}
	
	
	
}
