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

	public HashMap<String, Object> sQAList(String sid, int page) { // 판매자 문의내역 리스트
		
		//페이징
		int pagePerCnt = 10;//한페이당 수
		int end = page * pagePerCnt;//페이지의 끝
		int start = end-(pagePerCnt-1);//페이지의 시작
		HashMap<String, Object> map = new HashMap<String, Object>();
		//
		ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();
		
		String sql = "SELECT q_idx, category, subject, content, cid, q_reg_date, s_answer " + 
				"    FROM (SELECT ROW_NUMBER() OVER(ORDER BY q.q_idx DESC) AS rnum, q.q_idx, qc.category, q.subject, q.content, q.cid, q.q_reg_date, q.s_answer " + 
				"            FROM question q, q_categories qc WHERE q.qc_idx = qc.qc_idx AND sid = ?) " + 
				"    WHERE rnum BETWEEN ? AND ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setInt(2, start);
			ps.setInt(3, end);
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
			System.out.println("문의리스트 수:" + list.size()); //리스트 사이즈 확인
			int maxPage = getMaxPageSellerQnAList(sid, pagePerCnt);
			map.put("list", list);
			map.put("maxPage", maxPage);
			System.out.println("max page : "+maxPage);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}
	private int getMaxPageSellerQnAList(String sid, int pagePerCnt) {//판매자 문의리스트 최대페이지
		String sql="SELECT COUNT(q_idx) FROM (SELECT ROW_NUMBER() OVER(ORDER BY q.q_idx DESC) AS rnum, q.q_idx, qc.category, q.subject, q.content, q.cid, q.q_reg_date, q.s_answer \r\n" + 
				"    FROM question q, q_categories qc WHERE q.qc_idx = qc.qc_idx AND sid = ?)";		
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

	public HashMap<String, Object> cQuestionList(int group, String cid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pagePerCnt = 10;
		int end = group * pagePerCnt;
		int start = end - (pagePerCnt - 1);
		System.out.println(start + " ~ " + end + "까지의 리스트");

		ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();
		String sql = "SELECT q_idx, subject, s_answer, cid, to_char(q_reg_date,'yyyy-mm-dd') q_reg_date "
				+ "FROM (SELECT ROW_NUMBER() OVER(ORDER BY q_idx DESC) AS rnum, q_idx,subject, s_answer, cid, q_reg_date FROM question WHERE cid=?) "
				+ "WHERE rnum BETWEEN ? AND ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQ_idx(rs.getInt(1));
				dto.setSubject(rs.getString(2));
				dto.setS_answer(rs.getString(3));
				dto.setQ_reg_date(rs.getString(5));
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
		}finally {
			resClose();
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
		}finally {
			resClose();
		}
		return max;
	}

	public QuestionDTO sAnswerDetail(int q_idx) { // 판매자 문의 상세보기 
		String sql = "SELECT q.q_idx, q.sid, q.p_name, qc.category, q.subject, q.content, q.cid, q.q_reg_date, q.s_answer " + 
				"    FROM question q, q_categories qc WHERE q.qc_idx = qc.qc_idx AND q_idx=?";
		
		QuestionDTO dto = new QuestionDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, q_idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setQ_idx(rs.getInt("q_idx"));
				dto.setSid(rs.getString("sid"));//판매자
				dto.setP_name(rs.getString("p_name"));//문의상품
				dto.setCategory(rs.getString("category"));
				dto.setSubject(rs.getString("subject"));//제목
				dto.setContent(rs.getString("content"));//내용
				dto.setCid(rs.getString("cid"));
				dto.setQ_reg_date(rs.getString("q_reg_date").substring(0,10));//문의날짜
				dto.setS_answer(rs.getString("s_answer"));//답변
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return dto;
	}

	public int sAnswer(int q_idx, String s_answer) { //판매자 문의 답변하기
		String sql = "UPDATE question SET s_answer=? WHERE q_idx=?";
		
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, s_answer);
			ps.setInt(2, q_idx);
			success = ps.executeUpdate();
			System.out.println("답변 성공 여부 :"+success);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return success;
	}

	public long qWrite(String sId, String cId, int category, String subject,
			String content, int pass, String p_name) {
		String sql = "INSERT INTO Question (q_idx, subject, content, q_pw, cid, sid, qc_idx, p_name)VALUES"
				+"(Question_seq.NEXTVAL , ?,?,?,?,?,?,?)";
		long q_idx = 0;
		try {
			ps = conn.prepareStatement(sql, new String[] {"q_idx"});
			ps.setString(1, subject);
			ps.setString(2, content);
			ps.setInt(3, pass);
			ps.setString(4, cId);
			ps.setString(5, sId);
			ps.setInt(6, category);
			ps.setString(7, p_name);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				 q_idx = rs.getLong(1);
			}
			System.out.println(q_idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return q_idx;
	}

	public HashMap<String, Object> qDetail(String q_idx, String q_pw) {
		String sql =   "SELECT  subject, cid, category, q_reg_date, sid, content, s_answer, p_name FROM\r\n" + 
				"Question q, Q_Categories qc WHERE q.qc_idx = qc.qc_idx AND q_idx=? AND q_pw=?";
		boolean success = false;
		HashMap<String, Object> map = new HashMap<String, Object>();
		QuestionDTO dto = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, q_idx);
			ps.setString(2, q_pw);
			rs = ps.executeQuery();
			 dto = new QuestionDTO();
			if(rs.next()) {
				dto.setSubject(rs.getString("subject"));
				dto.setCid(rs.getString("cid"));
				dto.setCategory(rs.getString("category"));
				dto.setQ_reg_date(rs.getString("q_reg_date"));
				dto.setSid(rs.getString("sid"));
				dto.setContent(rs.getString("content"));
				dto.setS_answer(rs.getString("s_answer"));
				dto.setP_name(rs.getString("p_name"));
				success = true;
			}
			map.put("success", success);
			map.put("dto", dto);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}return map;
	}

}
