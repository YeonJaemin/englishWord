package dao.word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.word.WordEntity;

public class WordDAO {

	private Connection con;
	public WordDAO(Connection con) {
		this.con = con;
	}
	
	
	public JSONObject select(WordEntity entity){
		JSONObject object = new JSONObject();
		String sql = "select egWord,koWord,wordNum,id,wdate from word where wordNum = ?";
		try{
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, entity.getWordNum());
		ResultSet rs =  pstmt.executeQuery();
		while(rs.next()){
			
			object.put("egWord", rs.getString("egWord"));
			object.put("koWord", rs.getString("koWord"));
			object.put("wordNum", rs.getInt("wordNum"));
			object.put("wdate", rs.getString("wdate"));
			object.put("id", rs.getString("id"));
		}
		con.commit();
		
		rs.close();
		pstmt.close();
		con.close();
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return object;
	}

	public boolean insert(WordEntity entity){
		boolean result =false;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		 sql = "select wordNum from word where id=? and egWord=?";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, entity.getId());
		 pstmt.setString(2, entity.getEgWord());
		 rs = pstmt.executeQuery();
		 if(rs.next()){
			 entity.setWordNum(-1);
			 result =false;
			 rs.close();
			 pstmt.close();
			 con.close();
		 }
		 else {
		 sql = "insert into word(id,egWord,koWord,wdate) values (?,?,?,?)";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, entity.getId());
		 pstmt.setString(2, entity.getEgWord());
		 pstmt.setString(3, entity.getKoWord());
		 pstmt.setString(4, entity.getWdate());

		 int count = pstmt.executeUpdate();
		System.out.println(count);
		 if(count ==1){
			 sql = "select wordNum from word where id=? and egWord=?";
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, entity.getId());
			 pstmt.setString(2, entity.getEgWord());
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			 entity.setWordNum(rs.getInt("wordNum"));
			 }
			 result = true;
			 con.commit();
			 rs.close();
			 pstmt.close();
			 con.close();
		 }
		 else{
			 result =false;
			 con.rollback();
			 rs.close();
			 pstmt.close();
			 con.close();
		 }
		 }
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
			return false;
		}finally {
			try {
				pstmt.close();
				 con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	public JSONArray list(){
		JSONArray list = new  JSONArray();
		String sql = "select egWord,koWord,wordNum from word";
		try{
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs =  pstmt.executeQuery();
		while(rs.next()){
			JSONObject tmp = new JSONObject();
			tmp.put("egWord", rs.getString("egWord"));
			tmp.put("koWord", rs.getString("koWord"));
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
}
