package com.woori.wish.serivce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

	public void wishPaging() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("cLoginID");
		String msg = "";
		if (loginID != null) {
			System.out.println(loginID + " 의 위시리스트 불러오기");
			String pageParam = req.getParameter("page");
			System.out.println("이동하고 싶은 page : " + pageParam);
			int group = 1;
			if (pageParam != null) {
				group = Integer.parseInt(pageParam);
			}
			WishDAO dao = new WishDAO();
			HashMap<String, Object> map = dao.pagingList(group, loginID);
			
			req.setAttribute("msg", req.getAttribute("msg"));
			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);

			RequestDispatcher dis = req.getRequestDispatcher("./C_WishList.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("./C_login.jsp");
			dis.forward(req, resp);
		}

	}

	public void delWishList() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("cLoginID");
		//req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String msg = "";
		if (loginID != null) {
			String wish_idx = req.getParameter("wish_idx");
			System.out.println("삭제하고 싶은 WishList idx : " + wish_idx);
			WishDAO dao = new WishDAO();
			int success = dao.delWishList(wish_idx);
			if(success>0) {
				msg = wish_idx+"번 위시리스트가 삭제되었습니다.";
			}
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("wishPaging");
			dis.forward(req, resp);
			
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("./C_login.jsp");
			dis.forward(req, resp);
		}
	}

	public void addWishList() throws ServletException, IOException {

		String loginID = (String) req.getSession().getAttribute("cLoginID");
		//req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String msg = "";
		boolean success = false;
		if (loginID != null) {
			String p_idx = req.getParameter("p_idx");
			System.out.println("Add Wish List p_idx : " + p_idx);
			WishDAO dao = new WishDAO();
			success = dao.chkWishList(p_idx, loginID);
			if(success) {				
				dao.addWishList(p_idx, loginID);
				dao.resClose();
				req.setAttribute("p_idx", p_idx);
				RequestDispatcher dis = req.getRequestDispatcher("wishPaging?page=1");
				dis.forward(req, resp);
			} else {
				msg = "이미 위시리스트에 추가된 상품입니다.";
				dao.resClose();
				req.setAttribute("msg", msg);
				RequestDispatcher dis = req.getRequestDispatcher("C_itemDetail?p_idx="+p_idx);
				dis.forward(req, resp);
			}
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("C_login.jsp");
			dis.forward(req, resp);
		}
	}

}
