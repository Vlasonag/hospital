package controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.enumerations.ROLE;

public class DiagnosisFormCommand implements Command{
	
	@Override
	public String execute(HttpServletRequest request) {
		
		final HttpSession session = request.getSession();
		ROLE role = (ROLE)session.getAttribute("ROLE");
		
		if(role.toString().equals("ROLE_DOCTOR")) {return "make_diagnosis_page.jsp";}
		return "forbidden_page.jsp";
	}

}
