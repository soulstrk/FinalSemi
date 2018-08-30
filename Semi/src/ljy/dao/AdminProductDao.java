package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import ljy.vo.ProductVo;

import java.sql.Date;

import ljy.db.DBConnection;
import ljy.vo.OrderVo1;

public class AdminProductDao {
	public ArrayList<OrderVo1> allOrderInfo(String payIndex) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OrderVo1> list = new ArrayList<>();
		String sql = "";
		if(payIndex != null) {
			sql = "select * from order_table where o_paymethod ='"+payIndex+"'";
		}else {
			sql = "select * from order_table";
		}
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int oNum = rs.getInt("o_num");
				String oId = rs.getString("o_id");
				Date oDate = rs.getDate("o_date");
				String oAddr = rs.getString("o_addr");
				String oPaymethod = rs.getString("o_paymethod");
				String oPhone = rs.getString("o_phone");
				String oMsg = rs.getString("o_msg");
				int oAmount = rs.getInt("o_amount");
				int oDeliverynum = rs.getInt("o_deliverynum");
				int oPayment = rs.getInt("o_payment");
				int oState = rs.getInt("o_state");
				OrderVo1 vo = new OrderVo1(oNum, oId, oDate, oAddr, oPaymethod, oDeliverynum, oPhone, oMsg, oAmount, oPayment, oState);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("allOrderInfo오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public ArrayList<OrderVo1> dateOrderInfo(String date) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OrderVo1> list = new ArrayList<>();
		String sql = "select * from order_table where to_char(o_date) like ? || '%' order by o_date asc";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			while (rs.next()) {
				int oNum = rs.getInt("o_num");
				String oId = rs.getString("o_id");
				Date oDate = rs.getDate("o_date");
				String oAddr = rs.getString("o_addr");
				String oPaymethod = rs.getString("o_paymethod");
				String oPhone = rs.getString("o_phone");
				String oMsg = rs.getString("o_msg");
				int oAmount = rs.getInt("o_amount");
				int oDeliverynum = rs.getInt("o_deliverynum");
				int oPayment = rs.getInt("o_payment");
				int oState = rs.getInt("o_state");
				OrderVo1 vo = new OrderVo1(oNum, oId, oDate, oAddr, oPaymethod, oDeliverynum, oPhone, oMsg, oAmount, oPayment, oState);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("dateOrderInfo오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public ArrayList<ProductVo> allProductInfo(String col,String searchStr, String arrayStr,int startRow, int endRow) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<>();
		String sql = "";
		
		if(col != null && searchStr != null && arrayStr == null) {
			sql = "select * from products where "+col+"='"+searchStr+"'";
		}else if(col != null && searchStr == null && arrayStr != null){
			sql = "select * from (select AA.*,rownum rnum from (select * from products order by "+col+" "+arrayStr+")AA) where rnum >= ? and rnum <= ?";
		}else {
			sql = "select * from (select AA.*,rownum rnum from (select * from products order by p_num desc)AA) where rnum >= ? and rnum <= ?";
		}
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();
			while (rs.next()) {
				int pNum = rs.getInt("p_num");
				String pName = rs.getString("p_name");
				int pPrice = rs.getInt("p_price");
				int pStock = rs.getInt("p_stock");
				String pKind = rs.getString("p_kind");
				int pDiscountOk = rs.getInt("p_discountok");
				String pImage = rs.getString("p_image");
				String pArtist = rs.getString("p_artist");
				String pExplain = rs.getString("p_explain");
				int pBest = rs.getInt("p_best");
				int pDiscountRate = rs.getInt("p_discount_rate");
				ProductVo vo = new ProductVo(pNum, pName, pPrice, pStock, pKind, pDiscountOk, pArtist, pExplain, pBest, pImage, pDiscountRate);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("allProductInfo오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public int getCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from products";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("cnt");
				return count;
			}
			return -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		} finally{
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public int searchGetCount(String search) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(p_name) cnt from products where p_name like '%' || ? || '%'";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			rs = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("cnt");
				return count;
			}
			return -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("여긴가요");
			return -1;
		} finally{
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public ProductVo getProductInfo(int pNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductVo vo = null;
		String sql = "select * from products where p_num = ?";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				String pName = rs.getString("p_name");
				int pPrice = rs.getInt("p_price");
				int pStock = rs.getInt("p_stock");
				String pKind = rs.getString("p_kind");
				int pDiscountOk = rs.getInt("p_discountok");
				String pImage = rs.getString("p_image");
				String pArtist = rs.getString("p_artist");
				String pExplain = rs.getString("p_explain");
				int pBest = rs.getInt("p_best");
				int pDiscountRate = rs.getInt("p_discount_rate");
				vo = new ProductVo(pNum, pName, pPrice, pStock, pKind, pDiscountOk, pArtist, pExplain, pBest, pImage, pDiscountRate);
				return vo;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("getProductInfo오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public ArrayList<ProductVo> searchProductInfo(String search,int startRow, int endRow) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<>();
		String sql = "";
		sql = "select * from (select AA.*,rownum rnum from (select * from products where p_name like '%' || ? || '%' order by p_num desc)AA) where rnum >= ? and rnum <= ?";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			ps.setInt(2, startRow);
			ps.setInt(3, endRow);
			rs = ps.executeQuery();
			if(rs.next()) {
			do {	
				int pNum = rs.getInt("p_num");
				String pName = rs.getString("p_name");
				int pPrice = rs.getInt("p_price");
				int pStock = rs.getInt("p_stock");
				String pKind = rs.getString("p_kind");
				int pDiscountOk = rs.getInt("p_discountok");
				String pImage = rs.getString("p_image");
				String pArtist = rs.getString("p_artist");
				String pExplain = rs.getString("p_explain");
				int pBest = rs.getInt("p_best");
				int pDiscountRate = rs.getInt("p_discount_rate");
				ProductVo vo = new ProductVo(pNum, pName, pPrice, pStock, pKind, pDiscountOk, pArtist, pExplain, pBest, pImage, pDiscountRate);
				list.add(vo);
			} while(rs.next());
			return list;
		}else {
			return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("searchProductInfo오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	//searchWord 용 메소드
	public ArrayList<ProductVo> searchProduct(String word) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<>();
		String sql = "select * from products where p_name like ? || '%'";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, word);
			rs = ps.executeQuery();
			while (rs.next()) {
				int pNum = rs.getInt("p_num");
				String pName = rs.getString("p_name");
				int pPrice = rs.getInt("p_price");
				int pStock = rs.getInt("p_stock");
				String pKind = rs.getString("p_kind");
				int pDiscountOk = rs.getInt("p_discountok");
				String pImage = rs.getString("p_image");
				String pArtist = rs.getString("p_artist");
				String pExplain = rs.getString("p_explain");
				int pBest = rs.getInt("p_best");
				int pDiscountRate = rs.getInt("p_discount_rate");
				ProductVo vo = new ProductVo(pNum, pName, pPrice, pStock, pKind, pDiscountOk, pArtist, pExplain, pBest, pImage, pDiscountRate);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("searchProduct오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
}