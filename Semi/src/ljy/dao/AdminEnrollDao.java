package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ljy.db.DBConnection;
import ljy.vo.ProductVo;

public class AdminEnrollDao {
	public int enrollProduct(ProductVo vo) { //상품정보 인서트 메소드
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into products values(product_seq.nextval,?,?,?,?,?,?,?,0,?,?)";
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
			ps.setString(8, vo.getpImage());
			ps.setInt(9, vo.getpDiscountRate());
			System.out.println(vo);
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
	
}
