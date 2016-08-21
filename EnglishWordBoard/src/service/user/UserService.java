package service.user;

import dao.user.UserDAO;
import entity.user.UserEntity;

public class UserService {

	public boolean signUp(UserEntity entity){
		boolean result = false;
		UserDAO dao = new UserDAO(common.DBTemplate.getConnection());
		result = dao.insert(entity);
		return result;
	}
	
	public boolean signIn(UserEntity entity){
		boolean result = false;
		UserDAO dao = new UserDAO(common.DBTemplate.getConnection());
		result = dao.select(entity);
		return result;
	}
	
	
}
