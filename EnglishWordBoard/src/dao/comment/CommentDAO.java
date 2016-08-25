package dao.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.comment.CommentEntity;

public class CommentDAO {

	private Connection con;

	public CommentDAO(Connection con) {
		super();
		this.con = con;
	}
	
	
	
	public JSONArray list(CommentEntity entity){
		JSONArray list = new  JSONArray();
		String sql = "select commentNum,id,wordNum,comment,cdate from comment where wordNum = ? ";
		try{
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,entity.getWordNum());
		ResultSet rs =  pstmt.executeQuery();
		while(rs.next()){
			JSONObject tmp = new JSONObject();
			tmp.put("id", rs.getString("id"));
			tmp.put("commentNum", rs.getInt("commentNum"));
			tmp.put("comment", rs.getString("comment"));
			tmp.put("cdate", rs.getString("cdate"));
			tmp.put("wordNum", rs.getInt("wordNum"));
			list.add(tmp);
		}
		con.commit();
		
		rs.close();
		pstmt.close();
		con.close();
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return list;
	}

	
	
	public boolean insert(CommentEntity entity){
		boolean result = false;
		String sql = null;
		PreparedStatement pstmt = null;
		
		try{
		 sql = "insert into comment(id,wordNum,comment,cdate) values (?,?,?,?)";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, entity.getId());
		 pstmt.setInt(2, entity.getWordNum());
		 pstmt.setString(3, entity.getComment());
		 pstmt.setString(4, entity.getCdate());

		 int count = pstmt.executeUpdate();
		 if(count ==1){
			 System.out.println("성공");
			 result = true;
			 con.commit();
			 pstmt.close();
			 con.close();
		 }
		 else{
			 System.out.println("실패");
			 result =false;
			 con.rollback();
			 pstmt.close();
			 con.close();
		 }
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
			return false;
		}finally {
			try {
				pstmt.close();
				 con.close();
				 return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
		
	}
}
