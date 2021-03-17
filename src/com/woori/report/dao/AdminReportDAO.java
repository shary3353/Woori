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
import com.woori.report.dto.ReportListDTO;

public class AdminReportDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int pagePerCnt = 10;

	public AdminReportDAO() {
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

	public HashMap<String, Object> rList(int group) {
		ArrayList<ReportListDTO> rList = new ArrayList<ReportListDTO>();
		HashMap<String, Object> map = new HashMap<>();
		int start = 0;
		int end = 0;

		// pagePerCnt : 리스트는 무조건 5개씩
		end = pagePerCnt * group;
		start = end - (pagePerCnt - 1);

		String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, to_char(r_date, 'YYYY-MM-DD') AS r_date, status FROM(SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM report) WHERE rnum BETWEEN ? AND ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportListDTO dto = new ReportListDTO();
				dto.setR_idx(rs.getInt("r_idx"));
				dto.setRc_code(rs.getInt("rc_code"));
				dto.setSubject(rs.getString("subject"));
				dto.setReporter_id(rs.getString("reporter_id"));
				dto.setTarget_id(rs.getString("target_id"));
				dto.setR_date(rs.getString("r_date"));
				dto.setStatus(rs.getInt("status"));
				rList.add(dto);
			}
			int maxPage = getMaxReportPage();
			map.put("rList", rList);
			map.put("maxReportPage", maxPage);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}

	public HashMap<String, Object> rReporterSearch(String inputR, int group) {
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<ReportListDTO> rList = new ArrayList<>();
		// String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date,
		// status FROM report WHERE reporter_id=?";
		String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM(SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM report WHERE reporter_id=?) WHERE rnum BETWEEN ? AND ?";
		int start = 0;
		int end = 0;

		// pagePerCnt : 리스트는 무조건 5개씩
		end = pagePerCnt * group;
		start = end - (pagePerCnt - 1);
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputR);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportListDTO dto = new ReportListDTO();
				dto.setR_idx(rs.getInt("r_idx"));
				dto.setRc_code(rs.getInt("rc_code"));
				dto.setSubject(rs.getString("subject"));
				dto.setReporter_id(rs.getString("reporter_id"));
				dto.setTarget_id(rs.getString("target_id"));
				dto.setR_date(rs.getString("r_date"));
				dto.setStatus(rs.getInt("status"));
				rList.add(dto);
			}
			int maxPage = getMaxSearchReporterReportPage(inputR);
			map.put("maxReportPage", maxPage);
			map.put("rList", rList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}

	public HashMap<String, Object> rTargetSearch(String inputR, int group) {
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<ReportListDTO> rList = new ArrayList<>();
		// String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date,
		// status FROM report WHERE target_id=?";
		String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM(SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM report WHERE target_id=?) WHERE rnum BETWEEN ? AND ?";
		int start = 0;
		int end = 0;

		// pagePerCnt : 리스트는 무조건 5개씩
		end = pagePerCnt * group;
		start = end - (pagePerCnt - 1);
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputR);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportListDTO dto = new ReportListDTO();
				dto.setR_idx(rs.getInt("r_idx"));
				dto.setRc_code(rs.getInt("rc_code"));
				dto.setSubject(rs.getString("subject"));
				dto.setReporter_id(rs.getString("reporter_id"));
				dto.setTarget_id(rs.getString("target_id"));
				dto.setR_date(rs.getString("r_date"));
				dto.setStatus(rs.getInt("status"));
				rList.add(dto);
			}
			int maxPage = getMaxSearchTargetReportPage(inputR);
			map.put("maxReportPage", maxPage);
			map.put("rList", rList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}

	private int getMaxReportPage() {
		String sql = "SELECT COUNT(r_idx) AS cnt FROM report";
		int max = 0;

		try {
			ps = conn.prepareStatement(sql);
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

	private int getMaxSearchReporterReportPage(String inputR) {
		String sql = "SELECT COUNT(r_idx) AS cnt FROM report WHERE reporter_id = ?";
		int max = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputR);
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

	private int getMaxSearchTargetReportPage(String inputR) {
		String sql = "SELECT COUNT(r_idx) AS cnt FROM report WHERE target_id = ?";
		int max = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputR);
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

	public boolean rStatusUpdate(String r_idx, String rStatus) {
		boolean success = false;
		String sql = "UPDATE report SET status=? WHERE r_idx=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, rStatus);
			ps.setString(2, r_idx);
			if (ps.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return success;
	}

	public ArrayList<ReportDTO> getRList(String id, int group) {
		ArrayList<ReportDTO> rList = new ArrayList<>();
		//String sql = "SELECT a.subject, a.content, a.reporter_id, a.target_id, b.categories, to_char(a.r_date, 'YYYY-MM-DD') AS r_date, a.status FROM report a left outer join report_categories b ON a.rc_code = b.rc_idx WHERE target_id=?";
		String sql = "SELECT rnum, subject, content, reporter_id, target_id, categories, to_char(r_date, 'YYYY-MM-DD') AS r_date, status FROM (SELECT ROW_NUMBER() OVER(ORDER BY a.r_date) AS rnum, a.subject, a.content, a.reporter_id, a.target_id, b.categories, a.r_date, a.status FROM report a left outer join report_categories b ON a.rc_code = b.rc_idx WHERE a.target_id = ?)WHERE rnum BETWEEN ? AND ?";
		int start = 0;
		int end = 0;

		// pagePerCnt : 리스트는 무조건 5개씩
		end = pagePerCnt * group;
		start = end - (pagePerCnt - 1);
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReportDTO dto = new ReportDTO();
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReporter_id(rs.getString("reporter_id"));
				dto.setTarget_id(rs.getString("target_id"));
				dto.setCategory(rs.getString("categories"));
				dto.setR_date(rs.getString("r_date"));
				dto.setStatus(rs.getInt("status"));
				rList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resClose();
		}
		return rList;
	}
	
	public int getMaxSelectedRPage(String id) {
		String sql = "SELECT count(rnum) AS cnt FROM (SELECT ROW_NUMBER() OVER(ORDER BY a.r_date) AS rnum FROM report a left outer join report_categories b ON a.rc_code = b.rc_idx WHERE a.target_id = ?)";
		int max = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
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
