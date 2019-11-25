package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.Note;
import model.enumerations.ROLE;
import model.service.FindNotesService;

public class SortMyNotesByDateCommand implements Command{

FindNotesService fns;
	
	public SortMyNotesByDateCommand(FindNotesService fns) {
		this.fns = fns;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(!role.toString().equals("ROLE_UNKNOWN")) { 
			final int worker_id = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
			List<Note> listOfNotes = fns.findAllById(worker_id);
			String state = request.getParameter("state");
			if(state.equals("1")) {
				listOfNotes = fns.sortNoteListByDate(listOfNotes);
				request.setAttribute("listOfNotes", listOfNotes);
				request.setAttribute("state", "0");
				
				return "my_journal_page.jsp";
			}
			if(state.equals("0")) {
				listOfNotes = fns.sortNoteListByDateReverse(listOfNotes);
				request.setAttribute("listOfNotes", listOfNotes);
				request.setAttribute("state", "1");
				return "my_journal_page.jsp";
			}
			else {
				
				return "main_page.jsp";
			}
		}
		else{return "login_page.jsp";}
	}
	
}
