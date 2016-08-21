package dao.word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.word.WordEntity;

public class WordDAO {

	private Connection con;
	public WordDAO(Connection con) {
		this.con = con;
	}
	
	
	public boolean insert(WordEntity entity){
		boolean result =false;
		String sql = "alter table word auto_increment=1";
		PreparedStatement pstmt = null;
		
		try{
		 pstmt = con.prepareStatement(sql);
		 pstmt.executeUpdate();
		
		 sql = "insert into word(id,egWord,koWord,wdate) values (?,?,?,?);";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, entity.getId());
		 pstmt.setString(2, entity.getEgWord());
		 pstmt.setString(3, entity.getKoWord());
		 pstmt.setString(4, entity.getWdate());

		 int count = pstmt.executeUpdate();
		
		 if(count ==1){
			 result = true;
			 con.commit();
		 }
		 else{
			 result =false;
			 con.rollback();
		 }
		 pstmt.close();
		 con.close();		
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return result;
	}
	
	public JSONArray list(){
		JSONArray list = new  JSONArray();
		String sql = "select egWord,koWord from word";
		try{
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs =  pstmt.executeQuery();
		while(rs.next()){
			JSONObject tmp = new JSONObject();
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
			return null;
		}
		
		return list;
	}
}
