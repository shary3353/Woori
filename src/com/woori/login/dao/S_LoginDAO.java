package com.woori.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class S_LoginDAO {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	public S_LoginDAO() {
		
		
		try {
			Context ctx = new InitialContext();//Context 객체화
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();//데이터 소스를 통해 커넥션 가져오기
		} catch (Exception e) {//예외처리
			
			e.printStackTrace();
		}
	}

	public boolean slogin(String sid, String pw) {
		
		boolean success = false;//반환 값이 될 success를 false로 선언
		
		String sql ="SELECT id FROM member WHERE id=? AND pw=?";//쿼리 준비
		
		try {
			ps= conn.prepareStatement(sql);//connection을 통해 prepareStatement 준비
			ps.setString(1, sid);//매개 변수로 받은 값을 ? 에 대응
			ps.setString(2, pw);//매개 변수로 받은 값을 ? 에 대응
			rs = ps.executeQuery();//쿼리 실행
			success = rs.next();//값이 있다면 true로 반환 ,없으면 false로 반환
		}catch(SQLException e) {//예외 처리
			e.printStackTrace();
		}finally {
			try {
				//자원 정리
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return success;
	}

}
	
