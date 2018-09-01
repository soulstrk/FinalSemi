package Suhong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Suhong.Vojap;
import ljy.db.DBConnection;

public class Daovo {

	public int insert(Vojap vo) {
		Connection con=null;
		PreparedStatement pst=null;
		PreparedStatement pst1=null;
		try {
			con=DBConnection.getConn();
			int bonNum=getMaxNum()+1;
			int num=vo.getNum();
			int ref=vo.getRef();
			int lev=vo.getLev();
			int step=vo.getStep();
			if(num==0) {
				ref=bonNum;
			}else {
				String sql="update job set step=step+1 where ref=? and step>?";
				pst1=con.prepareStatement(sql);
				pst1.setInt(1, ref);
				pst1.setInt(2, step);
				pst1.executeUpdate();
				lev+=1;
				step+=1;
			}
			String sql="insert into job values(?,?,?,?,?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setInt(1, bonNum);
			pst.setString(2,vo.getName());
			pst.setString(3,vo.getTitle());
			pst.setString(4,vo.getContent());
			pst.setInt(5,ref);
			pst.setInt(6,lev);
			pst.setInt(7,step);
			int n=pst.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(pst);
			DBConnection.closeConn(pst1);
			DBConnection.closeConn(con);
		}
	}

	private int getMaxNum() {//가장 큰글번호 얻는 메소드
		Connection con=null;
		PreparedStatement pst=null;
		try {
		ResultSet rs=null;
			con=DBConnection.getConn();
			String sql="select NVL(max(num),0) maxnum from job";
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxnum");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pst, con);
		}
	}

	public ArrayList<Vojap> list(int sR, int eR) {
		String sql="SELECT * FROM " +
			    "(  " +
				"	   SELECT AA.*,ROWNUM RNUM FROM "+
				"	   ( "  +
				"	     SELECT * FROM job " +
				"	     ORDER BY REF DESC,STEP ASC " +
				"	   )AA " +
				")" +
				" WHERE RNUM>=? AND RNUM<=?";
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			pst=con.prepareStatement(sql);
			pst.setInt(1, sR);
			pst.setInt(2, eR);
			rs=pst.executeQuery();
			ArrayList<Vojap> list=new ArrayList<>();
			while(rs.next()) {
				int num=rs.getInt("num");
				String name=rs.getString("name");
				String title=rs.getString("title");
				String content=rs.getString("content");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				Vojap vo=new Vojap(num, name, title, content, ref, lev, step);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs,pst,con);
		}
	}

	public double getCount() {
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select NVL(count(num),0) cnt from job";
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs,pst,con);
		}
	}

	public Vojap dtl(int num) {
		String sql="select * from job where num=?";
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			pst=con.prepareStatement(sql);
			pst.setInt(1, num);
			rs=pst.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				String title=rs.getString("title");
				String content=rs.getString("content");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				Vojap vo=new Vojap(num, name, title, content, ref, lev, step);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs,pst,con);
		}
	}
}
