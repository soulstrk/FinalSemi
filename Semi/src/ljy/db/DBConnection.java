package ljy.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConn() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@61.102.155.36:1521:xe"; //orcl-> xe
			conn=DriverManager.getConnection(url,"scott","tiger"); //scott, tiger //yong, admin
		}catch(ClassNotFoundException ce) {
			System.out.println(ce.getMessage());
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
		return conn;
	}
	public static void closeConn(ResultSet rs,Statement stmt,Connection conn) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	public static void closeConn(ResultSet rs,PreparedStatement ps,Connection conn) {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	public static void closeConn(ResultSet rs) {
		try {
			if(rs!=null) rs.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	public static void closeConn(PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	public static void closeConn(Connection conn) {
		try {
			if(conn!=null) conn.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	
	
	
}






