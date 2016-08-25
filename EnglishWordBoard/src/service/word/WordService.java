package service.word;

import java.sql.Connection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.word.WordDAO;
import entity.user.UserEntity;
import entity.word.WordEntity;

public class WordService {

	public JSONObject detail(WordEntity entity){
		
		Connection con = common.DBTemplate.getConnection();// 왜? 서비스의 메서드하나가 트랜잭션하나니깐!!
		WordDAO dao = new WordDAO(con);
		JSONObject result = dao.select(entity);
		return result;
	}
	
	
	public JSONArray list(){
		
		Connection con = common.DBTemplate.getConnection();// 왜? 서비스의 메서드하나가 트랜잭션하나니깐!!
		WordDAO dao = new WordDAO(con);
		JSONArray list = dao.list();
		return list;
	}
	
	public boolean add(WordEntity entity){
		
		Connection con = common.DBTemplate.getConnection();// 왜? 서비스의 메서드하나가 트랜잭션하나니깐!!
		WordDAO dao = new WordDAO(con);
		boolean result = dao.insert(entity);
		return result;
	}
	
}
