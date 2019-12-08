package controller.command;

import java.util.List; 

import javax.servlet.http.HttpServletRequest;

import model.entity.Note;
import model.service.FindNotesService;

public class AllNotesCommand implements Command{
	
	FindNotesService findNotesService;
	
	public AllNotesCommand(FindNotesService findNotesService) {
		this.findNotesService = findNotesService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		
		request.getSession().setAttribute("state", "1");
		int page = 1;
        int recordsPerPage = 2;
        List<Note> listOfNotes = findNotesService.findAllNotes();
        int noOfRecords  = listOfNotes.size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
        if(request.getParameter("page") != null) {
        	page = Integer.parseInt(request.getParameter("page"));
        }
        
		listOfNotes = findNotesService.findAllForPage((page-1), recordsPerPage, listOfNotes);
		
		request.setAttribute("listOfNotes", listOfNotes);
		request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        
        logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + " получил список заметок на странице " + page);
        
		return "journal_page.jsp";
	}

}
