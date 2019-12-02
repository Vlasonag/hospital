package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dao.UserDao;
import model.entity.User;
import model.enumerations.ROLE;

public class JDBCUserDao implements UserDao{
	
	private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

	@Override
	public void create(User entity){
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> UserList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user")){
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ){
				UserList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), ROLE.valueOf(rs.getString(6))));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return UserList;
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByLogin(String login) {
		User user = null;
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE login= ?;")){
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ){
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), ROLE.valueOf(rs.getString(6)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return user;
	}

	@Override
	public void createWorker(String login, String password, String name,
			String surname, ROLE role) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO user (login, password, name, surname, role) VALUES (?, ?, ?, ?, ?);")){
			ps.setString(1, login);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, surname);
			ps.setString(5, role.toString());
			ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		
	}

	@Override
	public List<User> getListOfDoctors() {
		List<User> listOfDoctors = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE role = 'ROLE_DOCTOR';")){
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ){
				listOfDoctors.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), ROLE.valueOf(rs.getString(6))));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return listOfDoctors;
	}
	
	
	
}
