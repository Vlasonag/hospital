package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.enumerations.ROLE;
import model.service.CreateWorkerService;

public class CreateWorkerCommand implements Command{
	
	CreateWorkerService cns;
	
	public CreateWorkerCommand(CreateWorkerService cns) {
		this.cns = cns;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role1 = (ROLE)request.getSession().getAttribute("ROLE");
		
		if(role1.toString().equals("ROLE_ADMIN")) {
			
			final String login = request.getParameter("login");
			final String password = request.getParameter("password");
			final String name = request.getParameter("name");
			final String surname = request.getParameter("surname");
			final ROLE role = ROLE.valueOf(request.getParameter("ROLE").toString());
			String msg;
			
			if(!cns.isLoginExist(login) && !cns.isPasswordExist(password)) {
				
				cns.createWorker(login, password, name, surname, role);
				msg = "Worker " + surname + " " + name + " was created";
				request.setAttribute("msg", msg);
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						" создал работника, логин = " + login +", роль = " + role.toString());
				return "creation_page.jsp";
			}
			else {
				
				msg = "Password or Login exist";
				request.setAttribute("msg", msg);
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						": не удалось создать работника, логин = " + login +", пароль = " + password);
				return "creation_page.jsp";
			}
		}
		else {return "forbidden_page.jsp";}
	}
	
}
