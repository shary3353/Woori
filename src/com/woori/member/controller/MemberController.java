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
		//회원수정, 회원상세보기, 회원리스트
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("요청 url : "+sub);
		
		MemberService service = new MemberService(req, resp);
		
		switch(sub) {
			case "/cList":
				System.out.println("구매자 리스트 요청");
				//service.cList();
				break;
			case "/sList":
				System.out.println("판매자 리스트 요청");
				service.sList();
				break;
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원가입, 로그인
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("요청 url : "+sub);
		
		switch(sub) {
		case "/cLogin":
			System.out.println("구매자 로그인 요청");
			break;
		case "/sLogin":
			System.out.println("판매자 로그인 요청");
			break;
		case "/adminLogin":
			System.out.println("관리자 로그인 요청");
			break;
		case "/logout":
			System.out.println("로그아웃 요청");
			break;
		case "/cJoin":
			System.out.println("구매자 회원가입 요청");
			break;
		case "/sJoin":
			System.out.println("판매자 회원가입 요청");
			break;
			
		}
	}
	
}
