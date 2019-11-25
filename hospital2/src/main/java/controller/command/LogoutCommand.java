package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		
        session.removeAttribute("user_id");
        session.removeAttribute("ROLE");
        logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
				" вышел из системы");
		return "login_page.jsp";
	}

}
