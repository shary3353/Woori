package com.woori.wish.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.member.service.MemberService;
import com.woori.wish.serivce.WishService;

@WebServlet({"/Consumer/wishDel", "/Consumer/wishPaging"})
public class WishController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dual(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dual(req,resp);
	}

	private void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("get request url : "+sub);
		
		WishService service = new WishService(req, resp);
		
		switch (sub) {
		case "/Consumer/wishPaging":
			System.out.println("Request Paging Service");
			service.wishPaging();
			break;
		case "/Consumer/wishDel":
			System.out.println("Requset Delete Wishlist");
			service.delWishList();
			break;
		}
		
	}

}
