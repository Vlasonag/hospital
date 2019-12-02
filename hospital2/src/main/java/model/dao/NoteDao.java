package model.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import model.entity.Diagnosis;
import model.entity.Note;
import model.entity.Patient;

public interface NoteDao extends GenericDao<Note>{
	void create(Note note, int user_id, int patient_id);
	Note findNoteByDate(Timestamp date);
	List<Note> findNotesById(int worker_id);
	List<Note> findNotesByPatient(Patient patient) throws SQLException;
	Diagnosis findDiagnosisForNote(Note note);
	List<Note> findAllForPage(int i, int recordsPerPage);
}
