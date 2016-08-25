package service.comment;

import java.sql.Connection;

import org.json.simple.JSONArray;

import dao.comment.CommentDAO;
import dao.word.WordDAO;
import entity.comment.CommentEntity;

public class CommentService {

	public JSONArray detail(CommentEntity entity){
		Connection con = common.DBTemplate.getConnection();// 왜? 서비스의 메서드하나가 트랜잭션하나니깐!!
		CommentDAO dao = new CommentDAO(con);
		JSONArray list = dao.list(entity);
		return  list;
	}
	
	public boolean add(CommentEntity entity){
		
		Connection con = common.DBTemplate.getConnection();// 왜? 서비스의 메서드하나가 트랜잭션하나니깐!!
		CommentDAO dao = new CommentDAO(con);
		boolean result = dao.insert(entity);		
		return result;
	}
}
