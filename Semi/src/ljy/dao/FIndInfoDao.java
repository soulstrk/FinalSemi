package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ljy.db.DBConnection;

public class FIndInfoDao {
	public String getQuestion(String email,String phone) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from members where email = ? and phone = ?";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("id");
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public String getPwd(String q_num,String id, String answer) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from members where q_num = ? and id = ? and answer = ?";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, q_num);
			ps.setString(2, id);
			ps.setString(3, answer);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("pwd");
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
}
