package dao.board;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.board.BoardEntity;

public class BoardDAO {

	
	private Connection con;
	
	public BoardDAO(Connection con) {
		this.con = con;
	}
	
	public int count(){
		int count = 0;
		try{
			String sql = "select count(boardNum) from board_content";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("count(boardNum)");
			rs.close();
			pstmt.close();
			}catch (Exception e) {
				// TODO: handle exception
			}

		return count;
	}
	
	public ArrayList<BoardEntity> select(BoardEntity start){
		ArrayList<BoardEntity> list = new ArrayList<BoardEntity>();
		try{
		String sql ="select boardNum,uid,title,date,enterNum from board_content order by boardNum desc limit ?,10";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, start.getBoardNum());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			BoardEntity entity = new BoardEntity();
			entity.setBoardNum(rs.getInt("boardNum"));
			entity.setUid(rs.getString("uid"));
			entity.setTitle(rs.getString("title"));
			entity.setDate(rs.getString("date"));
			entity.setEnterNum(rs.getInt("enterNum"));
			list.add(entity);	
		}
		rs.close();
		pstmt.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public boolean insert(BoardEntity entity){
		boolean result = false;
		try{
		String sql ="select MAX(boardNum) from board_content";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int num = rs.getInt(1) + 1;
		sql = "insert into board_content values (?,?,?,?,?,0)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setString(2, entity.getUid());
		pstmt.setString(3, entity.getTitle());
		pstmt.setString(4, entity.getContent());
		pstmt.setString(5, entity.getDate());
		int count = pstmt.executeUpdate();
		if(count == 1){
			result = true;
		}else{
			result = false;
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
}


