package service.word;

import java.sql.Connection;

import org.json.simple.JSONArray;

import dao.word.WordDAO;
import entity.user.UserEntity;
import entity.word.WordEntity;

public class WordService {

	public JSONArray list(){
		
		Connection con = common.DBTemplate.getConnection();// ��? ������ �޼����ϳ��� Ʈ������ϳ��ϱ�!!
		WordDAO dao = new WordDAO(con);
		JSONArray list = dao.list();
		return list;
	}
	
	public boolean add(WordEntity entity){
		
		Connection con = common.DBTemplate.getConnection();// ��? ������ �޼����ϳ��� Ʈ������ϳ��ϱ�!!
		WordDAO dao = new WordDAO(con);
		boolean result = dao.insert(entity);
		return result;
	}
	
}
