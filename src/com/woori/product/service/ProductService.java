package com.woori.product.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("sLoginID");
		if(sid != null) {//로그인 여부 판별
			
			System.out.println("판매자"+ sid +"의 물품 리스트입니다."); //로그인한 아이디 확인& 판매자 확인
			
			String pageParam = req.getParameter("page");
			System.out.println("page : "+pageParam);
			
			int group=1;
			if(pageParam != null) {
				group = Integer.parseInt(pageParam);
			}
			
			ProductDAO dao = new ProductDAO();
			HashMap<String, Object> map = dao.sItemList(sid, group); //ArrayList(여러개의 dto)
			
			req.setAttribute("maxPage", map.get("maxPage"));
			req.setAttribute("list", map.get("list"));
			req.setAttribute("currPage", group);
			
			dis = req.getRequestDispatcher("S_ItemManage.jsp"); //S_ItemManage.jsp로 이동
			dis.forward(req, resp);//값보냄
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void sItemDetail() throws ServletException, IOException {//판매자 등록뭎룸 상세보기
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("sLoginID");
		if(sid != null) {//로그인 여부 판별
			
			int p_idx = Integer.parseInt(req.getParameter("p_idx"));//물품번호
			System.out.println("상세보기할 물품 번호 : " +p_idx);
			
			ProductDAO dao = new ProductDAO();
			ProductDTO dto = dao.sItemDetail(p_idx);//물품정보
			System.out.println("상세보기 dto : "+dto);
			
			String page = "sItemList";//실패시
			String msg = "본인이 등록한 물품 이외에는 볼 수 없습니다.";
			String ckSid = dto.getSid();
			System.out.println(sid+"=="+ckSid);
			if(sid.equals(ckSid)) {//등록된물품의 판매자와 현재 로그인된 판매자가 동일한지 검사 -성공시
				System.out.println("로그인된판매자와 해당물품의 판매자가 일치합니다.");
				dao = new ProductDAO();
				int r_count = dao.sItemRcount(p_idx);//예약수
				req.setAttribute("dto", dto);				
				req.setAttribute("r_count", r_count);
				page="S_goodsInfo.jsp";//S_goodsInfo.jsp로 이동
				msg="";
			}
			req.setAttribute("msg", msg);
			dis = req.getRequestDispatcher(page); 
			dis.forward(req, resp);//값보냄
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void registItem() throws ServletException, IOException {// 판매자 물품 등록하기
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("sLoginID");
		if(sid != null) {//로그인 여부 판별
		
			// FileService 에 파일과 관련된 내용을 추가 예정
			FileService upload = new FileService(req); //FileService 에 req 보내서 객체 생성.
			ProductDTO dto = upload.regist();//파일 등록(받은 파라메터와 업로드 파일 정보 반환)
			System.out.println(dto.getOriFileName()+"=>"+dto.getNewFileName());
			//DB 저장 (작성자, 제목, 내용 + 파일 이름)
			ProductDAO dao = new ProductDAO();
			
			String page="sItemList";
			
			long p_idx = dao.registItem(dto); //dto 보냄(받은 파라메터와 업로드 파일 정보)
			if(p_idx>0) {//0보다 크면 성공
				page="sItemDetail?p_idx="+p_idx; //detail로 수정
			}
			
			dis = req.getRequestDispatcher(page);
			dis.forward(req, resp);
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void sUpdateItemForm() throws ServletException, IOException {//등록물품 수정하기 폼 보이기
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("sLoginID");
		if(sid != null) {//로그인 여부 판별
			
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
			
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void sUpdateItem() throws IOException { //물품상세보기 수정
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("sLoginID");
		if(sid != null) {//로그인 여부 판별
		
			FileService upload = new FileService(req);//파일처리객체생성
			ProductDTO dto = upload.regist(); //수정할 정보 추출 + 파일 업로드 여부
			//product
			ProductDAO dao = new ProductDAO();
			dao.sUpdateItem(dto); //db처리 요청 - product
			
			if(dto.getOriFileName()!=null) { //2. 업로드 파일이 있는지 확인
				//3. 업로드 파일이 있다면... 기존 파일 지우기, 새로운 내용을 thumbFile 에 update
				int p_idx = dto.getP_idx();
				//3-1. 이전파일 이름 가져오기(그래야 지울 수 있으니깐....)
				dao= new ProductDAO();
				String delFileName = dao.getFileName(p_idx);
				System.out.println("삭제할 파일명 : "+delFileName);
				
				//이전파일 이름 -> 새파일 이름으로 변경...
				dao= new ProductDAO();
				dao.updateFileName(delFileName, dto);
				
				//파일 삭제
				if(delFileName!=null) {
					upload.delete(delFileName);
				}
				
			}	
			resp.sendRedirect("sItemDetail?p_idx="+dto.getP_idx());
			
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
	}

	public void sDeleteItem() throws IOException { //판매자 상품 판매중지
		//로그인검사 추가됨.
		//req.getSession().setAttribute("loginID","123-12-12345");//test용 -- 로그인
		String sid = (String)req.getSession().getAttribute("sLoginID");
		if(sid != null) {//로그인 여부 판별
		
			int p_idx = Integer.parseInt(req.getParameter("p_idx"));
			System.out.println("판매중지할 p_idx = "+p_idx);
			ProductDAO dao = new ProductDAO();
			
			dao.sDeleteItem(p_idx);
			resp.sendRedirect("sItemList");
		
		} else { //로그인을 안 했으면 로그인페이지로
			resp.sendRedirect("../Consumer/C_login.jsp");
		}
		
	}
	
}
