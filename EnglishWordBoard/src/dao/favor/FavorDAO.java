package dao.favor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.comment.CommentEntity;
import entity.favor.FavorEntity;

public class FavorDAO {
 
	private Connection con;

	public FavorDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public boolean delete(FavorEntity entity){
		boolean result = false;
		String sql = "delete from favorite where id=? and wordNum=?";
		PreparedStatement pstmt = null;
		
		try{
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, entity.getId());
		pstmt.setInt(2, entity.getWordNum());
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

	
	public JSONArray list(FavorEntity entity){
		JSONArray list = new  JSONArray();
		String sql = "select word.wordNum,egWord,koWord from favorite,word where favorite.wordNum  = word.wordNum and favorite.id = ? ";
		try{
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,entity.getId());
		ResultSet rs =  pstmt.executeQuery();
		while(rs.next()){
			JSONObject tmp = new JSONObject();
			tmp.put("wordNum", rs.getInt("wordNum"));
			tmp.put("egWord", rs.getString("egWord"));
			tmp.put("koWord", rs.getString("koWord"));
			list.add(tmp);
		}
		con.commit();
		
		rs.close();
		pstmt.close();
		con.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return list;
		}
		
		return list;
	}

	
	
	public boolean insert(FavorEntity entity){
		boolean result = false;
		String sql = "insert into favorite values (?,?)";
		PreparedStatement pstmt = null;
		
		try{
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, entity.getId());
		pstmt.setInt(2, entity.getWordNum());
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

