package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.Note;
import model.enumerations.ROLE;
import model.service.FindNotesService;

public class SortAllNotesByDateCommand implements Command{
	
	FindNotesService fns;
	
	public SortAllNotesByDateCommand(FindNotesService fns) {
		this.fns = fns;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(!role.toString().equals("ROLE_UNKNOWN")) { 
			List<Note> listOfNotes = fns.findAllNotes();
			String state = request.getSession().getAttribute("state").toString();
			if(state.equals("1")) {
				listOfNotes = fns.sortNoteListByDate(listOfNotes);
				int page = 1;
		        int recordsPerPage = 2;
		        if(request.getParameter("page") != null) {
		        	page = Integer.parseInt(request.getParameter("page"));
		        }
		        int noOfRecords  = fns.findAllNotes().size();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				listOfNotes = fns.findAllForPage((page-1), recordsPerPage, listOfNotes);
				request.setAttribute("listOfNotes", listOfNotes);
				request.setAttribute("noOfPages", noOfPages);
		        request.setAttribute("currentPage", page);
				request.getSession().setAttribute("state", "0");
				request.setAttribute("sort", "_sorted_by_date");
				return "journal_page.jsp";
			}
			if(state.equals("0")) {
				listOfNotes = fns.sortNoteListByDateReverse(listOfNotes);
				int page = 1;
		        int recordsPerPage = 2;
		        if(request.getParameter("page") != null) {
		        	page = Integer.parseInt(request.getParameter("page"));
		        }
		        int noOfRecords  = fns.findAllNotes().size();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				listOfNotes = fns.findAllForPage((page-1), recordsPerPage, listOfNotes);
				request.setAttribute("listOfNotes", listOfNotes);
				request.setAttribute("noOfPages", noOfPages);
		        request.setAttribute("currentPage", page);
				request.getSession().setAttribute("state", "1");
				request.setAttribute("sort", "_sorted_by_date");
				return "journal_page.jsp"; 
			}
			else {
				return "main_page.jsp";
			}
		}
		else {return "login_page.jsp";}
	}
}
