package model.dao.impl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.dao.NoteDao;
import model.entity.Diagnosis;
import model.entity.Note;
import model.entity.Patient;
import model.entity.User;
import model.enumerations.ROLE;

public class JDBCNoteDao implements NoteDao{
	
	private Connection connection;

    public JDBCNoteDao(Connection connection) {
        this.connection = connection;
    }	
	@Override
	public void create(Note entity, int user_id, int patient_id) {
		
		String query = "INSERT INTO note (worker_id, diagnosis_id, commentary, date) VALUES (?, ?, ?, ?);";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			
			connection.setAutoCommit(false);
			ps.setInt(1, entity.getWorker_id());
			ps.setInt(2, entity.getDiagnosis_id());
			ps.setString(3, entity.getCommentary());
			ps.setTimestamp(4, entity.getDate());
			ps.executeUpdate();
			
			query = "INSERT INTO user_patient (user_id, patient_id) VALUES (?, ?);";
			PreparedStatement ps1 = connection.prepareStatement(query);
			ps1.setInt(1, user_id);
			ps1.setInt(2, patient_id);
			ps1.executeUpdate();
			connection.commit();
		}
			catch (Exception e) {
				e.printStackTrace();
	        }
		}	

	@Override
	public Note findById(int id) {
		String query = "SELECT * note WHERE id = ?";
		Note note = null;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, id);
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			note = new Note(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5));
			
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return note;
	}

	@Override
	public List<Note> findAll() {
		String query = "SELECT * FROM note "
				+ "LEFT JOIN diagnosis ON note.diagnosis_id = diagnosis.id "
				+ "LEFT JOIN patient ON diagnosis.id = patient.diagnosis_id "
				+ "LEFT JOIN user ON user.id = note.worker_id;";
		List<Note> listOfNotes = new ArrayList<Note>();;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Note note = new Note();
				note.setId(rs.getInt(1));
				note.setWorker_id(rs.getInt(2));
				note.setDiagnosis_id(rs.getInt(3));
				note.setCommentary(rs.getString(4));
				note.setDate(rs.getTimestamp(5));
				
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(6));
				diagnosis.setDescription(rs.getString(7));
				diagnosis.setConclusion(rs.getString(8));
				diagnosis.setProcedures(rs.getString(9));
				diagnosis.setMedicines(rs.getString(10));
				diagnosis.setOperations(rs.getString(11));
				diagnosis.setPatient_id(rs.getInt(12));
				diagnosis.setDoctor_id(rs.getInt(13));
				
				Patient patient = new Patient();
				
				patient.setId(rs.getInt(14));
				patient.setRoom(rs.getInt(15));
				patient.setName(rs.getString(16));
				patient.setSurname(rs.getString(17));
				patient.setDoctor_id(rs.getInt(18));
				patient.setDiagnosis_id(rs.getInt(19));
				patient.setInHospital(rs.getInt(20));
				
				User user = new User();
				
				user.setId(rs.getInt(21));
				user.setLogin(rs.getString(22));
				user.setPassword(rs.getString(23));
				user.setName(rs.getString(24));
				user.setSurname(rs.getString(25));
				user.setRole(ROLE.valueOf(rs.getString(26)));
				
				diagnosis.setPatient(patient);
				note.setDiagnosis(diagnosis);
				note.setUser(user);
				
				listOfNotes.add(note);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return listOfNotes;
	}
	
	@Override
	public Diagnosis findDiagnosisForNote(Note note) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(Note entity) {
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
	public Note findNoteByDate(Timestamp date) {
		String query = "SELECT * FROM note WHERE date = ?";
		Note note = null;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setTimestamp(1, date);
			ResultSet rs = ps.executeQuery();
			rs.next();
			note = new Note(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5));
			
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return note;
	}
	
	public List<Note> findNotesById(int worker_id) {
		String query = "SELECT * FROM note WHERE worker_id = ?";
		List<Note> listOfNotes = new ArrayList<Note>();
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, worker_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listOfNotes.add(new Note(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5)));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return listOfNotes;
		
	}
	@Override
	public List<Note> findNotesByPatient(Patient patient) throws SQLException {
		String query = "SELECT * FROM note WHERE diagnosis_id = ?";
		List<Note> listOfNotes = new ArrayList<Note>();
		PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, patient.getDiagnosis_id());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listOfNotes.add(new Note(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5)));
			}
		return listOfNotes;
	}
	@Override
	public List<Note> findAllForPage(int start, int recordsOnPage) {
		String query = "SELECT * FROM note "
				+ "LEFT JOIN diagnosis_note ON diagnosis_note.note_id = note.id "
				+ "LEFT JOIN diagnosis ON diagnosis_note.diagnosis_id = diagnosis.id "
				+ "LEFT JOIN patient ON diagnosis.id = patient.diagnosis_id "
				+ "LEFT JOIN user ON user.id = note.worker_id;";
		List<Note> listOfNotes = new ArrayList<Note>();;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			for(int i = 0; i < start + recordsOnPage; i++) {
				rs.next();
				if(i >= start && i < start + recordsOnPage) {
					Note note = new Note();
					note.setId(rs.getInt(1));
					note.setWorker_id(rs.getInt(2));
					note.setDiagnosis_id(rs.getInt(3));
					note.setCommentary(rs.getString(4));
					note.setDate(rs.getTimestamp(5));
					
					Diagnosis diagnosis = new Diagnosis();
					diagnosis.setId(rs.getInt(8));
					diagnosis.setDescription(rs.getString(9));
					diagnosis.setConclusion(rs.getString(10));
					diagnosis.setProcedures(rs.getString(11));
					diagnosis.setMedicines(rs.getString(12));
					diagnosis.setOperations(rs.getString(13));
					diagnosis.setPatient_id(rs.getInt(14));
					diagnosis.setDoctor_id(rs.getInt(15));
					
					Patient patient = new Patient();
					
					patient.setId(rs.getInt(16));
					patient.setRoom(rs.getInt(17));
					patient.setName(rs.getString(18));
					patient.setSurname(rs.getString(19));
					patient.setDoctor_id(rs.getInt(20));
					patient.setDiagnosis_id(rs.getInt(21));
					patient.setInHospital(rs.getInt(22));
					
					User user = new User();
					
					user.setId(rs.getInt(23));
					user.setLogin(rs.getString(24));
					user.setPassword(rs.getString(25));
					user.setName(rs.getString(26));
					user.setSurname(rs.getString(27));
					user.setRole(ROLE.valueOf(rs.getString(28)));
					
					diagnosis.setPatient(patient);
					note.setDiagnosis(diagnosis);
					note.setUser(user);
					
					listOfNotes.add(note);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
        }
		return listOfNotes;
	}
	@Override
	public void create(Note entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
