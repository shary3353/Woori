package com.woori.member.controller;

import java.io.IOException ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.woori.member.service.MemberService;
/*처리 : (회원가입,중복확인, 로그인, 회원수정, 회원상세보기, 회원리스트)
 	구매.판매.관리자 로그인,로그아웃,구매.판매.관리자회원가입, ~리스트
	,판매자회원상세보기, 판매자 회원정보수정폼 보기, 판매자 회원정보 수정하기
 */
@WebServlet({"/Consumer/cLogin", "/Seller/sLogin", "/AdminLogin", "/Consumer/overlay" ,"/logout", "/cJoin", "/sJoin", "/Admin/cList", "/Admin/sList"
	, "/Seller/sPfpDetail","/Seller/sPfpUpdateForm","/Seller/sPfpUpdate", "/Admin/cSearch", "/Admin/sSearch", "/Admin/sDetail", "/Admin/cDetail"
	, "/Consumer/cDetail", "/Consumer/cUpadateForm", "/Consumer/cUpdateInfo"
	,"/Seller/soverlay"})
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logout, list
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("get request url : "+sub);
		
		MemberService service = new MemberService(req, resp);
		
		switch(sub) {
			case "/Admin/cList":
				System.out.println("Request Customer List");
				service.cList();
				break;
			case "/Admin/sList":
				System.out.println("Request Seller List");
				service.sList();
				break;
            case "/Admin/cSearch":
                System.out.println("Request Customer Search");
                service.cSearch();
                break;
            case "/Admin/sSearch":
                System.out.println("Request Seller Search");
                service.sSearch();
                break;
            case "/Admin/cDetail":
                System.out.println("Request Customer Detail");
                service.AdminCustomerDetail();
                break;
            case "/Admin/sDetail":
                System.out.println("Request Seller Detail");
                service.AdminSellerDetail();
                break;
			
            case "/logout":
				String loginID = (String)req.getSession().getAttribute("loginID");
				System.out.println("지워질 세션ID: "+ loginID);
				req.getSession().removeAttribute("loginID");
				resp.sendRedirect("index.jsp");
				break;
			
		}
		
		dual(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//join, login
		req.setCharacterEncoding("UTF-8");
		
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("post request url : "+sub);
		
		MemberService service = new MemberService(req, resp);
		
	
		
		RequestDispatcher dis = null;
		
		switch(sub) {
		
		case "/Consumer/cLogin":
			System.out.println("Request Customer Login");
			service. C_Loginid();
			break;
		
		case "/Seller/sLogin":
			System.out.println("Request Seller Login");
			service.S_Loginid();
			break;
			
		case "/AdminLogin":
			System.out.println("Request Admin Login");
			service.Admin_Login();
			break;
			
		case "/cJoin":
			System.out.println("Request Customer Join");
			service.cjoin();
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
		
		case"/Consumer/overlay"://중복체크요청
			System.out.println("중복체크 요청");
			service.overlay();
			break;
		
		case"/Seller/soverlay"://중복체크요청
			System.out.println("중복체크 요청");
			service.overlay();
			break;
		}
	}
	}
	

			
			
		
