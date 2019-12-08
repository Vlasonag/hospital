package model.dao;

import java.util.List;

import model.entity.User;
import model.enumerations.ROLE;

public interface UserDao extends GenericDao<User>{
	
	public User getUserByLogin(String login);
	
	public void createWorker(String login, String password, String name, String surname, ROLE role);
	
	public List<User> getListOfDoctors();
	
}
