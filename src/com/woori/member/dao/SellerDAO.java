package com.woori.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.member.dto.SellerDTO;

public class SellerDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public SellerDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void resClose() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SellerDTO> sList() {
		String sql = "SELECT * FROM seller";
		ArrayList<SellerDTO> sList = new ArrayList<SellerDTO>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SellerDTO dto = new SellerDTO();
				dto.setSid(rs.getString("sid"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setStore_call(rs.getString("store_call"));
				dto.setS_phone(rs.getString("s_phone"));
				dto.setReg_date(rs.getString("reg_date"));
				sList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return sList;
	}

}
