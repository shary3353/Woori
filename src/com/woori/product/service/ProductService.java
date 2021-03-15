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
		System.out.println("물품리스트 수:" + list.size()); //리스트 사이즈 확인
		
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

	public void registItem() throws ServletException, IOException {// 판매자 물품 등록하기
		String loginId = (String) req.getSession().getAttribute("loginId");//로그인 체크 추가예정
		
		// FileService 에 파일과 관련된 내용을 추가 예정
		FileService upload = new FileService(req); //FileService 에 req 보내서 객체 생성.
		ProductDTO dto = upload.regist();//파일 등록(받은 파라메터와 업로드 파일 정보 반환)
		System.out.println(dto.getOriFileName()+"=>"+dto.getNewFileName());
		//DB 저장 (작성자, 제목, 내용 + 파일 이름)
		ProductDAO dao = new ProductDAO();
					
		String page = "writeForm.jsp";//실패시
					
		long p_idx = dao.registItem(dto); //글쓰기db처리요청- dto 보냄(받은 파라메터와 업로드 파일 정보)
		if(p_idx>0) {//반환 받은 pk 의 값 == idx번호 0보다 크면 성공
			page="sItemList?p_idx="+p_idx; //list 요청-- detail로 수정예정
		}
		
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}

	public void sUpdateItemForm() throws ServletException, IOException {
		int p_idx = Integer.parseInt(req.getParameter("p_idx"));
		System.out.println("업데이트폼 p_idx = "+p_idx);
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = dao.sItemDetail(p_idx); //볼 정보가져오기
		String page = "/sItemList"; //실패시
		if(dto != null) { //성공시
			page = "S_goodsRevise.jsp";
			req.setAttribute("dto", dto); //${dto}
		}
		dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	}
	
}
