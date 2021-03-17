package com.woori.black.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.black.service.BlackService;

@WebServlet({ "/bList", "/bSearch", "/cBlackRegist", "/sBlackRegist", "/blackUpdate" })
public class BlackController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dual(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dual(req, resp);
	}

	public void dual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sub = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("get request url : " + sub);

		BlackService service = new BlackService(req, resp);

		switch (sub) {
		case "/bList":
			System.out.println("Request Customer List");
			service.bList();
			break;
		case "/bSearch":
			System.out.println("Request BlackList Search");
			service.bSearch();
			break;
		case "/sBlackRegist":
			System.out.println("Request Seller BlackList Search");
			service.sBlackRegist();
			break;
		case "/cBlackRegist":
			System.out.println("Request Customer BlackList Search");
			service.cBlackRegist();
			break;
		case "/blackUpdate":
			System.out.println("Request BlackList Update");
			service.blackCancel();
			break;
		}

	}

}
