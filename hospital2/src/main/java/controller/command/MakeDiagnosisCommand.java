package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.entity.Diagnosis;
import model.entity.Patient;
import model.enumerations.ROLE;
import model.service.MakeDiagnosisService;

public class MakeDiagnosisCommand implements Command{
	
	MakeDiagnosisService makeDiagnosisService;
	
	public MakeDiagnosisCommand(MakeDiagnosisService makeDiagnosisService) {
		this.makeDiagnosisService = makeDiagnosisService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		if(role.toString().equals("ROLE_DOCTOR")) { 
			try {
				final int doctor_id = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
				final int room = Integer.parseInt(request.getParameter("room"));
				final String name = request.getParameter("name");
				final String surname = request.getParameter("surname");
				final String description = request.getParameter("description");
				final String conclusion = request.getParameter("conclusion");
				final String procedures = request.getParameter("procedures");
				final String medicines = request.getParameter("medicines");
				final String operation = request.getParameter("operation");
				final int patient_id = makeDiagnosisService.findPatientIdByRoomNameSurname(room, name, surname);
				final Diagnosis diagnosis = makeDiagnosisService.getDiagnosisByPatientId(patient_id);
				if(makeDiagnosisService.isRoomBusy(room)) {
					request.setAttribute("msg1", "Room is busy");
					return "make_diagnosis_page.jsp";
				}
				makeDiagnosisService.createPatient(room, name, surname, doctor_id);
				final Patient patient = makeDiagnosisService.getPatientByRoomNameSurname(room, name, surname);
				makeDiagnosisService.createDiagnosis(description, conclusion, procedures, 
													medicines, operation, doctor_id, patient.getId());
				makeDiagnosisService.wirePatientAndDiagnosis(patient_id, diagnosis.getId());
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						" создал диагноз, diagnosis_id = " + diagnosis.getId());
				return "make_diagnosis_page.jsp";
				}
				catch(Exception e) {
					request.setAttribute("msg1", "Room is busy");
					return "make_diagnosis_page.jsp";
				}
			}
			else {return "forbidden_page.jsp";}
	}

}
