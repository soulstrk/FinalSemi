package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ljy.db.DBConnection;
import ljy.vo.ProductVo;

public class MainListDao {
	public ArrayList<ProductVo> getBestProducts () {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<>();
		String sql = "select * from products where p_best = 1";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("p_num");
				String name = rs.getString("p_name");
				int price = rs.getInt("p_price");
				int stock = rs.getInt("p_stock");
				String kind = rs.getString("p_kind");
				int disconut = rs.getInt("p_discountOk");
				String artist = rs.getString("p_artist");
				String explain = rs.getString("p_explain");
				int best = rs.getInt("p_best");
				String image = rs.getString("p_image");
				int discountRate = rs.getInt("p_discount_rate");
				ProductVo vo = new ProductVo(num, name, price, stock, kind, disconut, artist, explain, best, image, discountRate);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("getBestProducts오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public ArrayList<ProductVo> getSaleList() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<>();
		String sql = "select * from products where p_discountok = 1 and rownum <= 10 order by p_discount_rate desc";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("p_num");
				String name = rs.getString("p_name");
				int price = rs.getInt("p_price");
				int stock = rs.getInt("p_stock");
				String kind = rs.getString("p_kind");
				int disconut = rs.getInt("p_discountOk");
				String artist = rs.getString("p_artist");
				String explain = rs.getString("p_explain");
				int best = rs.getInt("p_best");
				String image = rs.getString("p_image");
				int discountRate = rs.getInt("p_discount_rate");
				ProductVo vo = new ProductVo(num, name, price, stock, kind, disconut, artist, explain, best, image, discountRate);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("getSaleProducts오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
}
