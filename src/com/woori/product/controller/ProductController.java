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
처리요청 : 판매자등록물품리스트,
*/
@WebServlet({"/Seller/sItemList"})
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
		
		}
		
	}
	
}
