package com.woori.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.reservation.dto.ReservationDTO;

public class ReservationDAO {
	
	//공통
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ReservationDAO() {//DB
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
	
	public ArrayList<ReservationDTO> sReservationList(String sid) {
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql= "SELECT r.r_idx, r.visit_date, r.reg_date, r.cid, p.p_idx, p.p_name, r.rs_idx, rs.status, s.sid" + 
				" FROM reservation r, reservation_status rs, product p, seller s" + 
				" WHERE r.rs_idx=rs.rs_idx AND p.sid=s.sid AND r.p_idx=p.p_idx AND s.sid = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReservationDTO dto = new ReservationDTO();
				dto.setR_idx(rs.getInt("r_idx"));//문의번호
				dto.setP_idx(rs.getInt("p_idx"));//상품번호
				dto.setP_name(rs.getString("p_name"));//상품이름
				dto.setCid(rs.getString("cid"));//구매자id
				dto.setVisit_date(rs.getString("visit_date").substring(0,10));//예약일
				dto.setReg_date(rs.getString("reg_date").substring(0, 10));//신청한 날짜
				dto.setStatus(rs.getString("status"));//예약현황
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
