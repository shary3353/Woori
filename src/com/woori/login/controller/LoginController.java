package com.woori.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.login.service.A_LoginService;
import com.woori.login.service.C_LoginService;
import com.woori.login.service.S_LoginService;


@WebServlet({"/clogin","/slogin","/adminlogin"})

public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		System.out.println("요청 확인");
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("post request url : "+sub);
		
		A_LoginService Aservice = new A_LoginService(req,resp);
		S_LoginService Sservice = new S_LoginService(req,resp);
		C_LoginService Cservice = new C_LoginService(req,resp);
		
		
		RequestDispatcher dis = null;
		
		switch(sub) {
		case"/clogin":
			System.out.println("로그인 요청");
			boolean success = Cservice.C_LoginService();
			System.out.println("로그인 결과 : "+success);
			
			String page ="C_login.jsp";
			String msg ="아이디와 비밀번호를 확인해 주세요.";
			
			if (success) {//로그인 성공시..
				String cId = req.getParameter("cid");
				page = "C_main";
				msg = cId+"님, 반갑습니다.";
				req.getSession().setAttribute("cId",cId);
			}
			req.setAttribute("msg", msg);
			dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);
			
			break;
		
		case"/slogin":
			System.out.println("로그인 요청");
			boolean Ssuccess = Sservice.S_LoginService();
			System.out.println("로그인 결과 : "+Ssuccess);
			
			String spage ="S_LoginService.jsp";
			String smsg ="아이디와 비밀번호를 확인해 주세요.";
			
			if (Ssuccess) {//로그인 성공시..
				String sId = req.getParameter("sid");
				spage = "S_main";
				smsg = sId+"님, 반갑습니다.";
				req.getSession().setAttribute("sId",sId);
			}
			req.setAttribute("msg", smsg);
			dis = req.getRequestDispatcher(spage);
			dis.forward(req, resp);
			
			break;
		
		case"/adminlogin":
			System.out.println("로그인 요청");
			boolean asuccess = Aservice.login();
			System.out.println("로그인 결과 : "+asuccess);
			
			String apage ="admin_Login.jsp";
			String amsg ="아이디와 비밀번호를 확인해 주세요.";
			
			if (asuccess) {//로그인 성공시..
				String aId = req.getParameter("aid");
				page = "admin_main";
				msg = aId+"님, 반갑습니다.";
				req.getSession().setAttribute("aId",aId);
			}
			req.setAttribute("msg", amsg);
			dis = req.getRequestDispatcher(apage);
			dis.forward(req, resp);
			
			break;
	}

	}
}
