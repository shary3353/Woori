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
import com.google.gson.Gson;
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
		// 로그인검사 추가됨.
		//req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
			
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
			
		} else { //로그인을 안 했으면 로그인페이지로 
			resp.sendRedirect("../Consumer/C_login.jsp"); 
		}
	}

	public void cQuestionList() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		//req.getSession().setAttribute("loginId", "test1"); // 테스트용
		String msg = "";
		if (loginID != null) {
			System.out.println(loginID + " 의 문의내역 불러오기");

			String pageParam = req.getParameter("page");
			System.out.println("이동하고 싶은 page : " + pageParam);
			int group = 1;
			if (pageParam != null) {
				group = Integer.parseInt(pageParam);
			}

			QuestionDAO dao = new QuestionDAO();
			HashMap<String, Object> map = dao.cQuestionList(group, loginID);

			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);

			RequestDispatcher dis = req.getRequestDispatcher("./C_QuestionList.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("../Consumer/C_login.jsp");
			dis.forward(req, resp);
		}

	}

	public void cQuestionDetail() throws ServletException, IOException {
		String loginID = (String) req.getSession().getAttribute("loginID");
		//req.getSession().setAttribute("loginId", "test1"); // 테스트용

		String msg = "";
		if (loginID != null) {
			String q_idx = req.getParameter("q_idx");
			System.out.println(q_idx + "번 문의 상세보기 요청");
			QuestionDAO dao = new QuestionDAO();
			QuestionDTO dto = dao.cQuestionDetail(q_idx);

			req.setAttribute("list", dto);
			RequestDispatcher dis = req.getRequestDispatcher("../Consumer/C_QuestionDetail.jsp");
			dis.forward(req, resp);
		} else {
			msg = "로그인을 해주세요.";
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("../Consumer/C_login.jsp");
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
		// 로그인검사 추가됨.
		//req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
		  
			int q_idx = Integer.parseInt(req.getParameter("q_idx"));
			System.out.println("상세보기할 문의 p_idx :" + q_idx);
	
			QuestionDAO dao = new QuestionDAO();
			QuestionDTO dto = dao.sAnswerDetail(q_idx);
			req.setAttribute("dto", dto);
			dis = req.getRequestDispatcher("./S_answer.jsp");
			dis.forward(req, resp);
		
		} else { //로그인을 안 했으면 로그인페이지로 
			  resp.sendRedirect("../Consumer/C_login.jsp"); 
		}

	}

	public void sAnswerForm() throws ServletException, IOException { // 판매자 문의답변하기 폼
		// 로그인검사 추가됨.
		//req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
		
			int q_idx = Integer.parseInt(req.getParameter("q_idx"));
			System.out.println("상세보기할 문의 p_idx :" + q_idx);
	
			QuestionDAO dao = new QuestionDAO();
			QuestionDTO dto = dao.sAnswerDetail(q_idx);
			req.setAttribute("dto", dto);
			dis = req.getRequestDispatcher("./S_noAnswer.jsp");
			dis.forward(req, resp);
		
		} else { //로그인을 안 했으면 로그인페이지로 
			resp.sendRedirect("../Consumer/C_login.jsp"); 
		}
	}

	public void sAnswer() throws IOException { // 판매자 문의답변하기
		// 로그인검사 추가됨.
		//req.getSession().setAttribute("loginID", "123-12-12345");// test용 -- 로그인
		String sid = (String) req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
		 
			int q_idx = Integer.parseInt(req.getParameter("q_idx"));
			String s_answer = req.getParameter("s_answer");
			System.out.println("문의번호/답변 : " + q_idx + "/" + s_answer);
	
			QuestionDAO dao = new QuestionDAO();
			dao.sAnswer(q_idx, s_answer);
	
			resp.sendRedirect("./sAnswerDetail?q_idx=" + q_idx);
			
		  } else { //로그인을 안 했으면 로그인페이지로 
			  resp.sendRedirect("../Consumer/C_login.jsp"); 
		}
	}

	public void qWrite() throws ServletException, IOException {
		String sid = (String) req.getSession().getAttribute("loginID");
		if(sid != null) {//로그인 여부 판별
		String sId = req.getParameter("sid");
		String cId = req.getParameter("cid");
		String category = req.getParameter("category");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String p_name = req.getParameter("product");
		String pass = req.getParameter("pass");
		if(pass == null) {
			pass = "";
		}
		System.out.println(sId + "/" + cId + "/" + category + "/" + subject + 
				"/" + content + "/" + pass + "/" + p_name);	
		QuestionDAO dao = new QuestionDAO();
		boolean success = false;
		HashMap<String, Object> map = new HashMap<String, Object>();
		long q_idx = dao.qWrite(sId,cId,Integer.parseInt(category),subject,content,Integer.parseInt(pass),p_name);
		if(q_idx > 0 ) {
			success = true;
			System.out.println("성공");
		}
		map.put("success", success);
		map.put("q_idx" , q_idx);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println(json);
		
		resp.getWriter().print(json);
		
		} else { //로그인을 안 했으면 로그인페이지로 
			resp.sendRedirect("../Consumer/C_login.jsp"); 
		}
	}

	public void qDetail() throws ServletException, IOException {
		String q_idx = req.getParameter("q_idx");
		String q_pw = req.getParameter("password");
		
		System.out.println("q_idx :" + q_idx + "/" + "q_pw :" + q_pw);
		boolean success = false;
		QuestionDAO dao = new QuestionDAO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = dao.qDetail(q_idx,q_pw);
		success  = (boolean) map.get("success");
		System.out.println("성공 비밀번호 : " + success);
		String msg= "비밀번호가 틀렸습니다.";
		String page ="qList";
		if(success) {
				msg ="";
				page = "Q_detail.jsp";
			
		}
		
		req.setAttribute("msg",msg);
		req.setAttribute("list", map.get("dto"));
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

}