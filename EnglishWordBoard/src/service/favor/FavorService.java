package service.favor;

import java.sql.Connection;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.favor.FavorDAO;
import entity.favor.FavorEntity;

public class FavorService {

	
	public boolean delete(FavorEntity entity){
		boolean result = false;
		Connection con = common.DBTemplate.getConnection();
		FavorDAO dao = new FavorDAO(con);
		result = dao.delete(entity);
		return result;
	}
	
	public JSONObject game(FavorEntity entity){

		JSONObject result = new JSONObject();
		Connection con = common.DBTemplate.getConnection();
		FavorDAO dao = new FavorDAO(con);
		Random r = new Random();
		JSONArray list  = dao.list(entity);
		JSONArray gameList  = new JSONArray();
		if(entity.getWordNum()>list.size()){
			result.put("rs",false);
		}
		else{
			result.put("rs",true);
		for(int i = 0; i<entity.getWordNum() ; i++ ){
			int tmp = r.nextInt(list.size());
			JSONObject object = (JSONObject)list.get(tmp);
			gameList.add(object);
			list.remove(tmp);
		}
		result.put("arr", gameList);
		}
		return result;
	}
	
	
	public JSONArray list(FavorEntity entity){
		
		Connection con = common.DBTemplate.getConnection();
		FavorDAO dao = new FavorDAO(con);
		JSONArray list  = dao.list(entity);
		return list;
	}
	
	public boolean add(FavorEntity entity){
		boolean result = false;
		Connection con = common.DBTemplate.getConnection();
		FavorDAO dao = new FavorDAO(con);
		result = dao.insert(entity);
		return result;
	}
}
