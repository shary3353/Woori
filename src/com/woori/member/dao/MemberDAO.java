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
	//회원가입 
	
	
	
	//관리자 로그인,구매자 판매자 로그인
	public boolean login(String id, String pw) {
		boolean success = false;
		String sql ="SELECT id FROM member WHERE id=? AND pw=?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			success = rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
			
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return success;
	}
	public boolean clogin(String cid, String pw) {
		
		boolean success = false;
		
		String sql ="SELECT id FROM member WHERE id= ? AND pw=?";
		
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			success = rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return success;
	}
	public boolean slogin(String sid, String pw) {
		
		boolean success = false;
		
		String sql ="SELECT id FROM member WHERE id=? AND pw=?";
		
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			success = rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return success;
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

	public boolean sBlackRegist(String sid, String admin_id, String reason) {
		boolean success  = false;
		String sql = "INSERT INTO s_blacklist (b_idx, sid, admin_id, reason) values (s_blacklist_seq.nextval, ?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setString(2, admin_id);
			ps.setString(3, reason);
			if(ps.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
	
	public boolean sBlackUpdate(String sid, String admin_id, String reason) {
		boolean success = false;
		String sql = "UPDATE s_blacklist SET admin_id=?, reason=?, stack=stack+1, isblack=1 WHERE sid=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, admin_id);
			ps.setString(2, reason);
			ps.setString(3, sid);
			if(ps.executeUpdate() > 0) {
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
	
	
	public boolean cBlackRegist(String cid, String admin_id, String reason) {
		boolean success  = false;
		String sql = "INSERT INTO c_blacklist (b_idx, cid, admin_id, reason) values (c_blacklist_seq.nextval, ?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, admin_id);
			ps.setString(3, reason);
			if(ps.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}
	
	public boolean cBlackUpdate(String cid, String admin_id, String reason) {
		boolean success = false;
		String sql = "UPDATE c_blacklist SET admin_id=?, reason=?, stack=stack+1, isblack=1 WHERE cid=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, admin_id);
			ps.setString(2, reason);
			ps.setString(3, cid);
			if(ps.executeUpdate() > 0) {
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

	public boolean sBlackCancel(String id) {
		boolean success  = false;
		String sql = "UPDATE s_blacklist SET isblack=0 WHERE sid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			if(ps.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}

	public boolean cBlackCancel(String id) {
		boolean success  = false;
		String sql = "UPDATE c_blacklist SET isblack=0 WHERE cid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			if(ps.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}

	public boolean checkCID(String cid) {
		boolean check = false;
		String sql = "SELECT cid FROM c_blacklist WHERE cid=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if(rs.next())
				if(rs.getString("cid") != null) {	//있음
					System.out.println("cid:"+rs.getString("cid"));
					check = true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resClose();
		}
		return check;
	}
	
	public boolean checkSID(String sid) {
		boolean check = false;
		String sql = "SELECT sid FROM s_blacklist WHERE sid=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			if(rs.next())
				if(rs.getString("cid") != null) {	//있음
					System.out.println("sid:"+rs.getString("sid"));
					check = true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resClose();
		}
		return check;
	}

	public int getSellerNewStack(String sid) {
		int newStack = 0;
		String sql = "SELECT stack FROM s_blacklist WHERE sid=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			if(rs.next()) {
				newStack = rs.getInt("stack");
				System.out.println("newStack : "+newStack);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newStack;
	}
	
	public int getCustomerNewStack(String cid) {
		int newStack = 0;
		String sql = "SELECT stack FROM c_blacklist WHERE cid=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if(rs.next()) {
				newStack = rs.getInt("stack");
				System.out.println("newStack : "+newStack);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newStack;
	}


}
