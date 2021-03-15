package com.woori.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.question.dto.QuestionDTO;
import com.woori.wish.dto.WishDTO;

public class QuestionDAO {

	// 공통
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public QuestionDAO() {// DB
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resClose() {// 자원반납
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

	public ArrayList<QuestionDTO> sQAList(String sid) { // 판매자 문의내역 리스트
		ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();
		String sql = "SELECT q.q_idx, qc.category, q.subject, q.content, q.cid, q.q_reg_date, q.s_answer "
				+ "FROM question q, q_categories qc WHERE q.qc_idx = qc.qc_idx AND sid = ? ORDER BY q.q_idx DESC";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			while (rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQ_idx(rs.getInt("q_idx"));
				dto.setCategory(rs.getString("category"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCid(rs.getString("cid"));
				dto.setQ_reg_date(rs.getString("q_reg_date").substring(0, 10));
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

	public HashMap<String, Object> cQuestionList(int group, String cid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pagePerCnt = 10;
		int end = group * pagePerCnt;
		int start = end - (pagePerCnt - 1);
		System.out.println(start + " ~ " + end + "까지의 리스트");

		ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();
		String sql = "SELECT rnum, q_idx, subject, s_answer, cid, to_char(q_reg_date,'yyyy-mm-dd') q_reg_date "
				+ "FROM (SELECT ROW_NUMBER() OVER(ORDER BY q_idx DESC) AS rnum, q_idx,subject, s_answer, cid, q_reg_date FROM question) "
				+ "WHERE cid=? AND rnum BETWEEN ? AND ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQ_idx(rs.getInt("q_idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setS_answer(rs.getString("s_answer"));
				dto.setQ_reg_date(rs.getString("q_reg_date"));
				list.add(dto);
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
		String sql = "SELECT COUNT(q_idx) FROM question WHERE cid=?";
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

	public QuestionDTO cQuestionDetail(String q_idx) {
		String sql = "SELECT q.subject, q.cid, q.qc_idx, to_char(q.q_reg_date,'yyyy-mm-dd') q_reg_date, q.sid, q.content, q.s_answer, ca.category "
				+ "FROM question q, q_categories ca "
				+ "WHERE q.q_idx=? AND q.qc_idx = ca.qc_idx";
		QuestionDTO dto = new QuestionDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, q_idx);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setSubject(rs.getString(1));
				dto.setCid(rs.getString(2));
				dto.setQc_idx(rs.getInt(3));
				dto.setQ_reg_date(rs.getString(4));
				dto.setSid(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setS_answer(rs.getString(7));
				dto.setCategory(rs.getString(8));
			}
			System.out.println(dto.getSubject() + " / " + dto.getS_answer());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return dto;

	}

	public HashMap<String, Object> q_list(int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pagePerCnt = 5;
		int end = page * pagePerCnt;
		int start = end -(pagePerCnt - 1);
		ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();
		String sql = "SELECT q_idx,subject,q_reg_date FROM"
				+"(SELECT ROW_NUMBER() OVER(ORDER BY q_idx DESC) AS rnum, q_idx,subject,q_reg_date FROM Question)"
				 +"WHERE rnum BETWEEN ? AND ?";
			
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQ_idx(rs.getInt("q_idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setQ_reg_date(rs.getString("q_reg_Date"));
				list.add(dto);
			}
			map.put("list", list);
			System.out.println("list size : "  + list.size());
			int maxPage = getQuestionListMaxPage(pagePerCnt);
			System.out.println("maxPage : " + maxPage);
			map.put("maxPage", maxPage);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		
		
		return map;
	}
	private int getQuestionListMaxPage(int pagePerCnt) {
		String sql = "SELECT COUNT(q_idx) FROM question";
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

}
