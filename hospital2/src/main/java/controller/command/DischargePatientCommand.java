package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.entity.Patient;
import model.enumerations.ROLE;
import model.service.DischargePatientService;

public class DischargePatientCommand implements Command{
	
	DischargePatientService dps;
	
	public DischargePatientCommand(DischargePatientService dps) {
		this.dps = dps;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		
		
		final HttpSession session = request.getSession();
		ROLE role = (ROLE)session.getAttribute("ROLE");
		
		if(role.toString().equals("ROLE_ADMIN")) {
			int id = Integer.parseInt(request.getParameter("patient_id").toString());
			dps.dischargePatientById(id);
			List<Patient> listOfPatients = dps.getAllPatientsInHospital();
			request.setAttribute("listOfPatients", listOfPatients);
			logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
					" выписал пациента, patient_id = " + id);
			return "discharge_page.jsp";
		}
		else {
			return "forbidden_page.jsp";
		}
	}

}
