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
처리요청 : 판매자 문의내역 리스트, 판매자 문의 답변하기폼, 답변하기, 판매자 문의 상세보기
           구매자 문의내역 리스트, 구매자 문의내역 상세보기
*/
@WebServlet({"/Seller/sQAList","/Seller/sAnswerForm","/Seller/sAnswerDetail" ,"/Seller/sAnswer"
	,"/Consumer/cQuestionList", "/Consumer/cQuestionDetail" , "/ServiceCenter/qList", "/ServiceCenter/qWrite", "/ServiceCenter/qDetail"})
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
		case "/Seller/sAnswerForm":
			System.out.println("판매자 문의내역 답변하기 폼 요청");
			service.sAnswerForm();
			break;
		case "/Seller/sAnswer":
			System.out.println("판매자 문의내역 답변하기");
			service.sAnswer();
			break;
		case "/Seller/sAnswerDetail":
			System.out.println("판매자 문의내역 상세보기 요청");
			service.sAnswerDetail();
			break;
		
		case "/Consumer/cQuestionList":
			System.out.println("Requset Customer QuestionList");
			service.cQuestionList();
			break;
		case "/Consumer/cQuestionDetail":
			System.out.println("Request Customer QuestionDetail");
			service.cQuestionDetail();
			break;
		case "/ServiceCenter/qList":
			System.out.println("Request Question List ");
			service.qList();
			break;
	
		case "/ServiceCenter/qWrite":
			System.out.println("Request Question Write");
			service.qWrite();
			break;
			
		case "/ServiceCenter/qDetail":
			System.out.println("Request Question Detail");
			service.qDetail();
		}
		
	}
	
}
