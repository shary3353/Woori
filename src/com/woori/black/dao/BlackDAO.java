package com.woori.black.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.woori.member.dto.BlackListDTO;

public class BlackDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int pagePerCnt = 10;
	
	public BlackDAO() {
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
	
	
	public HashMap<String, Object> bList(int group) {
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<BlackListDTO> bList = new ArrayList<>();
		//String sql = "SELECT sid AS id, reason, stack, to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM s_blacklist WHERE (sid, reg_date) in (SELECT sid, MAX(reg_date) FROM s_blacklist WHERE 1=1 GROUP BY sid)";
		//String sql = "SELECT sid AS id, reason, stack, to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM s_blacklist WHERE (sid, reg_date) in (SELECT sid, MAX(reg_date) FROM s_blacklist WHERE 1=1 GROUP BY sid)"
		//			+" UNION " +"SELECT cid AS id, reason, stack,to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM c_blacklist WHERE (cid, reg_date) in (SELECT cid, MAX(reg_date) FROM c_blacklist WHERE 1=1 GROUP BY cid)";
		String sql = "SELECT * FROM( SELECT ROW_NUMBER() OVER(ORDER BY reg_date DESC) AS rnum, id, reason, stack, to_char(reg_date, 'YYYY-MM-DD') AS reg_date, admin_id FROM (SELECT sid AS id, reason, stack, reg_date, admin_id FROM s_blacklist WHERE (sid, reg_date) in (SELECT sid, MAX(reg_date) FROM s_blacklist WHERE 1=1 GROUP BY sid) AND isblack=1 UNION SELECT cid AS id, reason, stack, reg_date, admin_id FROM c_blacklist WHERE (cid, reg_date) in (SELECT cid, MAX(reg_date) FROM c_blacklist WHERE 1=1 GROUP BY cid)  AND isblack=1 )) WHERE rnum BETWEEN ? AND ?";
		
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
				BlackListDTO dto = new BlackListDTO();
				dto.setId(rs.getString("id"));
				dto.setReason(rs.getString("reason"));
				dto.setStack(rs.getInt("stack"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setAdmin_id(rs.getString("admin_id"));
				bList.add(dto);
			}
			int maxPage = getMaxBlackPage();
			map.put("maxBlackPage", maxPage);
			map.put("bList", bList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return map;
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

	private int getMaxBlackPage() {
		String sql = "SELECT count(sid) AS cnt FROM s_blacklist WHERE (sid, reg_date) in (SELECT sid, MAX(reg_date) FROM s_blacklist WHERE 1=1 GROUP BY sid)"
				+ " UNION "
				+ "SELECT count(cid) AS cnt FROM c_blacklist WHERE (cid, reg_date) in (SELECT cid, MAX(reg_date) FROM c_blacklist WHERE 1=1 GROUP BY cid)";
		int max = 0;
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				cnt += rs.getInt("cnt");
			}
			if (cnt != 0) {
				max = (int) Math.ceil(cnt / (double) pagePerCnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
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

	public int getCBlack(String cid) {
		// TODO Auto-generated method stub
		return 0;
	}


}
