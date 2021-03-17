package com.woori.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.member.dto.CustomerListDTO;
import com.woori.member.dto.SellerListDTO;

//각종 List DB
public class ListDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int pagePerCnt = 10;

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
			if (rs != null) {rs.close();}
			if (ps != null) {ps.close();}
			if (conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Object> cList(int group) {
		ArrayList<CustomerListDTO> cList = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		// String sql = "SELECT a.cid, count(c.target_id) AS cntReport, count(b.cid) AS
		// cntBlack, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM
		// consumer a left outer join c_blacklist b on a.cid = b.cid left outer join
		// report c on a.cid = c.target_id GROUP BY a.cid, b.isblack, a.reg_date ORDER
		// BY a.reg_date";
		String sql = "SELECT *FROM (SELECT ROW_NUMBER() OVER(ORDER BY a.reg_date) AS rnum, a.cid, count(c.target_id) AS cntReport, b.stack, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM consumer a left outer join c_blacklist b on a.cid = b.cid left outer join report c on a.cid = c.target_id GROUP BY a.cid, b.isblack, a.reg_date, b.stack) WHERE rnum between ? and ?";
		int start = 0;
		int end = 0;

		// pagePerCnt : 리스트는 무조건 5개씩
		end = pagePerCnt * group;
		start = end - (pagePerCnt - 1);

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				CustomerListDTO dto = new CustomerListDTO();
				dto.setCid(rs.getString("cid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setStack(rs.getInt("stack"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				cList.add(dto);
			}
			int maxPage = getMaxCustomerPage();
			map.put("maxCustomerPage", maxPage);
			map.put("cList", cList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}

	public HashMap<String, Object> sList(int group) {
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<SellerListDTO> sList = new ArrayList<SellerListDTO>();
		// String sql = "SELECT a.sid, count(c.target_id) AS cntReport, count(b.sid) AS
		// cntBlack, b.isblack, a.reg_date FROM seller a left outer join s_blacklist b
		// on a.sid = b.sid left outer join report c on a.sid = c.target_id GROUP BY
		// a.sid, b.isblack, a.reg_date ORDER BY a.reg_date";
		String sql = "SELECT * FROM ( SELECT ROW_NUMBER() OVER(ORDER BY a.reg_date) AS rnum, a.sid, count(c.target_id) AS cntReport, b.stack, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM seller a left outer join s_blacklist b on a.sid = b.sid left outer join report c on a.sid = c.target_id GROUP BY a.sid, b.isblack, a.reg_date, b.stack ) WHERE rnum between ? and ?";
		int start = 0;
		int end = 0;

		// pagePerCnt : 리스트는 무조건 5개씩
		end = pagePerCnt * group;
		start = end - (pagePerCnt - 1);
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				SellerListDTO dto = new SellerListDTO();
				dto.setSid(rs.getString("sid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setStack(rs.getInt("stack"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				sList.add(dto);
			}
			int maxPage = getMaxSellerPage();
			map.put("sList", sList);
			map.put("maxSellerPage", maxPage);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return map;
	}

	public ArrayList<CustomerListDTO> cSearch(String inputC) {
		ArrayList<CustomerListDTO> search = new ArrayList<CustomerListDTO>();
		String sql = "SELECT a.cid, count(c.target_id) AS cntReport, b.stack, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM consumer a left outer join c_blacklist b on a.cid = b.cid left outer join report c on a.cid = c.target_id WHERE a.cid=? GROUP BY a.cid, b.isblack, a.reg_date, b.stack ORDER BY a.reg_date";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputC);
			rs = ps.executeQuery();
			if (rs.next()) {
				CustomerListDTO dto = new CustomerListDTO();
				dto.setCid(rs.getString("cid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setStack(rs.getInt("stack"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return search;
	}

	public ArrayList<SellerListDTO> sSearch(String inputS) {
		ArrayList<SellerListDTO> search = new ArrayList<SellerListDTO>();
		String sql = "SELECT a.sid, count(c.target_id) AS cntReport, b.stack, b.isblack, a.reg_date FROM seller a left outer join s_blacklist b on a.sid = b.sid left outer join report c on a.sid = c.target_id WHERE a.sid=? GROUP BY a.sid, b.isblack, a.reg_date b.stack ORDER BY a.reg_date";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputS);
			rs = ps.executeQuery();
			if (rs.next()) {
				SellerListDTO dto = new SellerListDTO();
				dto.setSid(rs.getString("sid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setStack(rs.getInt("stack"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return search;
	}

	private int getMaxSellerPage() {
		String sql = "SELECT COUNT(sid) AS cnt FROM seller";
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

	private int getMaxCustomerPage() {
		String sql = "SELECT COUNT(cid) AS cnt FROM consumer";
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
