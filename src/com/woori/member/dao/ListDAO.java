package com.woori.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.woori.member.dto.BlackListDTO;
import com.woori.member.dto.CustomerListDTO;
import com.woori.member.dto.ReportListDTO;
import com.woori.member.dto.SellerListDTO;

//각종 List DB
public class ListDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int pagePerCnt = 5;
	
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

	public HashMap<String, Object> cList(int group) {
		ArrayList<CustomerListDTO> cList = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		//String sql = "SELECT a.cid, count(c.target_id) AS cntReport, count(b.cid) AS cntBlack, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM consumer a left outer join c_blacklist b on a.cid = b.cid left outer join report c on a.cid = c.target_id GROUP BY a.cid, b.isblack, a.reg_date ORDER BY a.reg_date";
		String sql = "SELECT *FROM (SELECT ROW_NUMBER() OVER(ORDER BY a.reg_date) AS rnum, a.cid, count(c.target_id) AS cntReport, count(b.cid) AS cntBlack, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM consumer a left outer join c_blacklist b on a.cid = b.cid left outer join report c on a.cid = c.target_id GROUP BY a.cid, b.isblack, a.reg_date) WHERE rnum between ? and ?";
		int start = 0;
		int end = 0;
		
		//pagePerCnt : 리스트는 무조건 5개씩 
		end = pagePerCnt*group;
		start = end-(pagePerCnt-1);
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				CustomerListDTO dto = new CustomerListDTO();
				dto.setCid(rs.getString("cid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setCntBlack(rs.getInt("cntblack"));
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
		//String sql = "SELECT a.sid, count(c.target_id) AS cntReport, count(b.sid) AS cntBlack, b.isblack, a.reg_date FROM seller a left outer join s_blacklist b on a.sid = b.sid left outer join report c on a.sid = c.target_id GROUP BY a.sid, b.isblack, a.reg_date ORDER BY a.reg_date";
		String sql = "SELECT * FROM ( SELECT ROW_NUMBER() OVER(ORDER BY a.reg_date) AS rnum, a.sid, count(c.target_id) AS cntReport, count(b.sid) AS cntBlack, b.isblack, a.reg_date FROM seller a left outer join s_blacklist b on a.sid = b.sid left outer join report c on a.sid = c.target_id GROUP BY a.sid, b.isblack, a.reg_date ) WHERE rnum between ? and ?";
		int start = 0;
		int end = 0;
		
		//pagePerCnt : 리스트는 무조건 5개씩 
		end = pagePerCnt*group;
		start = end-(pagePerCnt-1);
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				SellerListDTO dto = new SellerListDTO();
				dto.setSid(rs.getString("sid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setCntBlack(rs.getInt("cntblack"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				sList.add(dto);
			}
			int maxPage = getMaxSellerPage();
			map.put("sList", sList);
			map.put("maxSellerPage", maxPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return map;
	}

	public ArrayList<BlackListDTO> bList() {
		ArrayList<BlackListDTO> bList = new ArrayList<>();
		String sql = "SELECT sid AS id, reason, stack, to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM s_blacklist WHERE (sid, reg_date) in (SELECT sid, MAX(reg_date) FROM s_blacklist WHERE 1=1 GROUP BY sid)";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BlackListDTO dto = new BlackListDTO();
				dto.setId(rs.getString("id"));
				dto.setReason(rs.getString("reason"));
				dto.setStack(rs.getInt("stack"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setAdmin_id(rs.getString("admin_id"));
				bList.add(dto);
			}
			sql = "SELECT cid AS id, reason, stack, to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM c_blacklist WHERE (cid, reg_date) in (SELECT cid, MAX(reg_date) FROM c_blacklist WHERE 1=1 GROUP BY cid)";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BlackListDTO dto = new BlackListDTO();
				dto.setId(rs.getString("id"));
				dto.setReason(rs.getString("reason"));
				dto.setStack(rs.getInt("stack"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setAdmin_id(rs.getString("admin_id"));
				bList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return bList;
	}

	public HashMap<String, Object> rList(int group) {
		ArrayList<ReportListDTO> rList = new ArrayList<ReportListDTO>();
		HashMap<String, Object> map = new HashMap<>();
		int start = 0;
		int end = 0;
		
		//pagePerCnt : 리스트는 무조건 5개씩 
		end = pagePerCnt*group;
		start = end-(pagePerCnt-1);
		
		String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM(SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM report) WHERE rnum BETWEEN ? AND ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
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
		}finally {
			resClose();
		}
		return map;
	}

	public ArrayList<CustomerListDTO> cSearch(String inputC) {
		ArrayList<CustomerListDTO> search = new ArrayList<CustomerListDTO>();
		String sql = "SELECT a.cid, count(c.target_id) AS cntReport, count(b.cid) AS cntBlack, b.isblack, to_char(a.reg_date, 'YYYY-MM-DD') AS reg_date FROM consumer a left outer join c_blacklist b on a.cid = b.cid left outer join report c on a.cid = c.target_id WHERE a.cid=? GROUP BY a.cid, b.isblack, a.reg_date ORDER BY a.reg_date";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputC);
			rs = ps.executeQuery();
			if(rs.next()) {
				CustomerListDTO dto = new CustomerListDTO();
				dto.setCid(rs.getString("cid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setCntBlack(rs.getInt("cntblack"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return search;
	}

	public ArrayList<SellerListDTO> sSearch(String inputS) {
		ArrayList<SellerListDTO> search = new ArrayList<SellerListDTO>();
		String sql = "SELECT a.sid, count(c.target_id) AS cntReport, count(b.sid) AS cntBlack, b.isblack, a.reg_date FROM seller a left outer join s_blacklist b on a.sid = b.sid left outer join report c on a.sid = c.target_id WHERE a.sid=? GROUP BY a.sid, b.isblack, a.reg_date ORDER BY a.reg_date";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputS);
			rs = ps.executeQuery();
			if(rs.next()) {
				SellerListDTO dto = new SellerListDTO();
				dto.setSid(rs.getString("sid"));
				dto.setCntReport(rs.getInt("cntreport"));
				dto.setCntBlack(rs.getInt("cntblack"));
				dto.setIsBlack(rs.getInt("isblack"));
				dto.setReg_date(rs.getString("reg_date"));
				search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return search;
	}

	public ArrayList<BlackListDTO> bSellerSearch(String inputB) {
		ArrayList<BlackListDTO> bList = new ArrayList<>();
		String sql = "SELECT sid AS id, reason, stack, to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM s_blacklist WHERE (sid, reg_date) in (SELECT sid, MAX(reg_date) FROM s_blacklist WHERE 1=1 GROUP BY sid) AND sid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputB);
			rs = ps.executeQuery();
			if(rs.next()) {
				BlackListDTO dto = new BlackListDTO();
				dto.setId(rs.getString("id"));
				dto.setReason(rs.getString("reason"));
				dto.setStack(rs.getInt("stack"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setAdmin_id(rs.getString("admin_id"));
				System.out.println(rs.getString("id")+" / "+rs.getString("reason")+" / "+rs.getInt("stack")+" / "+rs.getString("reg_date")+" / "+rs.getString("admin_id"));
				bList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return bList;
	}

	public ArrayList<BlackListDTO> bCustomerSearch(String inputB) {
		ArrayList<BlackListDTO> bList = new ArrayList<>();
		String sql = "SELECT cid AS id, reason, stack, to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM c_blacklist WHERE (cid, reg_date) in (SELECT cid, MAX(reg_date) FROM c_blacklist WHERE 1=1 GROUP BY cid) AND cid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputB);
			rs = ps.executeQuery();
			if(rs.next()) {
				BlackListDTO dto = new BlackListDTO();
				dto.setId(rs.getString("id"));
				dto.setReason(rs.getString("reason"));
				dto.setStack(rs.getInt("stack"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setAdmin_id(rs.getString("admin_id"));
				bList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return bList;
	}

	public HashMap<String, Object> rReporterSearch(String inputR, int group) {
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<ReportListDTO> rList = new ArrayList<>();
		//String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM report WHERE reporter_id=?";
		String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM(SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM report WHERE reporter_id=?) WHERE rnum BETWEEN ? AND ?";
		int start = 0;
		int end = 0;
		
		//pagePerCnt : 리스트는 무조건 5개씩 
		end = pagePerCnt*group;
		start = end-(pagePerCnt-1);
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputR);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
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
			int maxPage = getMaxSearchReportPage(inputR);
			map.put("maxReportPage", maxPage);
			map.put("rList", rList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return map;
	}

	public ArrayList<ReportListDTO> rTargetSearch(String inputR) {
		ArrayList<ReportListDTO> rList = new ArrayList<>();
		String sql = "SELECT r_idx, rc_code, subject, reporter_id, target_id, r_date, status FROM report WHERE target_id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputR);
			rs = ps.executeQuery();
			while(rs.next()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return rList;
	}
	
	private int getMaxReportPage() {
		String sql="SELECT COUNT(r_idx) AS cnt FROM report";	
		int max = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				max = (int) Math.ceil(cnt/(double)pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	private int getMaxSearchReportPage(String inputR) {
		String sql="SELECT COUNT(r_idx) AS cnt FROM report WHERE = ?";	
		int max = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				max = (int) Math.ceil(cnt/(double)pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	private int getMaxSellerPage() {
		String sql="SELECT COUNT(sid) AS cnt FROM seller";	
		int max = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				max = (int) Math.ceil(cnt/(double)pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	private int getMaxCustomerPage() {
		String sql="SELECT COUNT(cid) AS cnt FROM consumer";	
		int max = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				max = (int) Math.ceil(cnt/(double)pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}



}
