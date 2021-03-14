package com.woori.product.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori.product.dao.ProductDAO;
import com.woori.product.dto.ProductDTO;

public class ProductService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	RequestDispatcher dis = null;
	
	public ProductService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void sItemList() throws ServletException, IOException {//판매자 등록물품리스트 요청
		//로그인검사 추가예정
		req.getSession().setAttribute("loginId","123-12-12345");//test용
		String sid = (String)req.getSession().getAttribute("loginId");
		System.out.println("판매자"+ sid +"의 물품 리스트입니다."); //로그인한 아이디 확인& 판매자 확인
		
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> list = dao.sItemList(sid); //ArrayList(여러개의 dto)
		System.out.println(list); //리스트 확인
		System.out.println(list.size()); //리스트 사이즈 확인
		
		req.setAttribute("list", list);
		dis = req.getRequestDispatcher("S_ItemManage.jsp"); //S_ItemManage.jsp로 이동
		dis.forward(req, resp);//값보냄
	}

	public void sItemDetail() throws ServletException, IOException {//판매자 등록뭎룸 상세보기
		//로그인검사 추가예정
		int p_idx = Integer.parseInt(req.getParameter("p_idx"));//물품번호
		System.out.println("상세보기할 물품 번호 : " +p_idx);
		
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = dao.sItemDetail(p_idx);//물품정보
		System.out.println("상세보기 dto : "+dto);
		dao = new ProductDAO();
		int r_count = dao.sItemRcount(p_idx);//예약수
		
		req.setAttribute("dto", dto);
		req.setAttribute("r_count", r_count);
		dis = req.getRequestDispatcher("S_goodsInfo.jsp"); //S_goodsInfo.jsp로 이동
		dis.forward(req, resp);//값보냄
	}
	
}
