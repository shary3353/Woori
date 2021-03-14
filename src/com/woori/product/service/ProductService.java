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
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> list = dao.sItemList(); //ArrayList(여러개의 dto)
		System.out.println(list); //리스트 확인
		System.out.println(list.size()); //리스트 사이즈 확인
		
		req.setAttribute("list", list);
		dis = req.getRequestDispatcher("S_ItemManage.jsp"); //S_ItemManage.jsp로 이동
		dis.forward(req, resp);//값보냄
	}
	
}
