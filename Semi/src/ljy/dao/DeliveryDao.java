package ljy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import gdy.vo.Order_infoVo;
import ljy.db.DBConnection;
import ljy.vo.OrderInfoVo;
import ljy.vo.OrderVo1;

public class DeliveryDao {
	public ArrayList<OrderVo1> orderInfoDelivery(String state) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OrderVo1> list = new ArrayList<>();
		String sql = "";
		if(state.equals("1")) {
			sql = "select * from order_table where o_state = 1";
		}else if(state.equals("2")){
			sql = "select * from order_table where o_state = 2";
		}else if(state.equals("3")) {
			sql = "select * from order_table where o_state = 3";
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
			System.out.println("orderInfoDelivery오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public int updateState(String oNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update order_table set o_state = o_state+1 where o_num = ?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, oNum);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("updateState오류");
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
	
	public int updateAmount(int pNum,int amount) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update products set p_stock = p_stock - ? where p_num = ?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setInt(2, pNum);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("updateAmount오류");
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
	
	public ArrayList<OrderInfoVo> getEachOrder(String o_num) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OrderInfoVo> list = new ArrayList<>();
		int oi_num = Integer.parseInt(o_num);
		String sql = "select * from order_info where oi_num = ?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, o_num);
			rs = ps.executeQuery();
			while(rs.next()) {
				int oi_p_num = rs.getInt("oi_p_num");
				int oi_price = rs.getInt("oi_price");
				int oi_amount = rs.getInt("oi_amount");
				OrderInfoVo vo = new OrderInfoVo(0, oi_num, oi_p_num, oi_price, oi_amount);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("getEachOrder오류");
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
}
