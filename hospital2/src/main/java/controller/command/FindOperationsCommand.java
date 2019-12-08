package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.entity.Diagnosis;
import model.enumerations.ROLE;
import model.service.FindDiagnosisService;

public class FindOperationsCommand implements Command{
	
	FindDiagnosisService findDiagnosisService;
	
	public FindOperationsCommand (FindDiagnosisService findDiagnosisService) {
		this.findDiagnosisService = findDiagnosisService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		
		if(role.toString().equals("ROLE_DOCTOR")) {
			try{
				final int room = Integer.parseInt(request.getParameter("room"));
				final String name = request.getParameter("name");
				final String surname = request.getParameter("surname");
				final int patient_id = findDiagnosisService.findPatientIdByRoomNameSurname(room, name, surname);
				final int user_id = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
				Diagnosis diagnosis = findDiagnosisService.findDiagnosisByPatientIdAndDoctorId(patient_id, user_id);	
				
				request.setAttribute("diagnosis", diagnosis);
				request.setAttribute("room", room);
				request.setAttribute("name", name);
				request.setAttribute("surname", surname);
				request.setAttribute("msg", surname + " " + name + " was found");
				
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						" нашел операции пациента, patient_id = " + patient_id);
				return "operations_page.jsp";
			}
			catch (Exception e) {
				
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						"не нашел операции");
				request.setAttribute("msg", "Patient not found");
				return "operations_page.jsp";
			}
		}
		else {return "forbidden_page.jsp";}
	}

}
