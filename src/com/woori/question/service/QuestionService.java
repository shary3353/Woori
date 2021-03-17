package com.woori.question.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.product.dao.ProductDAO;
import com.woori.product.dto.ProductDTO;
import com.woori.question.dao.QuestionDAO;
import com.woori.question.dto.QuestionDTO;
import com.woori.wish.dao.WishDAO;

public class QuestionService {

	// 공통
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;

	public QuestionService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void sQAList() throws ServletException, IOException {// 판매자 문의 내역 리스트
		// 로그인검사 추가예정
		req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		/*
		 * if(sid != null) {//로그인 여부 판별
		 * 
		 * } else { //로그인을 안 했으면 로그인페이지로 resp.sendRedirect("판매자로그인.jsp"); }
		 */
		System.out.println("판매자" + sid + "의 문의 내역입니다."); // 로그인한 아이디 확인& 판매자 확인

		String pageParam = req.getParameter("page");
		System.out.println("page : " + pageParam);

		int group = 1;
		if (pageParam != null) {
			group = Integer.parseInt(pageParam);
		}

		QuestionDAO dao = new QuestionDAO();
		HashMap<String, Object> map = dao.sQAList(sid, group);

		req.setAttribute("maxPage", map.get("maxPage"));
		req.setAttribute("list", map.get("list"));
		req.setAttribute("currPage", group);

		dis = req.getRequestDispatcher("S_QnAList.jsp"); // S_QnAList.jsp로 이동
		dis.forward(req, resp);// 값보냄
	}

	public void cQuestionList() throws ServletException, IOException {
		// String loginId = (String) req.getParameter().getAttribute("loginId");
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String cid = (String) req.getSession().getAttribute("loginId");
		String loginId = cid;
		String msg = "";
		if (loginId != null) {
			System.out.println(cid + " 의 문의내역 불러오기");

			String pageParam = req.getParameter("page");
			System.out.println("이동하고 싶은 page : " + pageParam);
			int group = 1;
			if (pageParam != null) {
				group = Integer.parseInt(pageParam);
			}

			QuestionDAO dao = new QuestionDAO();
			HashMap<String, Object> map = dao.cQuestionList(group, cid);

			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);

			RequestDispatcher dis = req.getRequestDispatcher("./C_QuestionList.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}

	}

	public void cQuestionDetail() throws ServletException, IOException {
		// String loginId = (String) req.getParameter().getAttribute("loginId");
		req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String cid = (String) req.getSession().getAttribute("loginId");
		String loginId = cid;
		String msg = "";
		if (loginId != null) {
			String q_idx = req.getParameter("q_idx");
			System.out.println(q_idx + "번 문의 상세보기 요청");
			QuestionDAO dao = new QuestionDAO();
			QuestionDTO dto = dao.cQuestionDetail(q_idx);

			req.setAttribute("list", dto);
			RequestDispatcher dis = req.getRequestDispatcher("../ServiceCenter/Q_detail.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("");
			dis.forward(req, resp);
		}

	}

	public void qList() throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String pageParam = req.getParameter("page");
		System.out.println("Request page : " + pageParam);
		QuestionDAO dao = new QuestionDAO();
		int group = 1;

		if (pageParam != null) {
			group = Integer.parseInt(pageParam);
		}
		map = dao.q_list(group);
		req.setAttribute("maxPage", map.get("maxPage"));
		req.setAttribute("list", map.get("list"));
		req.setAttribute("currPage", group);

		dis = req.getRequestDispatcher("/ServiceCenter/Q_list.jsp");
		dis.forward(req, resp);
	}

	public void sAnswerDetail() throws ServletException, IOException { // 판매자 문의내역 상세보기
		// 로그인검사 추가예정
		req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		/*
		 * if(sid != null) {//로그인 여부 판별
		 * 
		 * } else { //로그인을 안 했으면 로그인페이지로 resp.sendRedirect("판매자로그인.jsp"); }
		 */
		int q_idx = Integer.parseInt(req.getParameter("q_idx"));
		System.out.println("상세보기할 문의 p_idx :" + q_idx);

		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = dao.sAnswerDetail(q_idx);
		req.setAttribute("dto", dto);
		dis = req.getRequestDispatcher("./S_answer.jsp");
		dis.forward(req, resp);

	}

	public void sAnswerForm() throws ServletException, IOException { // 판매자 문의답변하기 폼
		// 로그인검사 추가예정
		req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		/*
		 * if(sid != null) {//로그인 여부 판별
		 * 
		 * } else { //로그인을 안 했으면 로그인페이지로 resp.sendRedirect("판매자로그인.jsp"); }
		 */
		int q_idx = Integer.parseInt(req.getParameter("q_idx"));
		System.out.println("상세보기할 문의 p_idx :" + q_idx);

		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = dao.sAnswerDetail(q_idx);
		req.setAttribute("dto", dto);
		dis = req.getRequestDispatcher("./S_noAnswer.jsp");
		dis.forward(req, resp);
	}

	public void sAnswer() throws IOException { // 판매자 문의답변하기
		// 로그인검사 추가예정
		req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		/*
		 * if(sid != null) {//로그인 여부 판별
		 * 
		 * } else { //로그인을 안 했으면 로그인페이지로 resp.sendRedirect("판매자로그인.jsp"); }
		 */
		int q_idx = Integer.parseInt(req.getParameter("q_idx"));
		String s_answer = req.getParameter("s_answer");
		System.out.println("문의번호/답변 : " + q_idx + "/" + s_answer);

		QuestionDAO dao = new QuestionDAO();
		dao.sAnswer(q_idx, s_answer);

		resp.sendRedirect("./sAnswerDetail?q_idx=" + q_idx);

	}

	public void qWrite() throws ServletException, IOException {
		String sId = req.getParameter("sId");
		String cId = req.getParameter("cId");
		String category = req.getParameter("category");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String pass = req.getParameter("passWord");
		System.out.println(sId + "/" + cId + "/" + category + "/" + subject + "/" + content + "/" + pass);
		QuestionDAO dao = new QuestionDAO();
		long q_idx = 0;
		if (sId != null && cId != null && category != null && subject != null && content != null && pass != null) {

			q_idx = dao.qWrite(sId, cId, Integer.parseInt(category), subject, content, Integer.parseInt(pass));
		}
		String msg = "문의 등록의 실패하였습니다.";
		String page = "Q_write.jsp";
		if (q_idx > 0) {
			msg = "문의가 성공적으로 완료되었습니다	.";
			page = "Q_detail?q_idx=" + q_idx;

		}

		req.setAttribute("msg", msg);
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

}
