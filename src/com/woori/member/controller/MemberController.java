package com.woori.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.service.MemberService;

@WebServlet({"/cLogin", "/sLogin", "/adminLogin","/logout", "/cJoin", "/sJoin", "/cList", "/sList"})
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logout, list
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("request url : "+sub);
		
		MemberService service = new MemberService(req, resp);
		
		switch(sub) {
			case "/cList":
				System.out.println("Request Customer List");
				//service.cList();
				break;
			case "/sList":
				System.out.println("Request Seller List");
				service.sList();
				break;
			case "/logout":
				System.out.println("Request Logout");
				break;
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//join, login
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("request url : "+sub);
		
		switch(sub) {
		case "/cLogin":
			System.out.println("Request Customer Login");
			break;
		case "/sLogin":
			System.out.println("Request Seller Login");
			break;
		case "/adminLogin":
			System.out.println("Request Admin Login");
			break;
		case "/cJoin":
			System.out.println("Request Customer Join");
			break;
		case "/sJoin":
			System.out.println("Request Seller Join");
			break;
			
		}
	}
	
}
