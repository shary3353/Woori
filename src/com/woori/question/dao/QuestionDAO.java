package com.woori.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.question.dto.QuestionDTO;

public class QuestionDAO {
	
	//공통
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public QuestionDAO() {//DB
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

	public ArrayList<QuestionDTO> sQAList(String sid) { //판매자 문의내역 리스트 
		ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();
		String sql ="SELECT q.q_idx, qc.category, q.subject, q.content, q.cid, q.q_reg_date, q.s_answer " + 
				"FROM question q, q_categories qc WHERE q.qc_idx = qc.qc_idx AND sid = ? ORDER BY q.q_idx DESC";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			while(rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQ_idx(rs.getInt("q_idx"));
				dto.setCategory(rs.getString("category"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCid(rs.getString("cid"));
				dto.setQ_reg_date(rs.getString("q_reg_date").substring(0,10));
				dto.setS_answer(rs.getString("s_answer"));
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
