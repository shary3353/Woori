package com.woori.main.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.main.dao.MainDAO;

public class MainService {
	
	//자주쓰는것들 인자값 선언
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;
	
	public MainService(HttpServletRequest req , HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void Cmain() throws ServletException, IOException {
		HashMap<String, Object> map = null;
		MainDAO dao = new MainDAO();
		for (int i = 1; i < 7; i++) {
			map = new HashMap<String, Object>();
			map = dao.cmain(i);
			System.out.println(map);
			req.setAttribute("list"+i, map.get("list"+i));
		}
		dao.resClose();
		dis = req.getRequestDispatcher("Consumer/C_main.jsp");
		dis.forward(req, resp);
		
	}

}
