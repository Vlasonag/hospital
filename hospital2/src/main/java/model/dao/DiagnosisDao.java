package model.dao;

import java.sql.SQLException; 

import model.entity.Diagnosis;

public interface DiagnosisDao extends GenericDao<Diagnosis>{
	
	Diagnosis findDiagnosisByPatientId(int id);
	
	void editDiagnosisByPatientId(int patient_id, Diagnosis diagnosis);
	
	Diagnosis findDiagnosisByPatientIdAndDoctorId(int patiend_id, int doctor_id) throws SQLException;
	
}
