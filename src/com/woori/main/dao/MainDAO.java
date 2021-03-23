package com.woori.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.product.dto.ProductDTO;


public class MainDAO {
	
	//자주쓰는 것들 필드에 미리선언
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//생성자
	public MainDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//자원종료
	public void resClose() {
		try {
			if(conn!=null) {conn.close();}
			if(ps!=null) {ps.close();}
			if(rs!=null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public HashMap<String, Object> Cmain(int i) {
		
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT p.p_idx,p.p_name,p.likes,p.p_price,p.is_sold,t.orifilename,t.newfilename,rk FROM (SELECT p_idx,p_name,likes,p_price,is_sold,ROW_NUMBER() OVER(ORDER BY likes DESC) AS rk FROM product WHERE is_sold=1) p JOIN thumbfile t ON p.p_idx = t.p_idx(+) WHERE rk=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setLikes(rs.getInt("likes"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
				//사진이 없으면 파일 자체가 나타나지 않음
				list.add(dto);
			}
			System.out.println("list-size : " + list.size());
			map.put("list"+i, list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}


	public ProductDTO  Citemdetail(String pidx) {
		ProductDTO dto = null;
		String sql="SELECT p.p_idx,p.p_name,p.p_content,p.p_price,p.sid,t.orifilename,t.newfilename FROM product p JOIN thumbfile t ON p.p_idx = t.p_idx(+) WHERE p.p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pidx));
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setP_content(rs.getString("p_content"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setSid(rs.getString("sid"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
		
	}


	public ProductDTO Creservationdetail(String pidx) {
		ProductDTO dto = null;
		String sql="SELECT p.p_idx,p.p_name,p.p_price,p.sid,t.orifilename,t.newfilename FROM product p JOIN thumbfile t ON p.p_idx = t.p_idx WHERE p.p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pidx));
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setSid(rs.getString("sid"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}


	public boolean Creservation(String pidx, String cid, String visitdate) {
		boolean success = false;
		String sql="INSERT INTO reservation(r_idx,reg_date,visit_date,cid,p_idx,rs_idx)VALUES(reservation_seq.NEXTVAL,SYSDATE,?,?,?,0)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setDate(1,Date.valueOf(visitdate));
			ps.setString(2, cid);
			ps.setInt(3, Integer.parseInt(pidx));
			if(ps.executeUpdate()>0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		System.out.println("예약추가 : " + success);
		return success;
	}


	public ArrayList<ProductDTO> mainSearch(String searchname) {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql="SELECT p.p_idx,p.p_name,p.likes,p.p_price,t.orifilename,t.newfilename \r\n" + 
				"FROM product p JOIN thumbfile t ON p.p_idx = t.p_idx(+) WHERE p.p_name LIKE ? AND is_sold=1";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, searchname);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setLikes(rs.getInt("likes"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
	}


	public boolean likeconfirm(String cid, String pidx) {
		boolean success = false;
		String sql="SELECT cid,p_idx FROM likes WHERE cid=? AND p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, Integer.parseInt(pidx));
			
			rs = ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resClose();
		}
		return !success;
		
	}


	public int likesTableAdd(String cid, String pidx) {
		int success = 0;
		String sql="INSERT INTO likes(cid,p_idx) VALUES(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, Integer.parseInt(pidx));
			if(ps.executeUpdate()>0) {
				success=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
		
		
	}


	public boolean likePlus(String pidx) {
		boolean success = false;
		String sql="UPDATE product SET likes = likes+1 WHERE p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pidx));
			if(ps.executeUpdate()>0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
		
	}


	public int likesTableDel(String cid, String pidx) {
		int success = 0;
		String sql="DELETE FROM likes WHERE cid=? AND p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, Integer.parseInt(pidx));
			if(ps.executeUpdate()>0) {
				success=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;		
	}


	public boolean likeMinus(String pidx) {
		boolean success = false;
		String sql="UPDATE product SET likes = likes-1 WHERE p_idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pidx));
			if(ps.executeUpdate()>0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return success;
	}


	public ArrayList<ProductDTO> categorySearch(String c_idx) {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql="SELECT p.p_idx,p.p_name,p.likes,p.p_price,t.orifilename,t.newfilename " + 
				"FROM product p JOIN thumbfile t ON p.p_idx = t.p_idx WHERE c_idx=? AND is_sold=1";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(c_idx));
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_idx(rs.getInt("p_idx"));
				dto.setP_name(rs.getString("p_name"));
				dto.setLikes(rs.getInt("likes"));
				dto.setP_price(rs.getInt("p_price"));
				dto.setOriFileName(rs.getString("orifilename"));
				dto.setNewFileName(rs.getString("newfilename"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
		
	}

	
	
}
