package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ljy.db.DBConnection;
import ljy.vo.ProductVo;

public class AdminEnrollDao {
	public int enrollProduct(ProductVo vo) { //상품정보 인서트 메소드
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into products values(products_num.nextval,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getpName());
			ps.setInt(2, vo.getpPrice());
			ps.setInt(3, vo.getpStock());
			ps.setString(4, vo.getpKind());
			ps.setInt(5, vo.getpDiscountok());
			ps.setString(6, vo.getpArtist());
			ps.setString(7, vo.getpExplain());
			ps.setInt(8, vo.getpBest());
			ps.setString(9, vo.getpImage());
			ps.setInt(10, vo.getpDiscountRate());
			int w = ps.executeUpdate();
			return w;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("enrollProduct오류");
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
	
	public int productUpdate(ProductVo vo) { //상품정보 인서트 메소드
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update products set p_name=?, p_price=?, p_stock=?,"
				+ "p_kind=?, p_discountok=?, p_artist=?, p_explain=? ,p_discount_rate=?, p_best = ? where p_num=?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getpName());
			ps.setInt(2, vo.getpPrice());
			ps.setInt(3, vo.getpStock());
			ps.setString(4, vo.getpKind());
			ps.setInt(5, vo.getpDiscountok());
			ps.setString(6, vo.getpArtist());
			ps.setString(7, vo.getpExplain());
			ps.setInt(8, vo.getpDiscountRate());
			ps.setInt(9, vo.getpNum());
			ps.setInt(10, vo.getpBest());
			int w = ps.executeUpdate();
			return w;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("productUpdate오류");
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
	
}
