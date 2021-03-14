package com.woori.question.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.product.dao.ProductDAO;
import com.woori.product.dto.ProductDTO;
import com.woori.question.dao.QuestionDAO;
import com.woori.question.dto.QuestionDTO;

public class QuestionService {
	
	//공통
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;
	
	public QuestionService(HttpServletRequest req, HttpServletResponse resp) {
		this.req =req;
		this.resp =resp;
	}

	public void sQAList() throws ServletException, IOException {//판매자 문의 내역 리스트
		//로그인검사 추가예정
		req.getSession().setAttribute("loginId","123-12-12345");//test용
		String sid = (String)req.getSession().getAttribute("loginId");
		System.out.println("판매자"+ sid +"의 문의 내역입니다."); //로그인한 아이디 확인& 판매자 확인
		
		QuestionDAO dao = new QuestionDAO();
		ArrayList<QuestionDTO> list = dao.sQAList(sid);
		System.out.println(list); //리스트 확인
		System.out.println("문의리스트 수:" + list.size()); //리스트 사이즈 확인
		
		req.setAttribute("list", list);
		dis = req.getRequestDispatcher("S_QnAList.jsp"); //S_QnAList.jsp로 이동
		dis.forward(req, resp);//값보냄
	}

}
