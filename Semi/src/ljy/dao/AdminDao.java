package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import ljy.db.DBConnection;
import ljy.vo.MembersVo;

public class AdminDao {
	public ArrayList<MembersVo> allMembersInfo() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MembersVo> list = new ArrayList<>();
		String sql = "select * from members where id != 'admin'";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String addr = rs.getString("postnum") +" / "+ rs.getString("addr");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int point = rs.getInt("point");
				String gender = rs.getString("gender");
				Date regdate = rs.getDate("regdate");
				MembersVo vo = new MembersVo(id, pwd, name, addr, phone, email, 0, null, 0, null, point, gender, regdate);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("allMembersInfo오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public ArrayList<MembersVo> allMembersArray(String col,int num) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MembersVo> list = new ArrayList<>();
		String sql = "";
		if(num == 1) {
		sql = "select * from members where id != 'admin' order by "+col+" asc";
		}else if(num == 2) {
		sql = "select * from members where id != 'admin' order by "+col+" desc";
		}
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String addr = rs.getString("postnum") +" / "+ rs.getString("addr");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int point = rs.getInt("point");
				String gender = rs.getString("gender");
				Date regdate = rs.getDate("regdate");
				MembersVo vo = new MembersVo(id, pwd, name, addr, phone, email, 0, null, 0, null, point, gender, regdate);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("allMembersInfo오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
}
