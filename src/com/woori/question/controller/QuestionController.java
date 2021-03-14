package com.woori.question.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.product.service.ProductService;
import com.woori.question.service.QuestionService;
/*
처리요청 : 판매자 문의내역 리스트
*/
@WebServlet({"/Seller/sQAList"})
public class QuestionController extends HttpServlet {

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
		
		QuestionService service = new QuestionService(req, resp);
		switch(sub) {
		case "/Seller/sQAList":
			System.out.println("판매자 문의내역 리스트 요청");
			service.sQAList();
			break;
			
		
		}
		
	}
	
}
