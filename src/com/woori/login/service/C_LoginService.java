package com.woori.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.woori.login.dao.C_LoginDAO;

public class C_LoginService {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public C_LoginService(HttpServletRequest req, HttpServletResponse resp) {
		
		this.req = req;
		this.resp = resp;
	}

	public boolean C_LoginService() {
		C_LoginDAO dao = new C_LoginDAO();
		String cid =req.getParameter("cId");
		String pw = req.getParameter("Pw");
		System.out.println(cid+"/"+pw);
		return dao.clogin(cid,pw);

	}

}
