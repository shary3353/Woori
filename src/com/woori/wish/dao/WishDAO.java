package com.woori.wish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.product.dto.ProductDTO;
import com.woori.wish.dto.WishDTO;

public class WishDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public WishDAO() {//db접근
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void resClose() { //자원 반납
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<WishDTO> wishList(String cid) {
		String sql = "SELECT w.wish_idx, w.p_idx, p.p_name, p.sid, p.p_price, p.fileidx, w.w_date FROM wishlist w, product p WHERE w.cid=? and w.p_idx = p.p_idx(+) ORDER BY w_date DESC";
		 ArrayList<WishDTO> list = new ArrayList<WishDTO>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			while(rs.next()) {
				WishDTO dto = new WishDTO();
				dto.setWish_idx(rs.getInt(1));
				dto.setP_idx(rs.getString(2));
				dto.setP_name(rs.getString(3));
				dto.setSid(rs.getString(4));
				dto.setP_price(rs.getInt(5));
				dto.setFileIdx(6);
				list.add(dto);
			}
			System.out.println("위시리스트 데이터 수 : "+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return list;
	}
	public int delWishList(String wish_idx) {
		String sql = "DELETE wishlist WHERE wish_idx=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, wish_idx);
			if(ps.executeUpdate() > 0) {
				success = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		if(success > 0) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		return success;
	}

}
