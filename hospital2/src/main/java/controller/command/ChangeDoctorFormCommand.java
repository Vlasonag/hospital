package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.User;
import model.enumerations.ROLE;
import model.service.CreateWorkerService;

public class ChangeDoctorFormCommand implements Command{
	
	CreateWorkerService cws;
	
	public ChangeDoctorFormCommand(CreateWorkerService cws) {
		this.cws = cws;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(role.toString().equals("ROLE_ADMIN")) {
			List<User> listOfDoctors = cws.getListOfDoctors();
			System.out.println(listOfDoctors);
			request.setAttribute("listOfDoctors", listOfDoctors);
			return "change_doctor.jsp";
		}
		else {return "forbidden_page.jsp";}
	}

}
