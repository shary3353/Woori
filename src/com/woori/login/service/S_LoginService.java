package com.woori.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.login.dao.S_LoginDAO;

public class S_LoginService {

	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public S_LoginService(HttpServletRequest req, HttpServletResponse resp) {
	
		this.req = req;
		this.resp = resp;
	}

	public boolean S_LoginService() {
		S_LoginDAO dao = new S_LoginDAO();
		String sid =req.getParameter("sId");
		String pw = req.getParameter("pw");
		System.out.println(sid+"/"+pw);
		return dao.slogin(sid,pw);
	}
		
	}



