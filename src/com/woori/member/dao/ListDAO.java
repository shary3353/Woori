package com.woori.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.woori.member.dto.CustomerListDTO;

//각종 List DB
public class ListDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ListDAO() {
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

	public ArrayList<CustomerListDTO> cList() {
		ArrayList<CustomerListDTO> cList = new ArrayList<>();
		String sql = "SELECT a.cid, count(c.target_id) AS cntReport, count(b.cid) AS cntBlock, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM consumer a left outer join c_blacklist b on a.cid = b.cid left outer join report c on a.cid = c.target_id GROUP BY a.cid, b.isblack, a.reg_date ORDER BY a.reg_date";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CustomerListDTO dto = new CustomerListDTO();
				dto.setCid(rs.getString("cid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setCntBlock(rs.getInt("cntblock"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				System.out.println(dto.getCid()+" / "+dto.getCntReport()+" / "+dto.getCntBlock()+" / "+dto.getIsBlack()+" / "+dto.getReg_date());
				cList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return cList;
	}

}
