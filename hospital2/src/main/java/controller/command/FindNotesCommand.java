package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.Note;
import model.entity.Patient;
import model.enumerations.ROLE;
import model.service.FindNotesService;

public class FindNotesCommand implements Command{
	
	FindNotesService findNotesService;
	
	public FindNotesCommand(FindNotesService findNotesService) {
		this.findNotesService = findNotesService;
	}
	@Override
	public String execute(HttpServletRequest request) {
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(role.toString().equals("ROLE_UNKNOWN")) { return "forbidden_page.jsp";}
		try {
			final int room = Integer.parseInt(request.getParameter("room"));
			final String name = request.getParameter("name");
			final String surname = request.getParameter("surname");
			Patient patient = findNotesService.getPatientByRoomNameSurname(room, name, surname);
			List<Note> listOfNotes = findNotesService.findAllByPatient(patient);
			request.setAttribute("patient", patient);
			request.setAttribute("state", "1");
			request.setAttribute("listOfNotes", listOfNotes);
			logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
					" нашел записи пациента, patient_id = " + patient.getId());
			return "journal_find_page_result.jsp";
		}
		catch(Exception e) {
			request.setAttribute("msg", "Notes not found");
			return "journal_find_page.jsp";
		}
	}

}
