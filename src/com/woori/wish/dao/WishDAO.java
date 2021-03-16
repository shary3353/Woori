package com.woori.wish.dao;

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
import com.woori.wish.dto.WishDTO;

public class WishDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public WishDAO() {// db접근
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resClose() { // 자원 반납
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Object> pagingList(int group, String cid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pagePerCnt = 3;
		int end = group * pagePerCnt;
		int start = end - (pagePerCnt - 1);
		System.out.println(start + " ~ " + end + "까지의 리스트");
		ArrayList<WishDTO> list = new ArrayList<WishDTO>();
		String sql = "SELECT w.wish_idx, w.p_idx, p.p_name, p.sid, p.p_price, p.newfilename\r\n" + 
				"    FROM (SELECT ROW_NUMBER() OVER(ORDER BY wish_idx DESC) AS rnum,wish_idx,p_idx,w_date,cid FROM wishlist) w, (SELECT p.p_idx, p.p_name, p.p_price, p.sid, t.newfilename FROM product p, thumbfile t WHERE p.p_idx=t.p_idx) p\r\n" + 
				"    WHERE w.cid=? and w.p_idx = p.p_idx(+) and rnum BETWEEN ? AND ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				WishDTO dto = new WishDTO();
				dto.setWish_idx(rs.getInt(1));
				dto.setP_idx(rs.getString(2));
				dto.setP_name(rs.getString(3));
				dto.setSid(rs.getString(4));
				dto.setP_price(rs.getInt(5));
				dto.setNewFileName(rs.getString(6));
				dto.setPhotoPath("photo/"+dto.getNewFileName());
				list.add(dto);
				System.out.println(dto.getNewFileName());
			}
			System.out.println("위시리스트 데이터 수 : " + list.size());
			int maxPage = getMaxPage(pagePerCnt, cid);
			map.put("list", list);
			map.put("maxPage", maxPage);
			System.out.println("max page : " + maxPage);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}

	private int getMaxPage(int pagePerCnt, String cid) {
		String sql = "SELECT COUNT(wish_idx) FROM wishlist WHERE cid=?";
		int max = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt(1);
				max = (int) Math.ceil(cnt / (double) pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}

	public int delWishList(String wish_idx) {
		String sql = "DELETE wishlist WHERE wish_idx=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, wish_idx);
			if (ps.executeUpdate() > 0) {
				success = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		if (success > 0) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		return success;
	}

	public ArrayList<WishDTO> mainWishList(String cid) {
		String sql = "SELECT w.rnum, p.p_name, p.p_price, p.newfilename " + 
				"	FROM (SELECT ROW_NUMBER() OVER(ORDER BY wish_idx DESC) AS rnum,wish_idx,p_idx,cid FROM wishlist) w, (SELECT p.p_idx, p.p_name, p.p_price, t.newfilename FROM product p, thumbfile t WHERE p.p_idx=t.p_idx) p\r\n" + 
				"	WHERE w.cid=? and w.p_idx = p.p_idx and rnum BETWEEN 1 AND 3";
		ArrayList<WishDTO> list = new ArrayList<WishDTO>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			while(rs.next()) {
				WishDTO dto = new WishDTO();
				dto.setP_name(rs.getString(2));
				dto.setP_price(rs.getInt(3));
				dto.setNewFileName(rs.getString(4));
				list.add(dto);
			}
			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return list;
	}

	public void addWishList(String p_idx, String cid) {
		String sql = "INSERT INTO wishlist(cid, idx) VALUES(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, p_idx);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
	}
	
	

}
