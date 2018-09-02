
package gdy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdy.vo.OrderVo;
import ljy.db.DBConnection;

public class OrderDao {

	
	private static OrderDao instance = new OrderDao();
	private OrderDao() {}
	
	public static OrderDao getInstance() {
		
		return instance;
	}
	
	
	public int checkpoint() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con= ljy.db.DBConnection.getConn();
			String sql = "select (sum(plus_point)-sum(minus_point)) point from order_point";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				int aa = rs.getInt("point");
				return aa;
			}
			
			
			
			
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			return -1;
			
			
			
		}finally {
			
			ljy.db.DBConnection.closeConn(rs, pstmt, con);
			
		}
		return 0;
		
		
		
	}
	
	public int corder(OrderVo vo,int p_num[],int oi_price[],int oi_amount[],int size,int plus_point,int minus_point ) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null; // 주문 상품 테이블
		PreparedStatement pstmt2 = null; // 적립금 테이블
		
		try {
		
			con = DBConnection.getConn();
			String sql ="INSERT INTO order_table VALUES (seq_num.nextval,?,sysdate,?,?, seq_d.nextval,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, vo.getO_id());
			pstmt.setString(2, vo.getO_addr());
			pstmt.setString(3, vo.getO_paymethod());
			pstmt.setString(4, vo.getO_phone());
			pstmt.setString(5, vo.getO_msg());
			pstmt.setInt(6, vo.getO_payment());
			pstmt.setInt(7,vo.getO_state());
			pstmt.setInt(8,vo.getO_amount());
			int n=pstmt.executeUpdate();
			System.out.println("pstmt 성공");
			
			
			
			if(n>0) {
				
				for(int i=0; i<size; i++) {
					
					
					String sql1 ="INSERT INTO order_info VALUES (oi_idxnum.nextval,seq_num.currval,?,?,?)";
					pstmt1 = con.prepareStatement(sql1);
					pstmt1.setInt(1, p_num[i]); // 상품 번호
					pstmt1.setInt(2,oi_price[i] ); // 상품가격
					pstmt1.setInt(3,oi_amount[i]); // 수량
					pstmt1.executeUpdate();
					System.out.println("pstmt1 성공"+i);
					
					
					
					
					
				}

				String sql2="INSERT INTO order_point VALUES (point_num.nextval,seq_num.currval,?,?)";
				
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, plus_point);
				pstmt2.setInt(2, minus_point);
				pstmt2.executeUpdate();
				return n;
				
			}
			
			
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
		}
		return 0;
	}
	
	
	
	public int order(OrderVo vo,int p_num ,int plus_point,int minus_point) {
		
		Connection con = null;
		PreparedStatement pstmt = null; //주문테이블
		PreparedStatement pstmt1 = null; // 주문 상품 테이블
		PreparedStatement pstmt2 = null; // 적립금 테이블
		
		
		try {
			
			con = ljy.db.DBConnection.getConn();
			String sql ="INSERT INTO order_table VALUES (seq_num.nextval,?,sysdate,?,?, seq_d.nextval,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, vo.getO_id());
			pstmt.setString(2, vo.getO_addr());
			pstmt.setString(3, vo.getO_paymethod());
			pstmt.setString(4, vo.getO_phone());
			pstmt.setString(5, vo.getO_msg());
			pstmt.setInt(6, vo.getO_payment());
			pstmt.setInt(7,vo.getO_state());
			pstmt.setInt(8,vo.getO_amount());
			
			
			int n=pstmt.executeUpdate();
			
			if(n>0) {
				
				String sql1 ="INSERT INTO order_info VALUES (oi_idxnum.nextval,seq_num.currval,?,?,?)";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setInt(1, p_num); // 상품 번호
				pstmt1.setInt(2, vo.getO_payment()/vo.getO_amount()); // 상품가격
				pstmt1.setInt(3,vo.getO_amount()); // 수량
				pstmt1.executeUpdate();
				
				
				String sql2="INSERT INTO order_point VALUES (point_num.nextval,seq_num.currval,?,?)";
				
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, plus_point);
				pstmt2.setInt(2, minus_point);
				pstmt2.executeUpdate();
				
				return n;
				
				
			}else {
				
				String rollback="rollback";
				pstmt = con.prepareStatement(rollback);
				pstmt.executeUpdate();
				
				return -1;
			}
			
			
			
			
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			return -1;
		}
	}
	
	
	
	
}
