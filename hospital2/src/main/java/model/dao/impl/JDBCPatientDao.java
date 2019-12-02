package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.PatientDao;
import model.entity.Patient;

public class JDBCPatientDao implements PatientDao{

	private Connection connection;

    public JDBCPatientDao(Connection connection) {
        this.connection = connection;
    }	
	
	@Override
	public void create(Patient patient) throws SQLException {
		String query = "INSERT INTO patient (room, name, surname, doctor_id) VALUES (?, ?, ?, ?);";
		PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, patient.getRoom());
			ps.setString(2, patient.getName());
			ps.setString(3, patient.getSurname());
			ps.setInt(4, patient.getDoctor_id());
			ps.executeUpdate();		
	}

	@Override
	public Patient findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Patient entity) {
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
	public Patient findPatientByRoomNameSurname(int room, String name, String surname) {
		Patient patient = null;
		String roomStr = "'" + room + "'";
		name = "'" + name + "'";
		surname = "'" + surname + "'";
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM patient WHERE "
										+ "room=" + roomStr+" AND "
										+ "name=" + name+" AND "
										+ "surname=" + surname+" AND inHospital=1"
				)){
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ){
				patient = new Patient(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return patient;
	}

	@Override
	public void wirePatientAndDiagnosis(int id, int diagnosis_id) {
		String query = "Update patient SET diagnosis_id = ? WHERE (id = ?);";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, diagnosis_id);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
        }
	}

	@Override
	public List<Patient> getAllPatientsInHospital() {
		String query = "SELECT * FROM patient WHERE inHospital = 1";
		List<Patient> listOfPatients = new ArrayList<Patient>();
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listOfPatients.add(new Patient(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return listOfPatients;
	}

	@Override
	public void dischargePatientById(int id) {
		String query = "Update patient SET inHospital = 0 WHERE (id = ?);";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
        }
	}

	@Override
	public void changeDoctorInPatientAndDiagnosis(Patient patient, int doctor_id) throws SQLException {
		String query = "Update patient SET doctor_id = ? WHERE (id = ?);";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, doctor_id);
		ps.setInt(2, patient.getId());
		ps.executeUpdate();
		String query1 = "Update diagnosis SET doctor_id = ? WHERE (patient_id = ?);";
		PreparedStatement ps1 = connection.prepareStatement(query1);
		ps1.setInt(1, doctor_id);
		ps1.setInt(2, patient.getId());
		ps1.executeUpdate();
	}
}
