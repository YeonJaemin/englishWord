package service.comment;

import java.sql.Connection;

import org.json.simple.JSONArray;

import dao.comment.CommentDAO;
import dao.word.WordDAO;
import entity.comment.CommentEntity;

public class CommentService {

	public JSONArray detail(CommentEntity entity){
		Connection con = common.DBTemplate.getConnection();// ��? ������ �޼����ϳ��� Ʈ������ϳ��ϱ�!!
		CommentDAO dao = new CommentDAO(con);
		JSONArray list = dao.list(entity);
		return  list;
	}
	
	public boolean add(CommentEntity entity){
		
		Connection con = common.DBTemplate.getConnection();// ��? ������ �޼����ϳ��� Ʈ������ϳ��ϱ�!!
		CommentDAO dao = new CommentDAO(con);
		boolean result = dao.insert(entity);		
		return result;
	}
}
