package gdy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ljy.db.DBConnection;
import gdy.vo.PaintingVo;

public class PaintingDao {
	
	private static PaintingDao instance = new PaintingDao();
	private PaintingDao() {}
	public static PaintingDao getInstance() {
		return instance;
	}
	
	
	public PaintingVo getdetail(int p_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBConnection.getConn();
			String sql = "select *from products where p_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				
				
				
				   String p_name = rs.getString("p_name"); //��ǰ�̸�
				   int p_price = rs.getInt("p_price");  // ��ǰ����
				   int p_stock = rs.getInt("p_stock"); //��ǰ���
				   String p_kind = rs.getString("p_kind"); //��ǰ ����
				   int p_discountOk = rs.getInt("p_discountOk"); //���� ����,
				   String p_artist = rs.getString("p_artist"); //�۰�
				   String p_explain = rs.getString("p_explain"); //��ǰ����
				   int p_best = rs.getInt("p_best"); //best��ǰ����
				   String p_image = rs.getString("p_image"); //��ǰ�̹���
				   PaintingVo vo = new PaintingVo(p_num,p_name,p_price,p_stock,p_kind,p_discountOk,p_artist,p_explain,
						   p_best,p_image);
				return vo;
			}
			
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}finally {
			
			DBConnection.closeConn(rs, pstmt, con);
		}
		return null;
		
		
		
		
	}
	
	public int getCount(String search, String keyword) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBConnection.getConn();
			
		if(keyword == null) {
			
			
			String sql = "select NVL(count(p_num),0) maxnum from products";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		}else {
			
			String searchCase =""; // ���� ���ڿ�
			if(search.equals("writer")) { // writer ��ǰ �˻��� ������
				
				searchCase = " = ? ";
			
			}else {
				
				searchCase = "like '%'||?||'%'";
				
			}
			
				String sql = "select NVL(count(p_num),0) maxnum from products";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			
			}
		
		
		if(rs.next()) {
			
			return rs.getInt("maxnum");
			
		}else {
			
			return 0;
		}
		
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			return -1;
		}finally {
			
			DBConnection.closeConn(rs, pstmt, con);
		}
		
	}
	
	public ArrayList<PaintingVo> list(int startRow, int endRow, String search, String keyword){
		
		Connection con = DBConnection.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PaintingVo> list = new ArrayList<PaintingVo>();
		
		try {
			
			if(search == null) {
				String sql =
						
						"select *from"+
						"("+
						"select AA.*, rownum rnum from"+
						"("+
						"select *from products "+
						"order by p_num desc"+
						")AA"+
						")where rnum>=? and rnum<=?";
				
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs=pstmt.executeQuery();
						
						
				
				
				
			}else {
				


				String sql =
						
						"select *from"+
						"("+
						"select AA.*, rownum rnum from"+
						"("+
						"select *from products "+
						"where "+search+" Like ?||'%'"+
						")AA"+
						")where rnum>=? and rnum<=?";
						
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs=pstmt.executeQuery();
				
				
			}
			
			while(rs.next()) {
				  int p_num = rs.getInt("p_num"); //��ǰ ��ȣ
				   String p_name = rs.getString("p_name"); //��ǰ�̸�
				   int p_price = rs.getInt("p_price");  // ��ǰ����
				   int p_stock = rs.getInt("p_stock"); //��ǰ���
				   String p_kind = rs.getString("p_kind"); //��ǰ ����
				   int p_discountOk = rs.getInt("p_discountOk"); //���� ����,
				   String p_artist = rs.getString("p_artist"); //�۰�
				   String p_explain = rs.getString("p_explain"); //��ǰ����
				   int p_best = rs.getInt("p_best"); //best��ǰ����
				   String p_image = rs.getString("p_image"); //��ǰ�̹���
				   
				   
				   
				   list.add(new PaintingVo(p_num,p_name,p_price,p_stock,p_kind,p_discountOk,p_artist,p_explain,
						   p_best,p_image));
				
			}
			
			
			
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}finally {
			
			DBConnection.closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	

}
