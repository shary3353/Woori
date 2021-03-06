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
	
	public void resClose() { //자원 반납
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//구매자 회원가입 
	public int cjoin(CustomerDTO dto) {
		String sql = "INSERT INTO Consumer(cid,pw,name,gender,email,birthday,phone)VALUES(?,?,?,?,?,?,?)";
		int success = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getCid());//?대응 
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getEmail());
			ps.setString(6, dto.getBirthday());
			ps.setString(7, dto.getPhone());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	
		
		}

	
	//판매자 회원가입
	public int sjoin(SellerDTO dto) {
		String sql = "INSERT INTO seller(Sid,pw,name,gender,store_call,birthday,phone,email)VALUES(?,?,?,?,?,?,?,?)";
		int success = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getSid());//?대응 
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getStore_call());
			ps.setString(6, dto.getBirthday());
			ps.setString(7, dto.getPhone());
			ps.setString(8, dto.getEmail());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
	
	
	public boolean overlay(String cid) throws SQLException {//중복체크
		boolean success = false;
		String sql = "SELECT cid FROM consumer WHERE cid=?";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, cid);// ?대응
		rs = ps.executeQuery();
		success = rs.next();
		System.out.println(rs.next());
	
	      return !success;
	}
	public boolean soverlay(String sid) throws SQLException {//중복체크
		boolean success = false;
		String sql = "SELECT sid FROM seller WHERE sid=?";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, sid);// ?대응
		rs = ps.executeQuery();
		success = rs.next();
		System.out.println(rs.next());
	
	      return !success;
	
	}


	public HashMap<String, Boolean> clogin(String cid, String pw) {//구매자 로그인		
		String sql ="SELECT c.cid, b.isblack FROM consumer c, c_blacklist b WHERE c.cid = b.cid(+) AND c.cid=? AND c.pw=?";
		
		boolean success = false;
		boolean isblack = false;
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if(rs.next()) {				
				success = true;
				System.out.println("구매자로그인성공");
				if(rs.getInt("isblack") == 1) {
					isblack = true;
					System.out.println("블랙리스트회원이네..");
				}
			}
			map.put("success", success);
			map.put("isblack", isblack);
			System.out.println("map:"+map);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return map;
	}
	public HashMap<String, Boolean> slogin(String sid, String pw) {//판매자 로그인
		String sql ="SELECT s.sid, b.isblack FROM seller s, s_blacklist b WHERE s.sid = b.sid(+) AND s.sid=? AND s.pw=?";
		
		boolean success = false;
		boolean isblack = false;
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if(rs.next()) {				
				success = true;
				System.out.println("구매자로그인성공");
				if(rs.getInt("isblack") == 1) {
					isblack = true;
					System.out.println("블랙리스트회원이네..");
				}
			}
			map.put("success",success);
			map.put("isblack",isblack);
			System.out.println("map:"+map);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return map;
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

	public int sPfpUpdate(SellerDTO dto) { //판매자 회원정보 수정
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
		return success;
	}

	public CustomerDTO cDetail(String cid) {
		String sql = "SELECT cid, gender, email, to_char(birthday, 'yyyy-mm-dd') birthday, phone, name FROM consumer WHERE cid=?";
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
				dto.setName(rs.getString(6));
			}
			System.out.println(dto.getCid()+" / "+dto.getGender()+" / "+dto.getEmail()+" / "+dto.getBirthday()+" / "+dto.getPhone()+" / "+dto.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return dto;
	}

	public CustomerDTO cUpadateForm(String cid, String pw) {
		String sql = "SELECT cid, gender, email, to_char(birthday, 'yyyy-mm-dd') birthday, phone, name FROM consumer WHERE cid=? and pw=?";
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
				dto.setName(rs.getString(6));
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

	public CustomerDTO getCustomer(String cid) {
		CustomerDTO dto = new CustomerDTO();
		String sql = "SELECT cid, to_char(birthday, 'YYYY-MM-DD') AS birthday, name, gender, email, phone FROM consumer WHERE cid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setCid(rs.getString("cid"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}
	
	public SellerDTO getSeller(String sid) {
		SellerDTO dto = new SellerDTO();
		String sql = "SELECT sid, email, to_char(birthday, 'YYYY-MM-DD') AS birthday, phone, gender, store_call, name, to_char(reg_date, 'YYYY-MM-DD') AS reg_date FROM seller WHERE sid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setSid(rs.getString("sid"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setStore_call(rs.getString("store_call"));
				dto.setReg_date(rs.getString("reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	public boolean adminLogin(String adminID, String adminPW) {
		boolean success = false;
		String sql = "SELECT id FROM admin WHERE id=? AND pw=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminID);
			ps.setString(2, adminPW);
			rs = ps.executeQuery();
			if(rs.next()) {
				success = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}

	

}
