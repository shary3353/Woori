package com.woori.reservation.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.woori.reservation.dao.ReservationDAO;
import com.woori.reservation.dto.ReservationDTO;
import com.woori.wish.dao.WishDAO;
import com.woori.wish.dto.WishDTO;

public class ReservationService {

	// 공통
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;

	public ReservationService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void sReservationList() throws ServletException, IOException { // 판매자 예약내역 리스트
		// 로그인검사 추가됨.
		// req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if (sid != null) {// 로그인 여부 판별

			System.out.println("판매자" + sid + "의 예약 내역입니다."); // 로그인한 아이디 확인& 판매자 확인

			String pageParam = req.getParameter("page");
			System.out.println("page : " + pageParam);

			int group = 1;
			if (pageParam != null) {
				group = Integer.parseInt(pageParam);
			}

			ReservationDAO dao = new ReservationDAO();
			HashMap<String, Object> map = dao.sReservationList(sid, group);

			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);

			dis = req.getRequestDispatcher("S_Reservationlist.jsp"); // S_Reservationlist.jsp로 이동
			dis.forward(req, resp);// 값보냄

		} else { // 로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void updateResevationStatus() throws ServletException, IOException {// 판매자 예약현황변경
		// 로그인검사 추가됨.
		// req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if (sid != null) {// 로그인 여부 판별

			System.out.println("판매자" + sid + "가 예약 현황을 변경합니다."); // 로그인한 아이디 확인& 판매자 확인

			int r_idx = Integer.parseInt(req.getParameter("r_idx"));
			int rs_idx = Integer.parseInt(req.getParameter("status"));
			System.out.println("변경예약:" + r_idx + "/" + rs_idx);

			HashMap<String, Object> map = new HashMap<String, Object>();
			boolean success = false;

			ReservationDAO dao = new ReservationDAO();
			if (dao.updateResevationStatus(r_idx, rs_idx) > 0) {// 성공시
				success = true;
				dao = new ReservationDAO();
				String status = dao.getNewReservationStatus(r_idx);// 새상태
				map.put("update", success);
				map.put("status", status);
				Gson gson = new Gson();
				String json = gson.toJson(map);
				System.out.println(json);
				resp.setContentType("text/html charset-UTF-8");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().print(json);
			}

		} else { // 로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void cReservationList() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			System.out.println(loginID + " 의 예약내역 불러오기");
			String pageParam = req.getParameter("page");
			System.out.println("이동하고 싶은 page : " + pageParam);
			int group = 1;
			if (pageParam != null) {
				group = Integer.parseInt(pageParam);
			}
			ReservationDAO dao = new ReservationDAO();
			HashMap<String, Object> map = dao.cReservationList(group, loginID);
			
			req.setAttribute("msg", req.getAttribute("msg"));
			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);

			RequestDispatcher dis = req.getRequestDispatcher("./C_ReservationList.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("./C_login.jsp");
			dis.forward(req, resp);
		}

	}

	public void cMyPageMain() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			ReservationDAO dao = new ReservationDAO();
			ArrayList<ReservationDTO> rList = dao.mainReservationList(loginID);

			WishDAO wdao = new WishDAO();
			ArrayList<WishDTO> wList = wdao.mainWishList(loginID);

			req.setAttribute("rList", rList);
			req.setAttribute("wList", wList);
			RequestDispatcher dis = req.getRequestDispatcher("./C_MyPageMain.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("./C_login.jsp");
			dis.forward(req, resp);
		}

	}

	public void cReservationCancle() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			String r_idx = req.getParameter("r_idx");
			int success = 0;
			ReservationDAO dao = new ReservationDAO();
			success = dao.cReservationCancle(r_idx);

			if(success>0) {
				msg = r_idx+"번 예약이 취소신청 되었습니다.";
			}
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("cReservationList");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("./C_login.jsp");
			dis.forward(req, resp);
		}

	}

	public void cReservationDelete() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		String msg = "";
		if (loginID != null) {
			String r_idx = req.getParameter("r_idx");
			int success = 0;
			ReservationDAO dao = new ReservationDAO();
			success = dao.cReservationDelete(r_idx);

			resp.sendRedirect("cReservationList");

		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("./C_login.jsp");
			dis.forward(req, resp);
		}

	}

}
