package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.Note;
import model.enumerations.ROLE;
import model.service.FindNotesService;

public class SortedNotePaginationCommand implements Command{
	FindNotesService fns;
	public SortedNotePaginationCommand(FindNotesService fns) {
		this.fns = fns;
	}
	@Override
	public String execute(HttpServletRequest request) {
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(!role.toString().equals("ROLE_UNKNOWN")) { 
			List<Note> listOfNotes = fns.findAllNotes();
			String state = request.getSession().getAttribute("state").toString();
			listOfNotes = fns.sortNoteListByDate(listOfNotes);
			int page = 1;
		    int recordsPerPage = 2;
		    if(request.getParameter("page") != null) {
		        page = Integer.parseInt(request.getParameter("page"));
		    }
		    int noOfRecords  = fns.findAllNotes().size();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			if(state.equals("0")) {listOfNotes = fns.sortNoteListByDate(listOfNotes);}
			else if(state.equals("1")) {listOfNotes = fns.sortNoteListByDateReverse(listOfNotes);}
			listOfNotes = fns.findAllForPage((page-1), recordsPerPage, listOfNotes);
			request.setAttribute("listOfNotes", listOfNotes);
			request.setAttribute("noOfPages", noOfPages);
		    request.setAttribute("currentPage", page);
			return "journal_page.jsp";
			
			}
		else {return "login_page.jsp";}
	}

}
