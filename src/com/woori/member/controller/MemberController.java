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
	,판매자회원상세보기, 판매자 회원정보수정폼 보기, 판매자 회원정보 수정하기
 */
@WebServlet({"/cLogin", "/sLogin", "/adminLogin","/logout", "/cJoin", "/sJoin", "/cList", "/sList", "/bList", "/rList"
	, "/Seller/sPfpDetail","/Seller/sPfpUpdateForm","/Seller/sPfpUpdate", "/cSearch", "/sSearch", "/bSearch", "/rSearch"
	, "/Consumer/cDetail", "/Consumer/cUpadateForm", "/Consumer/cUpdateInfo", "/Consumer/wishList"})
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logout, list
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("get request url : "+sub);
		
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
			case "/bList":
				System.out.println("Request Seller List");
				service.bList();
				break;
			case "/rList":
				System.out.println("Request Seller List");
				service.rList();
				break;
            case "/cSearch":
                System.out.println("Request Customer Search");
                service.cSearch();
                break;
            case "/sSearch":
                System.out.println("Request Seller Search");
                service.sSearch();
                break;
            case "/bSearch":
                System.out.println("Request BlackList Search");
                service.bSearch();
                break;
            case "/rSearch":
                System.out.println("Request Report Search");
                service.rSearch();
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
		System.out.println("post request url : "+sub);
		
		MemberService service = new MemberService(req, resp);
		
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
		case "/Consumer/cUpadateForm":
			System.out.println("Requset Customer UpdateForm");
			service.cUpdateForm();
			break;
		case "/Consumer/cUpdateInfo":
			System.out.println("Requset Customer UpdateInfo");
			service.cUpdateInfo();
			break;

		}
		
		dual(req,resp);
	}

	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("dual request url : "+sub);
		
		MemberService service = new MemberService(req, resp);
		
		switch(sub) {
		case "/Seller/sPfpDetail": //판매자 회원 상세보기 요청 
			System.out.println("Request  seller profile Detail");
			service.sPfpDatail();
			break;
			
		case "/Seller/sPfpUpdateForm": //판매자 회원정보 수정보기 요청
			System.out.println("Request  seller profile UpdateForm");
			service.sPfpUpdateForm();
			break;
		
		case "/Seller/sPfpUpdate": //판매자 회원정보 수정 요청
			System.out.println("Request  seller profile Update");
			service.sPfpUpdate();
			break;
		case "/Consumer/cDetail":
			System.out.println("Request Custumer Info");
			service.cDetail();
			break;
		case "/Consumer/wishList":
			System.out.println("Request Custumer WishList");
			break;
		}
	}
	
}
