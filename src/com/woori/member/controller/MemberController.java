package com.woori.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.service.MemberService;
/*처리 : (회원가입, 로그인, 회원수정, 회원상세보기, 회원리스트)
 	구매.판매.관리자 로그인,로그아웃,구매.판매.관리자회원가입, ~리스트
	,판매자회원상세보기
 */
@WebServlet({"/cLogin", "/sLogin", "/adminLogin","/logout", "/cJoin", "/sJoin", "/cList", "/sList", "/blackList", "/reportList"
	, "/sPfpDetail"})
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
				service.cList();
				break;
			case "/sList":
				System.out.println("Request Seller List");
				service.sList();
				break;
			case "/blackList":
				System.out.println("Request Seller List");
				service.blackList();
				break;
			case "/reportList":
				System.out.println("Request Seller List");
				service.reportList();
				break;
			case "/logout":
				System.out.println("Request Logout");
				break;
			
		}
		
		dual(req,resp);
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
		
		dual(req,resp);
	}

	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("request url : "+sub);
		switch(sub) {
		
		case "/sPfpDetail":
			System.out.println("Request  seller profile Detail");
			break;
			
		}
	}
	
}
