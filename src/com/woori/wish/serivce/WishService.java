package com.woori.wish.serivce;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.wish.dao.WishDAO;
import com.woori.wish.dto.WishDTO;

public class WishService {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public WishService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	public void wishList() throws ServletException, IOException {
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String cid = (String) req.getSession().getAttribute("loginId");
		System.out.println(cid + " 의 위시리스트 불러오기");
		
		WishDAO dao = new WishDAO();
		ArrayList<WishDTO> list = new ArrayList<WishDTO>();
		list = dao.wishList(cid);
		if(list.size()>0) {
			req.setAttribute("list", list);
		}
		RequestDispatcher dis = req.getRequestDispatcher("./C_WishList.jsp");
		dis.forward(req, resp);
	}
}
