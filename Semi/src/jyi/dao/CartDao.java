package jyi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jyi.vo.CartVo;
import jyi.vo.ProductsVo;
import jyi.vo.ViewsVo;
import ljy.db.DBConnection;

public class CartDao {
	private static CartDao instance=new CartDao();
	
	private CartDao() {}
	public static CartDao getInstance() {
		return instance;
	}
	
	//�ش� ���̵� ��ٱ��� ���� �ҷ�����
	public ArrayList<CartVo> getCart(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
			try {
				con=DBConnection.getConn();
				String sql="select * from cart where c_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				ArrayList<CartVo> list=new ArrayList<CartVo>();
				while(rs.next()) {
					int c_num=rs.getInt("c_num");
					int c_p_num=rs.getInt("c_p_num");
					int c_amount=rs.getInt("c_amount");
					Date c_date=rs.getDate("c_date");					
					CartVo vo=new CartVo(c_num, id, c_p_num, c_amount, c_date);
					list.add(vo);
				}
				return list;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				DBConnection.closeConn(rs, pstmt, con);
			}
	}
	
	//��ٱ��� �ش� ��ǰ ���� �ҷ�����
	public ArrayList<ProductsVo> getProducts(ArrayList<CartVo> list){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select * from products where p_num=?";
			pstmt=con.prepareStatement(sql);
			ArrayList<ProductsVo> plist=new ArrayList<ProductsVo>();
			for(CartVo vo : list) {
				pstmt.setInt(1, vo.getC_p_num());
				rs=pstmt.executeQuery();
				while(rs.next()) {
					int p_num=rs.getInt("p_num");
					String p_name=rs.getString("p_name");
					int p_price=rs.getInt("p_price");
					int p_stock=rs.getInt("p_stock");
					String p_kind=rs.getString("p_kind");
					int p_discountOk=rs.getInt("p_discountOk");
					String p_artist=rs.getString("p_artist");
					String p_explain=rs.getString("p_explain");
					int p_best=rs.getInt("p_best");
					String p_image=rs.getString("p_image");
					int p_discount_rate=rs.getInt("p_discount_rate");
					ProductsVo v=new ProductsVo(p_num, p_name, p_price, p_stock,
							p_kind, p_discountOk, p_artist, p_explain, 
							p_best, p_image, p_discount_rate);
					plist.add(v);
				}
			}
			return plist;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//��ٱ��� ��ü �Ǵ� �κ� �����ϱ�
	public int delete(int c_num, String c_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="delete from cart where c_num=?"; //�κл���
			if(c_num==-1) { //��ü����
				sql="delete from cart where c_id=?";
			}
			pstmt=con.prepareStatement(sql);
			if(c_num==-1) { //��ü����
				pstmt.setString(1, c_id);
			}else { //�κл���
				pstmt.setInt(1, c_num);
			}
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	//��ǰ ���� ����
	public int update(int c_num, int c_amount) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="update cart set c_amount=? where c_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, c_amount);
			pstmt.setInt(2, c_num);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}

	//�ֱ� �� 5�� ��ǰ��ȣ �ҷ�����
	public ArrayList<ViewsVo> getView(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select * from (select aa.*, rownum rnum from ("+
						"select * from views where id=? order by v_snum desc) aa) "+
						"where rnum >=0 and rnum<=4";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<ViewsVo> list=new ArrayList<ViewsVo>();
			while(rs.next()) {
				int v_num=rs.getInt("v_num");
				int p_num=rs.getInt("p_num");
				int v_snum=rs.getInt("v_snum");
				ViewsVo vo=new ViewsVo(v_num, id, p_num, v_snum);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
}
