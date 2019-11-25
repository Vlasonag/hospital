package controller.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.Note;
import model.entity.Patient;
import model.enumerations.ROLE;
import model.service.FindNotesService;

public class SortPatientNotesByDateCommand implements Command{
FindNotesService fns;
	
	public SortPatientNotesByDateCommand(FindNotesService fns) {
		this.fns = fns;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(!role.toString().equals("ROLE_UNKNOWN")) { 
			final int room = Integer.parseInt(request.getParameter("room"));
			final String name = request.getParameter("name");
			final String surname = request.getParameter("surname");
			
			Patient patient = fns.getPatientByRoomNameSurname(room, name, surname);
			List<Note> listOfNotes;
			try {
				listOfNotes = fns.findAllByPatient(patient);
			} catch (SQLException e) {
				return "main_page.jsp";
			}
			
			String state = request.getParameter("state");
			if(state.equals("1")) {
				listOfNotes = fns.sortNoteListByDate(listOfNotes);
				request.setAttribute("patient", patient);
				request.setAttribute("listOfNotes", listOfNotes);
				request.setAttribute("state", "0");
				
				return "journal_find_page_result.jsp";
			}
			if(state.equals("0")) {
				listOfNotes = fns.sortNoteListByDateReverse(listOfNotes);
				request.setAttribute("patient", patient);
				request.setAttribute("listOfNotes", listOfNotes);
				request.setAttribute("state", "1");
				return "journal_find_page_result.jsp";
			}
			else {
				return "main_page.jsp";
			}
		}
		else {return "login_page.jsp";}
		
	}
}
