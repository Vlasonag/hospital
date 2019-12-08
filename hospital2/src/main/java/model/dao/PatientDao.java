package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.entity.Patient;

public interface PatientDao extends GenericDao<Patient>{
	
		Patient findPatientByRoomNameSurname(int room, String name, String surname);
		
		void wirePatientAndDiagnosis(int patient_id, int diagnosis_id);
		
		List<Patient> getAllPatientsInHospital();
		
		void dischargePatientById(int id);
		
		void changeDoctorInPatientAndDiagnosis(Patient patient, int doctor_id) throws SQLException;
	}
