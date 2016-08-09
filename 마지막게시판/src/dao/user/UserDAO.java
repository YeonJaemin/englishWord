package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.user.UserEntity;

public class UserDAO {

	private Connection con;
	
	public UserDAO(Connection con) {
		this.con = con;
	}
	
	public boolean select(UserEntity entity){
		boolean result = false;
		try{
		String sql = "select id from user where id=? and pw=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, entity.getId());
		pstmt.setString(2, entity.getPw());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			result = true;
		}
		else{
			result =false;
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
}
