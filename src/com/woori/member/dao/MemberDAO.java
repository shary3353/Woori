package com.woori.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.member.dto.CustomerDTO;
import com.woori.member.dto.SellerDTO;

//회원가입, 로그인, 회원정보 상세보기, 회원정보 수정 
public class MemberDAO {
	
	//공통
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MemberDAO() {//db접근
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void resClose() { //자원 반납
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//판매자메인 - 판매자 회원정보 상세보기, 수정보기 요청
	public SellerDTO sPfpDetail(String sid) {
		String sql = "SELECT sid, pw, name, birthday, gender, email,phone, store_call FROM seller WHERE sid=?";
		
		SellerDTO dto = new SellerDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setSid(rs.getString("sid"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getString("birthday").substring(0,10));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setStore_call(rs.getString("store_call"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}		
		return dto;
	}

	public void sPfpUpdate(SellerDTO dto) { //판매자 회원정보 수정
		String sql = "UPDATE seller SET pw=?, name=?, email=?, phone=?, store_call=? WHERE sid=?";
		
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getEmail());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getStore_call());
			ps.setString(6, dto.getSid());
			success = ps.executeUpdate();
			System.out.println("업데이트 완료 :"+success);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
	}

	public CustomerDTO cDetail(String cid) {
		String sql = "SELECT cid, gender, email, to_char(birthday, 'yyyy-mm-dd') birthday, phone FROM consumer WHERE cid=?";
		CustomerDTO dto = new CustomerDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setCid(rs.getString(1));
				dto.setGender(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setBirthday(rs.getString(4));
				dto.setPhone(rs.getString(5));
			}
			System.out.println(dto.getCid()+" / "+dto.getGender()+" / "+dto.getEmail()+" / "+dto.getBirthday()+" / "+dto.getPhone());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return dto;
	}

	public CustomerDTO cUpadateForm(String cid, String pw) {
		String sql = "SELECT cid, gender, email, to_char(birthday, 'yyyy-mm-dd') birthday, phone FROM consumer WHERE cid=? and pw=?";
		CustomerDTO dto = new CustomerDTO();
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setCid(rs.getString(1));
				dto.setGender(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setBirthday(rs.getString(4));
				dto.setPhone(rs.getString(5));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return dto;
	}

	public int cUpdateInfo(CustomerDTO dto) {
		String sql = "UPDATE consumer SET pw=?, email=?, phone=? WHERE cid=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,dto.getPw());
			ps.setString(2, dto.getEmail());
			ps.setString(3, dto.getPhone());
			ps.setString(4, dto.getCid());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	

}
