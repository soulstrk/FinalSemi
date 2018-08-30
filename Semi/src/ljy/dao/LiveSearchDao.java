package ljy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import ljy.db.DBConnection;
import ljy.vo.OrderInfoVo;
import ljy.vo.OrderVo1;

public class LiveSearchDao {
	public HashMap<Integer, String> getAccumulateSell() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<Integer, String> map = new HashMap<>();
		String sql = "select * from (select oi_p_num,sum(oi_amount) sellSum from order_info group by oi_p_num order by sellSum desc) where rownum > 0 and rownum <= 10";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer pNum = rs.getInt("oi_p_num");
				String pImage = getImagePath(pNum);
				map.put(pNum, pImage);
			}
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("getAccumulateSell오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public String getImagePath(int pNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select p_image from products where p_num = ?";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				String pImage = rs.getString("p_image");
				return pImage;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("getImagePath오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public String getProductName(int pNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select p_name from products where p_num = ?";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				String pName = rs.getString("p_name");
				return pName;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("getProductName오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
}
