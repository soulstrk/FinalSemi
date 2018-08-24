package jyi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import ljy.db.DBConnection;
import jyi.vo.CustomerBoardVo;

//싱글톤패턴DAO
public class CustomerBoardDao {
	private static CustomerBoardDao instance = new CustomerBoardDao();

	private CustomerBoardDao() {
	}

	public static CustomerBoardDao getInstance() {
		return instance;
	}
	
	//고객센터 전체 게시물 수 불러오기
	public int getCountNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select NVL(count(b_num),0) countnum from customer_board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int n=0;
			if(rs.next()) {
				n=rs.getInt("countnum");
			}
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//고객센터 전체 게시글 불러오기
	public ArrayList<CustomerBoardVo> list(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from("+
						 "select AA.*, rownum rnum from("+
						 "select * from customer_board where b_is=0 order by b_num desc) AA)"+
						 "where rnum >=? and rnum <=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
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
				int b_public_private=rs.getInt("b_public_private");
				int b_result=rs.getInt("b_result");
				CustomerBoardVo vo
					= new CustomerBoardVo(b_num, b_id, b_date, b_content, b_ref, b_title, b_is, b_public_private,b_result);
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

	//회원 개인 게시물 불러오기
	public CustomerBoardVo info(int b_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select * from customer_board where b_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String b_id = rs.getString("b_id");
				Date b_date = rs.getDate("b_date");
				String b_content = rs.getString("b_content");
				int b_ref = rs.getInt("b_ref");
				String b_title = rs.getString("b_title");
				int b_is = rs.getInt("b_is");
				int b_public_private=rs.getInt("b_public_private");
				int b_result=rs.getInt("b_result");
				CustomerBoardVo vo 
					= new CustomerBoardVo(b_num, b_id, b_date, b_content, b_ref, b_title, b_is, b_public_private,b_result);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//고객센터 글번호 중 가장 큰 번호 불러오기 => +1해서 새글번호로 입력
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select NVL(max(b_num),0) maxnum from customer_board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int n=0;
			if(rs.next()) {
				n=rs.getInt("maxnum");
			}
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	
	//고객센터 글쓰기
	public int insert(CustomerBoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=DBConnection.getConn();
			con.setAutoCommit(false);
			String b_id=vo.getB_id();
			int b_num=vo.getB_num(); 
			int b_ref=vo.getB_ref(); 
			int b_is=vo.getB_is(); 	 
			String b_title=vo.getB_title();
			String b_content=vo.getB_content();
			int b_public_private=vo.getB_public_private();
			int b_result=vo.getB_result();
			if(b_num==0) { //새 글일 때
				b_num=getMaxNum()+1;
				b_ref=b_num;
			}else {//답변일 때 
				String sql1="update customer_board set b_result=1 where b_num=?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, b_num);
				int n1=pstmt1.executeUpdate();
				if(n1<=0) {
					con.rollback();
					return n1;
				}
				b_num=getMaxNum()+1;
				b_is=-1;
			}
			String sql2="insert into customer_board "
						+"values(?,?,sysdate,?,?,?,?,?,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, b_num);
			pstmt2.setString(2, b_id);
			pstmt2.setString(3, b_content);
			pstmt2.setInt(4, b_ref); 
			pstmt2.setString(5, b_title);
			pstmt2.setInt(6, b_is);
			pstmt2.setInt(7, b_public_private);
			pstmt2.setInt(8, b_result);
			int n=pstmt2.executeUpdate();
			if(n>0) {
				con.commit();
			}else {
				con.rollback();
			}
				return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			try {
				con.rollback();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt1, null);
			DBConnection.closeConn(null, pstmt2, con);
		}
	}
	
	//고객센터 글 수정하기
	public int update(CustomerBoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			con.setAutoCommit(false);
			String sql="update customer_board "
					+ "set b_title=?, b_content=?, b_public_private=? where b_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getB_title());
			pstmt.setString(2, vo.getB_content());
			pstmt.setInt(3, vo.getB_public_private());
			pstmt.setInt(4, vo.getB_num());
			int n=pstmt.executeUpdate();
			if(n>0) {
				con.commit();
				return n;
			}else {
				con.rollback();
				return 0;
			}			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			try {
				con.rollback();				
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	//검색했을 때 총 게시물수 구하기
	public int findCount(String select, String find) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="";
			if(select.equals("id")) {
				sql="select NVL(count(b_num),0) countnum from customer_board where b_is=0 and b_id=?";
			}else if(select.equals("title")) {
				sql="select NVL(count(b_num),0) countnum from customer_board where b_is=0 and b_title=?";
			}else if(select.equals("content")) {
				sql="select NVL(count(b_num),0) countnum from customer_board where b_is=0 and b_content=?";
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, find);
			rs=pstmt.executeQuery();
			int n=0;
			if(rs.next()) {
				n=rs.getInt("countnum");
			}
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//글찾기(작성자 아이디, 제목, 글내용)
	public ArrayList<CustomerBoardVo> find(String select, String find, int startRow, int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="";
			if(select.equals("id")) {
				sql="select * from("+
					 "select AA.*, rownum rnum from("+
					 "select * from customer_board where b_is=0 and b_id=? order by b_num desc) AA)"+
					 "where rnum >=? and rnum <=?";
			}else if(select.equals("title")){
				sql="select * from("+
					"select AA.*, rownum rnum from("+
					"select * from customer_board where b_is=0 and b_title=? order by b_num desc) AA)"+
					"where rnum >=? and rnum <=?";
			}else if(select.equals("content")) {
				sql="select * from("+
					 "select AA.*, rownum rnum from("+
					 "select * from customer_board where b_is=0 and b_content like '%'||?||'%' order by b_num desc) AA)"+
					 "where rnum >=? and rnum <=?";
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, find);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			ArrayList<CustomerBoardVo> list=new ArrayList<CustomerBoardVo>();
			while(rs.next()) {
				int b_num = rs.getInt("b_num");
				String b_id = rs.getString("b_id");
				Date b_date = rs.getDate("b_date");
				String b_content = rs.getString("b_content");
				int b_ref = rs.getInt("b_ref");
				String b_title = rs.getString("b_title");
				int b_is = rs.getInt("b_is");
				int b_public_private=rs.getInt("b_public_private");
				int b_result=rs.getInt("b_result");
				CustomerBoardVo vo
					= new CustomerBoardVo(b_num, b_id, b_date, b_content, b_ref, b_title, b_is, b_public_private,b_result);
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
	
	//관리자 답변 불러오기
	public String getMsg(int b_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select b_content from customer_board where b_is=-1 and b_ref=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs=pstmt.executeQuery();
			String msg="관리자 확인 중입니다..";
			if(rs.next()) {
				msg=rs.getString("b_content");
			}
			return msg;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
}
