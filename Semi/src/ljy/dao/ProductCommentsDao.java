package ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ljy.db.DBConnection;
import ljy.vo.ReviewVo;

public class ProductCommentsDao {
	public ArrayList<ReviewVo> getComments(String pNum){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from review where p_num = ? order by comments_num asc";
		ArrayList<ReviewVo> list = new ArrayList<>();
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				do {
					int commentsNum = rs.getInt("comments_num");
					String id = rs.getString("id");
					String content = rs.getString("content");
					ReviewVo vo = new ReviewVo(commentsNum, Integer.parseInt(pNum), id, content);
					list.add(vo);
				} while(rs.next());
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, ps, conn);
		}
	}
	
	public int insertComments(ReviewVo vo){ // 상품후기 입력 메소드
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into review values(review_seq.nextval,?,?,?)";
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getpNum());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getContent());
			int w = ps.executeUpdate();
			return w;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(null, ps, conn);
		}
	}
	
	
}
