package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.enumerations.ROLE;

public class CreateWorkerFormCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		
		if(role.toString().equals("ROLE_ADMIN")) {return "creation_page.jsp";}
		else {return "forbidden_page.jsp";}
	}
}
