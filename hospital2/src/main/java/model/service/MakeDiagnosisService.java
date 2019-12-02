package model.service;


import java.sql.SQLException;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DiagnosisDao;
import model.dao.PatientDao;
import model.entity.Diagnosis;
import model.entity.Patient;

public class MakeDiagnosisService {
	
	public void createPatient(int room, String name, String surname, int doctor_id) throws SQLException {
		
		DaoFactory factory = DaoFactory.getInstance();	
	    PatientDao dao = factory.createPatientDao();
	    Patient p = new Patient(room, name, surname, doctor_id);
	    dao.create(p);
	}
	
	public void createDiagnosis(String description, String conclusion, String procedures,
			String medicines, String operation, int doctor_id, int patient_id) throws SQLException{
		
		DaoFactory factory = DaoFactory.getInstance();
		DiagnosisDao dao = factory.createDiagnosisDao();
	    Diagnosis d = new Diagnosis(description, conclusion, procedures, medicines, operation, patient_id, doctor_id);
	    dao.create(d);
	}
	
	public Patient getPatientByRoomNameSurname(int room, String name, String surname) {
		
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    Patient p = dao.findPatientByRoomNameSurname(room, name, surname);
	    return p;
	}
	
	public void wirePatientAndDiagnosis(int patient_id, int diagnosis_id) {
		
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    dao.wirePatientAndDiagnosis(patient_id, diagnosis_id);
	}
	public Diagnosis getDiagnosisByPatientId(int patient_id) {
		
		DaoFactory factory = DaoFactory.getInstance();
		DiagnosisDao dao = factory.createDiagnosisDao();
		Diagnosis diagnosis = dao.findDiagnosisByPatientId(patient_id);
		return diagnosis;
	}
	
	public int findPatientIdByRoomNameSurname(int room, String name, String surname) {
		
		int id;
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    Patient patient = dao.findPatientByRoomNameSurname(room, name, surname);
	    id = patient.getId();
	    
		return id;
	}
	public boolean isRoomBusy(int room) {
		
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    List<Patient> listOfPatients = dao.getAllPatientsInHospital();
	    boolean isRoomBusy = listOfPatients.stream().anyMatch(var -> var.getRoom() == room);
	    return isRoomBusy;
	}
}
