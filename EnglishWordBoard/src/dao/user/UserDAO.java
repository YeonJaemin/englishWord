package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.user.UserEntity;

public class UserDAO {

	private Connection con;

	public UserDAO(Connection con) {
		super();
		this.con = con;
	}
	
	
	public boolean select(UserEntity entity){
		boolean result = false;
		String sql = "select id,pw,name from user where id=? and pw=?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,entity.getId());
			pstmt.setString(2,entity.getPw());
			rs =  pstmt.executeQuery();
			
			if(rs.next()){
				result =true;
			}else{
				result =false;
			}
			con.commit();
		}catch(Exception e){
			System.out.println(e);
		}finally {
			try{
			rs.close();
			pstmt.close();
			con.close();
			}catch(Exception e){
				System.out.println(e);
		}
 
	}
		 return result;	
	}
	
	
	
	public boolean insert(UserEntity entity){
		boolean result = false;
		String sql = "insert into user values(?,?,?)"; 
		PreparedStatement pstmt = null;
		int count =0;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,entity.getId());
			pstmt.setString(2,entity.getName());
			pstmt.setString(3,entity.getPw());
			count = pstmt.executeUpdate();
			if(count==1){
				result =true;
				con.commit();
			}else{
				result =false;
				con.rollback();
			}
			
		}catch(Exception e){
			System.out.println(e);
		}finally {
			try{
			pstmt.close();
			con.close();
			}catch(Exception e){
				System.out.println(e);
		}
		
	   
	}
		 return result;	
	}
}
