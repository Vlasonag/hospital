package model.service;

import java.util.List; 

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class SignInService {
	
	public boolean isUserExist(String login, String password) {
			
		DaoFactory factory = DaoFactory.getInstance();		
	    UserDao dao = factory.createUserDao();
		List<User> userList = dao.findAll();
		return userList.stream().anyMatch(var -> var.getLogin().equals(login) 
												&& var.getPassword().equals(password));
	}
	
	public User getUserByLogin(String login) {
		
		DaoFactory factory = DaoFactory.getInstance();	
	    UserDao dao = factory.createUserDao();
	    User user = dao.getUserByLogin(login);
	    return user;
		
	}
	
}
