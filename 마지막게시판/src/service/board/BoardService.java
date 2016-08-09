package service.board;

import java.sql.Connection;
import java.util.ArrayList;

import dao.board.BoardDAO;
import entity.board.BoardEntity;

public class BoardService {

	
	
	public int getPageNum(){
		Connection con = common.DBTemplate.getConnection();
		BoardDAO dao = new BoardDAO(con);
		int pageNum = dao.count();
		
		try{
		con.commit();
		con.close();
		} catch(Exception e){
			
		}
		
		return pageNum;
	}
	
	public ArrayList<BoardEntity> searchPage(BoardEntity start){
		Connection con = common.DBTemplate.getConnection();
		BoardDAO dao = new BoardDAO(con);
		ArrayList<BoardEntity> list = dao.select(start);
		
		try{
		con.commit();
		con.close();
		} catch(Exception e){
			
		}
		
		return list;
	}
	
	
	public boolean write(BoardEntity entity){
		boolean result = false;
		Connection con = common.DBTemplate.getConnection();
		BoardDAO dao = new BoardDAO(con);
		result = dao.insert(entity);
		try{
		if(result){
			con.commit();
		}
		else{
			con.rollback();
		}
		con.close();
		}catch (Exception e) {
			
		}
		return result;
	}
	
	
	
}
