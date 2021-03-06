package com.woori.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.service.MemberService;
import com.woori.product.service.ProductService;

/*
처리요청 : 판매자등록물품리스트,판매자물품상세보기, 판매자 물품 등록하기, 판매자 물품수정폼 보이기, 물품 수정하기, 물품삭제하기(판매여부바꾸기)
*/
@WebServlet({"/Seller/sItemList","/Seller/sItemDetail","/Seller/registItem","/Seller/sUpdateItemForm","/Seller/sUpdateItem"
	,"/Seller/sDeleteItem"})
public class ProductController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("get request url : "+sub);
		
		dual(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("post request url : "+sub);
		
		ProductService service = new ProductService(req, resp);
		
		switch(sub) {
		
		case "/Seller/sUpdateItemForm":
			System.out.println("판매자 물품 수정 폼 보이기");
			service.sUpdateItemForm();
			break;
			
		case "/Seller/sDeleteItem":
			System.out.println("판매자 물품 삭제하기 - 판매여부변경");
			service.sDeleteItem();
			break;
		
		}
		
		dual(req,resp);
	}
	
	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("dual request url : "+sub);
		
		ProductService service = new ProductService(req, resp);
		
		switch(sub) {
		case "/Seller/sItemList":
			System.out.println("판매자 등록물품 리스트 요청");
			service.sItemList();
			break;
			
		case "/Seller/sItemDetail":
			System.out.println("판매자 등록물품 상세보기 요청");
			service.sItemDetail();
			break;
		
		case "/Seller/registItem":
			System.out.println("판매자 물품 등록하기");
			service.registItem();
			break;
		
		case "/Seller/sUpdateItem":
			System.out.println("판매자 물품 수정 하기");
			service.sUpdateItem();
			break;
			
			
		}
		
	}
	
}
