package com.woori.main.dao;

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


public class MainDAO {
	
	//자주쓰는 것들 필드에 미리선언
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//생성자
	public MainDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//자원종료
	public void resClose() {
		try {
			if(conn!=null) {conn.close();}
			if(ps!=null) {ps.close();}
			if(rs!=null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public HashMap<String, Object> cmain(int i) {
		
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT p.p_idx,p.p_name,p.likes,p.p_price,t.orifilename,t.newfilename " + 
				"FROM (SELECT p_idx,p_name,likes,p_price, ROW_NUMBER() OVER(ORDER BY likes DESC) AS rk FROM product) p JOIN thumbfile t ON p.p_idx = t.p_idx WHERE rk=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setLikes(rs.getInt("likes"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
				//사진이 없으면 파일 자체가 나타나지 않음
				list.add(dto);
			}
			System.out.println("list-size : " + list.size());
			map.put("list"+i, list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}


	public ProductDTO  citemdetail(String pidx) {
		ProductDTO dto = null;
		String sql="SELECT p.p_idx,p.p_name,p.p_content,p.p_price,t.orifilename,t.newfilename FROM product p JOIN thumbfile t ON p.p_idx = t.p_idx WHERE p.p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pidx));
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setP_content(rs.getString("p_content"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
		
	}


	public ProductDTO citemreservation(String pidx) {
		ProductDTO dto = null;
		String sql="SELECT p.p_idx,p.p_name,p.p_price,p.sid,t.orifilename,t.newfilename FROM product p JOIN thumbfile t ON p.p_idx = t.p_idx WHERE p.p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pidx));
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setSid(rs.getString("sid"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	
	
}
