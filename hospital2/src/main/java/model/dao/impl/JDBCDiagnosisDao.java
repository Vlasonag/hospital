package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.dao.DiagnosisDao;
import model.entity.Diagnosis;
import model.entity.Patient;

public class JDBCDiagnosisDao implements DiagnosisDao{
	
	private Connection connection;

    public JDBCDiagnosisDao(Connection connection) {
        this.connection = connection;
    }
	
    @Override
	public void create(Diagnosis entity) {
		String query = "INSERT INTO diagnosis (description, conclusion, procedures, medicines, operations, patient_id, doctor_id) "
				+ "VALUES ('" + entity.getDescription() + "', '" + entity.getConclusion() + 
				"', '" + entity.getProcedures() + "', '" + entity.getMedicines() + "', '" + 
				entity.getOperations() + "', '" + entity.getPatient_id() + "', '" + entity.getDoctor_id() + "')";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
        }		
	}

	@Override
	public Diagnosis findById(int id) {
		return null;
	}

	@Override
	public List<Diagnosis> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Diagnosis entity) {
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
	public Diagnosis findDiagnosisByPatientId(int id) {
		Diagnosis diagnosis = null;
		String idStr = "'" + id + "'";
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM diagnosis WHERE patient_id=" + idStr )){
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ){
				diagnosis = new Diagnosis(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
												rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return diagnosis;
	}

	@Override
	public void editDiagnosisByPatientId(int patient_id, Diagnosis diagnosis) {
		System.out.println(patient_id);
		String query = "UPDATE diagnosis SET description = ?, conclusion = ?, procedures = ?, medicines = ?, operations = ? WHERE (patient_id = ?);";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, diagnosis.getDescription());
			ps.setString(2, diagnosis.getConclusion());
			ps.setString(3, diagnosis.getProcedures());
			ps.setString(4, diagnosis.getMedicines());
			ps.setString(5, diagnosis.getOperations());
			ps.setInt(6, patient_id);
			ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
        }	
		
	}

	@Override
	public Diagnosis findDiagnosisByPatientIdAndDoctorId(int patiend_id, int doctor_id) throws SQLException{
		Diagnosis diagnosis = null;
		String query = "SELECT * FROM diagnosis WHERE patient_id = ? AND doctor_id = ?;";
		PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(patiend_id +  "    "+ doctor_id);
			ps.setInt(1, patiend_id);
			ps.setInt(2, doctor_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			diagnosis = new Diagnosis(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
					rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
		return diagnosis;
	}
	

}
