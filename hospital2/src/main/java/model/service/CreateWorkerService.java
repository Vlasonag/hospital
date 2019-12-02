package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.PatientDao;
import model.dao.UserDao;
import model.entity.Patient;
import model.entity.User;
import model.enumerations.ROLE;

public class CreateWorkerService {
	
	public void createWorker(String login, String password, String name, String surname, ROLE role) {
		
		DaoFactory factory = DaoFactory.getInstance();		
	    UserDao dao = factory.createUserDao();
	    dao.createWorker(login, password, name, surname, role);
	}
	
	public boolean isLoginExist(String login) {
		
		DaoFactory factory = DaoFactory.getInstance();		
	    UserDao dao = factory.createUserDao();
	    List<User> userList = dao.findAll();
	    for(int i = 0; i < userList.size(); i++) {
			if ((login.equals(userList.get(i).getLogin()))) {
				return true;
			}		
		}
	    return false;
	}
	
	public boolean isPasswordExist(String password) {
		
		DaoFactory factory = DaoFactory.getInstance();		
	    UserDao dao = factory.createUserDao();
	    List<User> userList = dao.findAll();
	    for(int i = 0; i < userList.size(); i++) {
			if ((password.equals(userList.get(i).getPassword()))) {
				return true;
			}		
		}
	    return false;
	}
	
	public List<User> getListOfDoctors() {
		
		DaoFactory factory = DaoFactory.getInstance();		
	    UserDao dao = factory.createUserDao();
	    List<User> listOfDoctors = dao.getListOfDoctors();
		return listOfDoctors;
	}

	public void changeDoctor(Patient patient, int doctor_id) throws SQLException {
		
		DaoFactory factory = DaoFactory.getInstance();
		PatientDao dao1 = factory.createPatientDao();
		dao1.changeDoctorInPatientAndDiagnosis(patient, doctor_id);
		
	}
	
}
