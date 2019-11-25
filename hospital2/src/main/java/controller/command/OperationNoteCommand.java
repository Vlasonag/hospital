package controller.command;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import model.entity.Diagnosis;
import model.enumerations.ROLE;
import model.service.FindDiagnosisService;

public class OperationNoteCommand implements Command{
FindDiagnosisService findDiagnosisService;
	
	public OperationNoteCommand(FindDiagnosisService findDiagnosisService) {
		this.findDiagnosisService = findDiagnosisService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(role.toString().equals("ROLE_DOCTOR")) { 
			try {
				final int worker_id = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
				final int room = Integer.parseInt(request.getParameter("room"));
				final String name = request.getParameter("name");
				final String surname = request.getParameter("surname");
				final String commentary = request.getParameter("commentary");
				int patient_id = findDiagnosisService.findPatientIdByRoomNameSurname(room, name, surname); 
				LocalDateTime now = LocalDateTime.now();
				Timestamp sqlNow = Timestamp.valueOf(now);
				sqlNow.toInstant().minus(Duration.ofHours(3));
				Diagnosis diagnosis = findDiagnosisService.findDiagnosisByPatientIdAndDoctorId(patient_id, worker_id);
				findDiagnosisService.createNote(worker_id, diagnosis.getId(), commentary, sqlNow, patient_id);
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						" создал запись: \"" + commentary + "\" по операции, diagnosis_id = " + diagnosis.getId());
				return "operations_page.jsp";
			}
			catch(Exception e) {
				return "operations_page.jsp";
			}
		}
		else{return "forbidden_page.jsp";}
	}
}
