package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.comparartor.NoteComparator;
import model.dao.DaoFactory;
import model.dao.NoteDao;
import model.entity.Note;
import model.entity.Patient;

public class FindNotesService extends MakeDiagnosisService{
	
	public List<Note> findAllNotes() {
		
		List<Note> listOfNotes = new ArrayList<Note>();
		DaoFactory factory = DaoFactory.getInstance();
	    NoteDao dao = factory.createNoteDao();
	    listOfNotes = dao.findAll();
		return listOfNotes;
	}
	
	public List<Note> findAllById(int worker_id) {
		
		List<Note> listOfNotes = findAllNotes();
		listOfNotes = listOfNotes.stream().filter(var -> var.getWorker_id()	
																== worker_id).collect(Collectors.toList());
		return listOfNotes;
	}
	
	public List<Note> findAllByPatient(Patient patient) throws SQLException{
		
		List<Note> listOfNotes = findAllNotes();
		listOfNotes = listOfNotes.stream().filter(var -> var.getDiagnosis().getPatient().getId()
																== patient.getId()).collect(Collectors.toList());
		return listOfNotes;
	}
	
	public List<Note> sortNoteListByDate(List<Note> listOfNotes) {
		
		listOfNotes = listOfNotes.stream().sorted(new NoteComparator()).collect(Collectors.toList());
		
		return listOfNotes;
	}
	
	public List<Note> sortNoteListByDateReverse(List<Note> listOfNotes) {
		
		List<Note> reversedListOfNotes = sortNoteListByDate(listOfNotes);
		Collections.reverse(reversedListOfNotes);
		return reversedListOfNotes;
	}
	
	public List<Note> findAllForPage(int page, int recordsPerPage, List<Note> listOfNotes) {
		
        return listOfNotes.stream()
	        		.skip(page * recordsPerPage)
	        		.limit(recordsPerPage)
	        		.collect(Collectors.toList());
	}
}
