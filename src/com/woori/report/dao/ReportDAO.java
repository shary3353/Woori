package com.woori.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.report.dto.ReportDTO;
import com.woori.reservation.dto.ReservationDTO;

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

	public HashMap<String, Object> sReportList(String sid, int page) { //판매자 신고내역 리스트 
		
		//페이징
		int pagePerCnt = 10;//페이지당 개수
		int end = page * pagePerCnt;//페이지의 끝 
		int start = end-(pagePerCnt-1);//페이지의 시작
		HashMap<String, Object> map = new HashMap<String, Object>();
		//
		ArrayList<ReportDTO> list = new ArrayList<ReportDTO>();
		String sql = "SELECT r_idx, categories,subject, reporter_id, target_id, r_date, status " + 
				"    FROM (SELECT ROW_NUMBER() OVER (ORDER BY r.r_idx DESC) AS rnum, r.r_idx, rs.categories,r.subject, r.reporter_id,r.target_id, r.r_date, r.status " + 
				"            FROM report r, report_categories rs WHERE r.rc_code=rs.rc_idx AND r.reporter_id=?) "
				+ "WHERE rnum BETWEEN ? AND ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setInt(2, start);
			ps.setInt(3, end);
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
			System.out.println("신고리스트 수:" + list.size()); //리스트 사이즈 확인	
			int maxPage = getMaxPageSellerReportList(sid, pagePerCnt);//maxpage
			map.put("list", list);
			map.put("maxPage", maxPage);
			System.out.println("max page : "+maxPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return map;
	}
	private int getMaxPageSellerReportList(String sid, int pagePerCnt) { //판매자 신고리스트 최대페이지
		String sql="SELECT COUNT(r_idx) FROM (SELECT r.r_idx, rs.categories,r.subject, r.reporter_id,r.target_id, r.r_date, r.status " + 
				"    FROM report r, report_categories rs WHERE r.rc_code=rs.rc_idx AND r.reporter_id=?)";
		
		int max = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				max = (int) Math.ceil(cnt/(double)pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			resClose();
		}
		return max;
	}

	public HashMap<String, Object> report(ReportDTO dto) { //고객센터 신고하기, 판매자 신고하기에서 둘다 사용 중 - 변경 시 알릴 것.

		String sql = "INSERT INTO report (R_IDX, CONTENT,RC_code,Reporter_ID,Target_ID,"
				+"SUBJECT)VALUES(Report_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		long r_idx = 0;
		boolean success = false;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			ps = conn.prepareStatement(sql,new String[] {"R_IDX"});
			ps.setString(1, dto.getContent());
			ps.setString(2, dto.getCategory());
			ps.setString(3, dto.getReporter_id());	
			ps.setString(4, dto.getTarget_id());
			ps.setString(5, dto.getSubject());
			ps.executeUpdate();
			System.out.println("실행완료");
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				success = true;
				r_idx = rs.getLong(1);
			}
			map.put("r_idx", r_idx);
			map.put("success", success);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}return  map;
	}

	public ReportDTO detail(String idx) { //고객센터 신고상세보기, 판매자 신고상세보기에서 둘다 사용 중 - 변경 시 알릴 것.
		
		String sql = "SELECT subject, reporter_id, target_id, rs.categories AS categories, status, content FROM\r\n" + 
				"Report r, report_categories rs WHERE r.rc_code = rs.rc_idx AND r_idx = ?";
		ReportDTO dto = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new ReportDTO();
				dto.setSubject(rs.getString("subject"));
				dto.setReporter_id(rs.getString("reporter_id"));
				dto.setTarget_id(rs.getString("target_id"));
				dto.setCategory(rs.getString("categories"));
				dto.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}return dto;
	}

	public HashMap<String, Object> cReportList(int group, String cid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pagePerCnt = 10;
		int end = group * pagePerCnt;
		int start = end - (pagePerCnt - 1);
		System.out.println(start + " ~ " + end + "까지의 리스트");
		ArrayList<ReportDTO> list = new ArrayList<ReportDTO>();

		String sql = "SELECT r.rnum, r.r_idx, r.subject, r.target_id, r.r_date, rc.categories, r.status "
				+ "FROM (SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, r_idx, rc_code, subject, target_id, to_char(r_date, 'yyyy-mm-dd') r_date, status FROM report WHERE reporter_id = ?) r,report_categories rc "
				+ "WHERE r.rc_code=rc.rc_idx AND r.rnum BETWEEN ? AND ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportDTO dto = new ReportDTO();
				dto.setR_idx(rs.getInt(2));
				dto.setSubject(rs.getString(3));
				dto.setTarget_id(rs.getString(4));
				dto.setR_date(rs.getString(5));
				dto.setCategory(rs.getString(6));
				dto.setStatus(rs.getInt(7));
				list.add(dto);
			}
			System.out.println("신고내역 데이터 수 : " + list.size());
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
		String sql = "SELECT COUNT(r_idx) FROM report WHERE reporter_id=?";
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
		}finally {
			resClose();
		}
		return max;
	}

}
