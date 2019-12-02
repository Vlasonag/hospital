package controller.command;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;

import model.entity.User;
import model.service.SignInService;

public class SignInCommand implements Command{
	
	SignInService signInService;
	
	public SignInCommand(SignInService signInService) {
		this.signInService = signInService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {

        final String login = request.getParameter("login");
        final String password = request.getParameter("password"); 
        final HttpSession session = request.getSession();
        if(signInService.isUserExist(login, password)) {
        	User user = signInService.getUserByLogin(login);
        	session.setAttribute("ROLE", user.getRole());
        	session.setAttribute("user_id", Integer.toString(user.getId()));
        	logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
					" вошел в систему");
        	return "main_page.jsp";
        }
        else {
        	return "login_page.jsp";
        }
		
	}

}
