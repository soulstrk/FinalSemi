package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	
}
