package model.service;

import java.sql.SQLException;
import java.sql.Timestamp;

import model.dao.DaoFactory;
import model.dao.DiagnosisDao;
import model.dao.NoteDao;
import model.dao.PatientDao;
import model.entity.Diagnosis;
import model.entity.Note;
import model.entity.Patient;

public class FindDiagnosisService {
	
	public Diagnosis findDiagnosisByPatientId(int room, String name, String surname) throws SQLException{
		
		Diagnosis diagnosis;
		DaoFactory factory = DaoFactory.getInstance();
	    DiagnosisDao dao = factory.createDiagnosisDao();
	    diagnosis = dao.findDiagnosisByPatientId(findPatientIdByRoomNameSurname(room, name, surname));
		return diagnosis;
	}
	
	public int findPatientIdByRoomNameSurname(int room, String name, String surname) throws SQLException{
		
		int id;
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    Patient patient = dao.findPatientByRoomNameSurname(room, name, surname);
	    id = patient.getId();
	    
		return id;
		
	}
	
	public void createNote(int worker_id, int diagnosis_id, String commentary, Timestamp date, 
																	int patient_id) throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
	    NoteDao dao = factory.createNoteDao();
	    Note note = new Note(worker_id, diagnosis_id, commentary, date);
	    dao.create(note);
	    dao.createRelationUser_Patient(worker_id, patient_id);
	    note = dao.findNoteByDate(date);
	    /*dao.createRelationDiagnosis_Note(diagnosis_id, note);*/
	}
	
	public void editDiagnosisByPatientId(int patient_id, Diagnosis diagnosis) {
		
		DaoFactory factory = DaoFactory.getInstance();
	    DiagnosisDao dao = factory.createDiagnosisDao();
	    dao.editDiagnosisByPatientId(patient_id, diagnosis);
	}
	
	public Diagnosis findDiagnosisByPatientIdAndDoctorId(int patient_id, int doctor_id) throws SQLException {
		
		Diagnosis diagnosis;
		DaoFactory factory = DaoFactory.getInstance();
	    DiagnosisDao dao = factory.createDiagnosisDao();
	    diagnosis = dao.findDiagnosisByPatientIdAndDoctorId(patient_id, doctor_id);
		return diagnosis;
	}
	
	public Patient findPatientByRoomNameSurname(int room, String name, String surname) {
		
		DaoFactory factory = DaoFactory.getInstance();
	    PatientDao dao = factory.createPatientDao();
	    Patient patient = dao.findPatientByRoomNameSurname(room, name, surname);
	    
		return patient;
		
	}
}
