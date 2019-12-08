package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.enumerations.ROLE;

public class LoginFormCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		
		request.setAttribute("ROLE", ROLE.valueOf("ROLE_UNKNOWN"));
		return "login_page.jsp";
	}

}
