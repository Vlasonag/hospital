package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.entity.Diagnosis;
import model.enumerations.ROLE;
import model.service.FindDiagnosisService;

public class EditDiagnosisCommand implements Command{
	
	FindDiagnosisService fds;
	
	public EditDiagnosisCommand(FindDiagnosisService fds) {
		this.fds = fds;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		
		if(role.toString().equals("ROLE_DOCTOR")) {
			
			try {
				final int room = Integer.parseInt(request.getParameter("room"));
				final String name = request.getParameter("name");
				final String surname = request.getParameter("surname");
				final String description = request.getParameter("description");
				final String conclusion = request.getParameter("conclusion");
				final String procedures = request.getParameter("procedures");
				final String medicines = request.getParameter("medicines");
				final String operation = request.getParameter("operation");
				final int patient_id = fds.findPatientIdByRoomNameSurname(room, name, surname);
				Diagnosis diagnosis = new Diagnosis(description, conclusion, procedures, medicines, operation, patient_id);
				
				fds.editDiagnosisByPatientId(patient_id, diagnosis);
				
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						" изменил диагноз пациенту, patient_id = " + patient_id);
				return "make_diagnosis_page.jsp";
			}
			catch(Exception e) {return "make_diagnosis_page.jsp";}
		}
		else {return "forbidden_page.jsp";}
	}

}
