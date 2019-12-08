package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.enumerations.ROLE;
 
public class FindNotesPageCommand implements Command{
	
	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(role.toString().equals("ROLE_UNKNOWN")) { return "forbidden_page.jsp";}
		return "journal_find_page.jsp";
	}

}
