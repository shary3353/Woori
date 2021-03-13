package com.woori.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.woori.member.dto.CustomerDTO;

public class CustomerDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public CustomerDAO() {
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
	
	
	

	public ArrayList<CustomerDTO> cList() {
		String sql = "SELECT * FROM consumer";
		ArrayList<CustomerDTO> list = new ArrayList<CustomerDTO>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CustomerDTO dto = new CustomerDTO();
				dto.setCid(rs.getString("cid"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setReg_date(rs.getString("reg_Date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		
		
		
		
		
		
		return list;
	}

}
