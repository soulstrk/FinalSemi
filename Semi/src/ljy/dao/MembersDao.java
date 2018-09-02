package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import ljy.db.DBConnection;
import ljy.vo.MembersVo;

public class MembersDao {
	public int memberInsert(MembersVo vo) { //회원정보 인서트 메소드
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?,0,0,?,sysdate)";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getPostnum());
			ps.setString(5, vo.getAddr());
			ps.setString(6, vo.getPhone());
			ps.setString(7, vo.getEmail());
			ps.setInt(8, vo.getQNum());
			ps.setString(9, vo.getAnswer());
			ps.setString(10, vo.getGender());
			System.out.println(vo);
			int w = ps.executeUpdate();
			return w;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("memberinsert오류");
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
	
	public int memberDupliCheck(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from members where id = ?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return 1;
			}else {
				return -1;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("memberDupli오류");
			return -2;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public MembersVo loginChk(String id,String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MembersVo vo = null;
		String sql = "select * from members where id = ? and pwd = ?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				int postnum =rs.getInt("postnum");
				String addr = rs.getString("addr");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int qNum = rs.getInt("q_num");
				String answer = rs.getString("answer");
				int adminok = rs.getInt("adminok");
				int point = rs.getInt("point");
				String gender = rs.getString("gender");
				Date regdate = rs.getDate("regdate");
				vo = new MembersVo(id, pwd, name, addr, phone, email, qNum, answer, adminok, postnum, point, gender, regdate);
				return vo;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("loginChk오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public int deleteMember(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from members where id = ?";
		updateKickMember(id);
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("memberDupli오류");
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
	
	public int updateKickMember(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update members set adminok = -1 where id = ?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("updateKickMember오류");
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
}
