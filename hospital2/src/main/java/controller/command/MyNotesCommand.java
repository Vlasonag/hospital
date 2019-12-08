package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.Note;
import model.service.FindNotesService;

public class MyNotesCommand implements Command{

FindNotesService findNotesService;
	
	public MyNotesCommand(FindNotesService findNotesService) {
		this.findNotesService = findNotesService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			int worker_id = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
			List<Note> listOfNotes = findNotesService.findAllById(worker_id);
			
			request.setAttribute("listOfNotes", listOfNotes);
			request.setAttribute("state", "1");
			return "my_journal_page.jsp";
		}
		catch(Exception e){
			return "login_page.jsp";
		}
	}

}
