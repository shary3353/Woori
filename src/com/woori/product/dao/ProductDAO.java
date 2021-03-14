package com.woori.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public ArrayList<ProductDTO> sItemList() {
		ArrayList<ProductDTO> list =new ArrayList<ProductDTO>();
		String sql = "SELECT p.p_idx, c.category, p.p_name, p.p_content, p.p_price, th.oriFileName, th.newFileName" + 
				" FROM product p, thumbfile th, categories c WHERE p.p_idx = th.p_idx(+) AND p.c_idx = c.c_idx ORDER BY p.p_idx DESC";
		
		try {
			ps = conn.prepareStatement(sql);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return list;
	}

}
