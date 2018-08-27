package jyi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import ljy.db.DBConnection;
import jyi.vo.CustomerBoardVo;
import jyi.vo.OrderInfoVo;
import jyi.vo.OrderPointVo;
import jyi.vo.OrderVo;
import jyi.vo.ProductsVo;
import jyi.vo.ReviewVo;
import ljy.vo.MembersVo;

//�̱�������DAO
public class MyPageDao {
	private static MyPageDao instance = new MyPageDao();

	private MyPageDao() {
	}

	public static MyPageDao getInstance() {
		return instance;
	}

	// ���������� ȸ�� ���� �ҷ�����
	public MembersVo getInfo(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from members where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				int postnum = rs.getInt("postnum");
				String addr = rs.getString("addr");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int point = rs.getInt("point");
				String gender = rs.getString("gender");
				Date regdate = rs.getDate("regdate");
				MembersVo vo = new MembersVo(id, pwd, name, addr, phone, email, 0, null, 0, postnum, point, gender,
						regdate);
				return vo;
			}
			return null;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// ���������� ȸ������ �����ϱ�
	public int update(MembersVo vo, String pwd1) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			con.setAutoCommit(false);
			String sql = "update members " + "set postnum=?, addr=?, phone=?, email=?, pwd=?" + "where id=? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostnum());
			pstmt.setString(2, vo.getAddr());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, pwd1); // �� ��й�ȣ
			pstmt.setString(6, vo.getId());
			pstmt.setString(7, vo.getPwd());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				con.commit();
			} else {
				con.rollback();
			}
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			try {
				con.rollback();
			} catch (SQLException s) {
				System.out.println(s.getMessage());
			}
			return -1;
		} finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}

	// ȸ��Ż�� --> �����ڹ�ȣ -1�� �ٲٱ�
	public int delete(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			con.setAutoCommit(false);
			String sql = "update members set adminok = -1 where id=? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				con.commit();
			} else {
				con.rollback();
			}
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			try {
				con.rollback();
			} catch (SQLException s) {
				System.out.println(s.getMessage());
			}
			return -1;
		} finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}

	// --------------------------------------------------------------------------------

	// ���������� ȸ�� �ֹ���ü���� �ҷ�����
	public ArrayList<OrderVo> getOrderList(String id, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql="";
			if(startRow==0 && endRow==0) {
				sql="select * from order_table where o_id=? order by o_num desc";
			}else {
				sql = "select * from(select aa.*, rownum rnum from("+
						"select * from order_table where o_id=? order by o_num desc)aa ) "+
						"where rnum >=? and rnum <=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			if(!(startRow==0 && endRow==0)) {
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			rs = pstmt.executeQuery();
			ArrayList<OrderVo> list = new ArrayList<OrderVo>();
			while (rs.next()) {
				int o_num = rs.getInt("o_num");
				String o_id = id;
				Date o_date = rs.getDate("o_date");
				String o_addr = rs.getString("o_addr");
				String o_paymethod = rs.getString("o_paymethod");
				int o_deliverynum = rs.getInt("o_deliverynum");
				String o_phone = rs.getString("o_phone");
				String o_msg = rs.getString("o_msg");
				int o_payment = rs.getInt("o_payment");
				int o_state = rs.getInt("o_state");
				int o_amount = rs.getInt("o_amount");
				OrderVo vo = new OrderVo(o_num, o_id, o_date, o_addr, o_paymethod, o_deliverynum, o_phone, o_msg,
						o_payment, o_state, o_amount);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// ���������� ȸ�� �ֹ����� �󼼺��� �ֹ��� ��ǰ�� ��ȣ �ҷ�����
	public ArrayList<Integer> getProductNum(int o_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from order_info where oi_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, o_num);
			rs = pstmt.executeQuery();
			ArrayList<Integer> list = new ArrayList<Integer>();
			while (rs.next()) {
				int oi_p_num = rs.getInt("oi_p_num");
				list.add(oi_p_num);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// ���������� ȸ�� �ֹ����� �󼼺���-�ش� �ֹ���ȣ ��ǰ���� �ҷ�����
	public ArrayList<ProductsVo> getOrderInfo(ArrayList<Integer> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from products where p_num=?";
			pstmt = con.prepareStatement(sql);
			ArrayList<ProductsVo> voList = new ArrayList<ProductsVo>();
			for (int i : list) {
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int p_num = rs.getInt("p_num");
					String p_name = rs.getString("p_name");
					int p_price = rs.getInt("p_price");
					int p_discountOk = rs.getInt("p_discountOk");
					String p_artist = rs.getString("p_artist");
					int p_best = rs.getInt("p_best");
					String p_image = rs.getString("p_image");
					int p_discount_rate = rs.getInt("p_discount_rate");
					ProductsVo vo = new ProductsVo(p_num, p_name, p_price, 0, null, p_discountOk, p_artist, null,
							p_best, p_image, p_discount_rate);
					voList.add(vo);
				}
			}
			return voList;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// ���������� ȸ�� �ֹ����� �󼼺��� �ֹ� ���� �ҷ�����
	public OrderInfoVo getOrderProductInfo(int o_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println(o_num);
			con = DBConnection.getConn();
			String sql = "select * from order_info where oi_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, o_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int oi_idxnum = rs.getInt("oi_idxnum");
				int oi_num = rs.getInt("oi_num");
				int oi_p_num = rs.getInt("oi_p_num");
				int oi_price = rs.getInt("oi_price");
				int oi_amount = rs.getInt("oi_amount");
				OrderInfoVo vo = new OrderInfoVo(oi_idxnum, oi_num, oi_p_num, oi_price, oi_amount);
				return vo;
			}
			return null;
		} catch (SQLException se) {
			// System.out.println(list.get(0)[0]);
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// �ֱ� �ֹ� ���� ���� �ҷ�����
	public OrderVo getLatelyOrder(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from(select ot.*, rownum rnum from order_table ot where o_id=? order by o_num desc)  where rnum=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int o_num = rs.getInt("o_num");
				String o_id = id;
				Date o_date = rs.getDate("o_date");
				String o_addr = rs.getString("o_addr");
				String o_paymethod = rs.getString("o_paymethod");
				int o_deliverynum = rs.getInt("o_deliverynum");
				String o_phone = rs.getString("o_phone");
				String o_msg = rs.getString("o_msg");
				int o_payment = rs.getInt("o_payment");
				int o_state = rs.getInt("o_state");
				int o_amount = rs.getInt("o_amount");
				OrderVo vo = new OrderVo(o_num, o_id, o_date, o_addr, o_paymethod, o_deliverynum, o_phone, o_msg,
						o_payment, o_state, o_amount);
				return vo;
			}
			return null;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// ------------------------------------------------------------------
	// ���������� ������ ���� �ҷ�����
	public ArrayList<OrderPointVo> getPointList(ArrayList<OrderVo> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from order_point where o_num=? order by o_num desc";
			pstmt = con.prepareStatement(sql);
			ArrayList<OrderPointVo> opvList = new ArrayList<OrderPointVo>();
			for (OrderVo vo : list) {
				pstmt.setInt(1, vo.getO_num());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int num = rs.getInt("num");
					int o_num = rs.getInt("o_num");
					int plus_point = rs.getInt("plus_point");
					int minus_point = rs.getInt("minus_point");
					OrderPointVo opv = new OrderPointVo(num, o_num, plus_point, minus_point);
					opvList.add(opv);
					System.out.println(opv);
				}
			}
			return opvList;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// ------------------------------------------------------------------
	// ���������� ȸ�� ������ �Խù� �ҷ�����
	public ArrayList<CustomerBoardVo> getCustomerBoardList(String id, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from(select aa.*, rownum rnum from("+
					"select * from customer_board where b_is=0 and b_id=? order by b_num desc) aa) "+
					"where rnum >=? and rnum <=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<CustomerBoardVo> list = new ArrayList<CustomerBoardVo>();
			while (rs.next()) {
				int b_num = rs.getInt("b_num");
				String b_id = rs.getString("b_id");
				Date b_date = rs.getDate("b_date");
				String b_content = rs.getString("b_content");
				int b_ref = rs.getInt("b_ref");
				String b_title = rs.getString("b_title");
				int b_is = rs.getInt("b_is");
				int b_public_private = rs.getInt("b_public_private");
				int b_result = rs.getInt("b_result");
				CustomerBoardVo vo = new CustomerBoardVo(b_num, b_id, b_date, b_content, b_ref, b_title, b_is,
						b_public_private, b_result);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//���������� ȸ�� �Խù� �� ���� ���ϱ�(�ֹ�, faq, point)
	public int getMyPageCount(String id, String page){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="";
			if(page.equals("orderList")) { //�ֹ�
				sql="select NVL(count(o_num),0) count from order_table where o_id=?";
			}else if(page.equals("faqList")){ //������ �Խù�
				sql="select NVL(count(b_num),0) count from customer_board where b_id=?";
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			int n=0;
			if(rs.next()) {
				n=rs.getInt("count");
			}
			System.out.println(n+"!!!");
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//���������� �� ��ǰ�ı� �ҷ�����
	public ArrayList<ReviewVo> review(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select * from review where id=? order by adminok desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<ReviewVo> list=new ArrayList<ReviewVo>();
			while(rs.next()) {
				int adminOk=rs.getInt("adminOk");
				int product_num=rs.getInt("product_num");
				String content=rs.getString("content");
				ReviewVo vo=new ReviewVo(adminOk, product_num, id, content);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//���������� ��ǰ�ı� �����ϱ�
	public int reviewDelete(String id,int adminOk) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="delete from review where id=? and adminok=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, adminOk);
			int n=pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
}
