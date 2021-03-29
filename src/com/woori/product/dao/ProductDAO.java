package com.woori.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.product.dto.ProductDTO;

public class ProductDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ProductDAO() { //DB
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resClose() {//자원반납
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Object> sItemList(String sid, int page) { //판매자 등록물품리스트 - 판매중인 상품만 :판매-1
		
		//페이징처리
		int pagePerCnt = 10;//한페이지 개수
		int end = page *pagePerCnt;//페이지 끝
		int start = end-(pagePerCnt-1);//페이지 시작
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		//
		ArrayList<ProductDTO> list =new ArrayList<ProductDTO>();
		
		String sql = "SELECT sid, p_idx, category, p_name, p_content, p_price, oriFileName, newFileName\r\n" + 
				"    FROM (SELECT ROW_NUMBER() OVER(ORDER BY p.p_idx DESC) AS rnum, p.sid, p.p_idx, c.category, p.p_name, p.p_content, p.p_price, th.oriFileName, th.newFileName " + 
					"		FROM product p, thumbfile th, categories c WHERE p.p_idx = th.p_idx(+) AND p.c_idx = c.c_idx AND p.sid=? AND p.is_sold=1)" + 
				"    WHERE rnum BETWEEN ? AND ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setCategory(rs.getString("category"));
				dto.setP_name(rs.getString("p_name"));
				dto.setP_content(rs.getString("p_content"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setOriFileName(rs.getString("oriFileName"));
				dto.setNewFileName(rs.getString("newFileName"));
				list.add(dto);
			}
			System.out.println("물품리스트 수:" + list.size()); //리스트 사이즈 확인
			int maxPage = getMaxPageSellerProduct(sid ,pagePerCnt);
			map.put("list",list);
			map.put("maxPage", maxPage);
			System.out.println("max page : "+maxPage);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}
	private int getMaxPageSellerProduct(String sid, int pagePerCnt) {//판매자 물품리스트 최대페이지
		String sql = "SELECT COUNT (p_idx) FROM (SELECT p.SID, p.p_idx, c.category, p.p_name, p.p_content, p.p_price, th.oriFileName, th.newFileName \r\n" + 
				"		FROM product p, thumbfile th, categories c WHERE p.p_idx = th.p_idx(+) AND p.c_idx = c.c_idx "
					+ "AND p.sid=? AND p.is_sold=1)";
		int max=0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				max = (int)Math.ceil(cnt/(double)pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}

	public ProductDTO sItemDetail(int p_idx) {//판매자 물품상세보기
		String sql = "SELECT p.p_idx, p.sid, c.category, c.c_idx, p.p_name, p.p_content, p.p_price, th.oriFileName, th.newFileName" + 
				" FROM product p, thumbfile th, categories c WHERE p.p_idx = th.p_idx(+) AND p.c_idx = c.c_idx AND p.p_idx = ?";
		
		ProductDTO dto = new ProductDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setSid(rs.getString("sid"));
				dto.setCategory(rs.getString("category"));
				dto.setP_name(rs.getString("p_name"));
				dto.setP_content(rs.getString("p_content"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setOriFileName(rs.getString("oriFileName"));
				dto.setNewFileName(rs.getString("newFileName"));
				dto.setC_idx(rs.getInt("c_idx"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}
	
	public int sItemRcount(int p_idx) { //해당 물품 예약수
		String sql = "SELECT COUNT(*) count FROM reservation WHERE p_idx=?";
		int r_count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				r_count = rs.getInt("count");
				System.out.println("예약수 : "+ r_count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return r_count;
	}

	public long registItem(ProductDTO dto) {
		//1. bbs 에 데이터 넣기
		String sql ="INSERT INTO product(p_idx, p_name, p_content, p_price, c_idx, sid)" + 
				"         VALUES (product_seq.nextval,?,?,?,?,?)";
		long p_idx = 0; //pk 초기값 - 0
		
		try {
			//new String[] {"반환받을 컬럼명"}
			//ojdbc8 버전 이하에서는 동작하지 않는다.
			ps = conn.prepareStatement(sql, new String[] {"p_idx"}); //p_idx 반환 받을 거임.
			ps.setString(1, dto.getP_name());//상품명
			ps.setString(2, dto.getP_content());//상품설명
			ps.setInt(3, dto.getP_price());//가격
			ps.setInt(4, dto.getC_idx());//상품카테고리c_idx
			ps.setString(5, dto.getSid());//판매자
			ps.executeUpdate(); //쿼리실행
			
			//방금 실행된 INSERT 문의 p_idx 가져오기
			rs = ps.getGeneratedKeys(); //
			if(rs.next()) {//값이 있는가?
				p_idx = rs.getLong(1); //p_idx 값넣기
				System.out.println("생성 p_idx :"+p_idx);//2. thumbFile 에 데이터 넣기(product 에서 가져온 p_idx를 추가)
				
				if(dto.getOriFileName() != null ) { //파일 업로드가 된 경우에만 실행 -- 받은 dto에 원본파일명이 있느지 체크
					sql = "INSERT INTO thumbfile(fileidx, OriFileName, NewFileName, p_idx)"
							+ "VALUES(thumbfile_seq.nextval,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, dto.getOriFileName());//원본파일이름
					ps.setString(2, dto.getNewFileName());//새파일이름
					ps.setLong(3, p_idx); //(product 에서 가져온 p_idx를 추가)
					ps.executeUpdate();//쿼리실행
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//자원반납.
			resClose();
		}
		return p_idx; //pk -- product로 부터 받은 p_idx 반환
	}

	public String getFileName(int p_idx) { // 파일이름 추출 - 삭제할
		String newFileName =null; //파일이 없다면 null
		String sql = "SELECT newFileName FROM thumbfile WHERE p_idx=?";//특정 p_idx의 newFileName 추출
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_idx);
			rs = ps.executeQuery();
			if(rs.next()) { // 값이 있는가?
				newFileName = rs.getString("newFileName"); //파일이름 넣어줌
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return newFileName; //파일이름 반환
	}

	public int sUpdateItem(ProductDTO dto) {//product 테이블 수정
		String sql = "UPDATE product SET p_name=?, c_idx=?, p_price=?, p_content=? WHERE p_idx=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getP_name());
			ps.setInt(2, dto.getC_idx());
			ps.setInt(3, dto.getP_price());
			ps.setString(4, dto.getP_content());
			ps.setInt(5, dto.getP_idx());
			success =ps.executeUpdate();
			System.out.println("업데이트 완료된 갯수 : "+success);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return success;
	}
	
	public int updateFileName(String delFileName, ProductDTO dto) { //thumbFile 파일정보수정 
		String sql = "";
		int success = 0;
		try {
			if(delFileName != null) { //기존파일이 있다.
				sql = "UPDATE thumbfile SET newFileName=?, oriFileName=? WHERE p_idx=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, dto.getNewFileName());//?처리
				ps.setString(2, dto.getOriFileName());
				ps.setInt(3, dto.getP_idx());
			} else { //신규 업로드
				sql = "INSERT INTO thumbfile(fileidx, OriFileName, NewFileName, p_idx)"
						+ "VALUES(thumbfile_seq.nextval,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, dto.getOriFileName());//원본파일이름
				ps.setString(2, dto.getNewFileName());//새파일이름
				ps.setInt(3, dto.getP_idx());//(product 에서 가져온 p_idx를 추가)
			}			
			success = ps.executeUpdate();//쿼리실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		System.out.println(sql+" 성공여부 :"+success);
		return success;
	}

	public int sDeleteItem(int p_idx) {//판매중지 0
		String sql = "UPDATE product SET is_sold=0 WHERE p_idx=?";
		
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_idx);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		System.out.println("판매중지 성공여부 :"+success);
		return success;
	}
	
}
