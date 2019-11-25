package model.service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PatientDao;
import model.entity.Patient;

public class DischargePatientService {
	
	public List<Patient> getAllPatientsInHospital() {
		
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    return dao.getAllPatientsInHospital();
	}
	
	public void dischargePatientById(int id) {
		
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    dao.dischargePatientById(id);
	}
}
