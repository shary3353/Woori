package com.woori.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.reservation.dto.ReservationDTO;

public class ReservationDAO {

	// 공통
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public ReservationDAO() {// DB
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

	public ArrayList<ReservationDTO> sReservationList(String sid) {
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql = "SELECT r.r_idx, r.visit_date, r.reg_date, r.cid, p.p_idx, p.p_name, r.rs_idx, rs.status, s.sid, th.orifilename, th.newfilename"
				+ " FROM reservation r, reservation_status rs, product p, seller s, thumbfile th"
				+ " WHERE r.rs_idx=rs.rs_idx AND p.sid=s.sid AND r.p_idx=p.p_idx AND p.p_idx=th.p_idx(+)"
				+ " AND s.sid = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ReservationDTO dto = new ReservationDTO();
				dto.setR_idx(rs.getInt("r_idx"));// 문의번호
				dto.setP_idx(rs.getInt("p_idx"));// 상품번호
				dto.setP_name(rs.getString("p_name"));// 상품이름
				dto.setCid(rs.getString("cid"));// 구매자id
				dto.setVisit_date(rs.getString("visit_date").substring(0, 10));// 예약일
				dto.setReg_date(rs.getString("reg_date").substring(0, 10));// 신청한 날짜
				dto.setStatus(rs.getString("status"));// 예약현황
				dto.setOriFileName(rs.getString("orifilename"));//원본파일
				dto.setNewFileName(rs.getString("newfilename"));//새파일
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return list;
	}

	public void updateResevationStatus(int r_idx, int rs_idx) { // 판매자 예약현황 변경
		String sql = "UPDATE reservation SET rs_idx=? WHERE r_idx=?";

		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rs_idx);
			ps.setInt(2, r_idx);
			success = ps.executeUpdate();
			System.out.println("예약현황 변경 수 : " + success);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
	}

	public HashMap<String, Object> cReservationList(int group, String cid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pagePerCnt = 3;
		int end = group * pagePerCnt;
		int start = end - (pagePerCnt - 1);
		System.out.println(start + " ~ " + end + "까지의 리스트");
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();

		String sql = "SELECT rnum, r.r_idx, r.p_idx, r.p_name, r.sid, to_char(r.reg_date,'yyyy-mm-dd') reg_date,to_char(r.visit_date, 'yyyy-mm-dd') visit_date, rs.status, r.newFileName "
				+ "FROM (SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, r.r_idx, r.p_idx, r.reg_date, r.visit_date, r.cid, r.rs_idx, p.p_name, p.sid, p.newfilename "
				+ "FROM reservation r, (SELECT p.p_idx, p.p_name, p.p_price, p.sid, t.newfilename FROM product p, thumbfile t WHERE p.p_idx=t.p_idx) p WHERE r.cid=? AND r.p_idx = p.p_idx) r, reservation_status rs "
				+ "WHERE r.cid=? AND rs.rs_idx = r.rs_idx AND rnum BETWEEN ? AND ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, cid);
			ps.setInt(3, start);
			ps.setInt(4, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReservationDTO dto = new ReservationDTO();
				dto.setR_idx(rs.getInt(2));
				dto.setP_idx(rs.getInt(3));
				dto.setP_name(rs.getString(4));
				dto.setSid(rs.getString(5));
				dto.setReg_date(rs.getString(6));
				dto.setVisit_date(rs.getString(7));
				dto.setStatus(rs.getString(8));
				dto.setNewFileName(rs.getString(9));
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
		String sql = "SELECT COUNT(r_idx) FROM reservation WHERE cid=?";
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

	public ArrayList<ReservationDTO> mainReservationList(String cid) {
		String sql = "SELECT r.rnum, p.p_name,  p.p_price, p.newfilename, to_char(r.visit_date, 'yyyy-mm-dd') "
				+ "FROM (SELECT p.p_idx, p.p_name, p.p_price, p.sid, t.newfilename FROM product p, thumbfile t WHERE p.p_idx=t.p_idx) p"
				+ ",(SELECT ROW_NUMBER() OVER(ORDER BY r_idx DESC) AS rnum, cid, r_idx, visit_date, p_idx FROM reservation WHERE cid=?) r "
				+ "WHERE r.p_idx = p.p_idx AND rnum BETWEEN 1 AND 3";
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReservationDTO dto = new ReservationDTO();
				dto.setP_name(rs.getString(2));
				dto.setP_price(rs.getString(3));
				dto.setNewFileName(rs.getString(4));
				dto.setVisit_date(rs.getString(5));
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
