package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.entity.Patient;
import model.enumerations.ROLE;
import model.service.DischargePatientService;

public class DischargeCommand implements Command{
	
	DischargePatientService dps;
	
	public DischargeCommand(DischargePatientService dps) {
		this.dps = dps;
	}
	@Override
	public String execute(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		ROLE role = (ROLE)session.getAttribute("ROLE");
		List<Patient> listOfPatients = dps.getAllPatientsInHospital();
		request.setAttribute("listOfPatients", listOfPatients);
		if(role.toString().equals("ROLE_ADMIN")) {
			return "discharge_page.jsp";
		}
		else {
			return "forbidden_page.jsp";
		}
	}

}
