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

	public void cQuestionList() throws ServletException, IOException {
			req.getSession().setAttribute("loginId", "test1"); // 테스트용
			String cid = (String) req.getSession().getAttribute("loginId");
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

	}

	public void cQuestionDetail() throws ServletException, IOException {
		String q_idx = req.getParameter("q_idx");
		System.out.println(q_idx +"번 문의 상세보기 요청");
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto =  dao.cQuestionDetail(q_idx);
		
		req.setAttribute("list", dto);
		RequestDispatcher dis = req.getRequestDispatcher("../ServiceCenter/Q_detail.jsp");
		dis.forward(req, resp);
		
	}

	public void q_list() throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		String pageParam = req.getParameter("page");
		System.out.println("Request page : "+ pageParam);
		QuestionDAO dao = new QuestionDAO();
		int group = 1;
		
		if(pageParam != null) {
			group = Integer.parseInt(pageParam);
		}
		map = dao.q_list(group);
		req.setAttribute("maxPage", map.get("maxPage"));
		req.setAttribute("list", map.get("list"));
		req.setAttribute("currPage", group);
		
		dis = req.getRequestDispatcher("/ServiceCenter/Q_list.jsp");
		dis.forward(req, resp);
	}

	public void sAnswerDetail() throws ServletException, IOException { //판매자 문의내역 상세보기
		int q_idx = Integer.parseInt(req.getParameter("q_idx"));
		System.out.println("상세보기할 문의 p_idx :"+q_idx);
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = dao.sAnswerDetail(q_idx);
		req.setAttribute("dto", dto);
		dis = req.getRequestDispatcher("./S_answer.jsp");
		dis.forward(req, resp);
		
	}
	public void sAnswerForm() throws ServletException, IOException { //판매자 문의답변하기 폼
		// TODO Auto-generated method stub
		int q_idx = Integer.parseInt(req.getParameter("q_idx"));
		System.out.println("상세보기할 문의 p_idx :"+q_idx);
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = dao.sAnswerDetail(q_idx);
		req.setAttribute("dto", dto);
		dis = req.getRequestDispatcher("./S_noAnswer.jsp");
		dis.forward(req, resp);
	}

	public void sAnswer() throws IOException { //판매자 문의답변하기
		int q_idx = Integer.parseInt(req.getParameter("q_idx"));
		String s_answer = req.getParameter("s_answer");
		System.out.println("문의번호/답변 : " +q_idx+"/"+s_answer);
		
		QuestionDAO dao = new QuestionDAO();
		dao.sAnswer(q_idx, s_answer);
		
		resp.sendRedirect("./sAnswerDetail?q_idx="+q_idx);
		
	}


}
