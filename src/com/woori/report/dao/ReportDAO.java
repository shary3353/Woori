package com.woori.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.report.dto.ReportDTO;

public class ReportDAO {
	
	//공통
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ReportDAO() {//DB
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

	public ArrayList<ReportDTO> sReportList(String sid) { //판매자 신고내역 리스트 
		ArrayList<ReportDTO> list = new ArrayList<ReportDTO>();
		String sql = "SELECT r.r_idx, rs.categories, r.subject, r.target_id, r.r_date, r.status" + 
				"    FROM report r, report_categories rs\r\n" + 
				"    WHERE r.rc_code=rs.rc_idx AND r.reporter_id=? ORDER BY r.r_idx DESC";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReportDTO dto = new ReportDTO();
				dto.setR_idx(Integer.parseInt(rs.getString("r_idx")));//신고글번호
				dto.setCategory(rs.getString("categories"));//신고카테고리
				dto.setSubject(rs.getString("subject"));//신고제목
				dto.setTarget_id(rs.getString("target_id"));//신고대상자
				dto.setR_date(rs.getString("r_date").substring(0,10));//신고날짜
				dto.setStatus(rs.getInt("status"));//신고상태 0-처리중 1-처리완료
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
