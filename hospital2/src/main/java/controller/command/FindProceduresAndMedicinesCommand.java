package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.entity.Diagnosis;
import model.enumerations.ROLE;
import model.service.FindDiagnosisService;

public class FindProceduresAndMedicinesCommand implements Command{
	
	FindDiagnosisService findDiagnosisService;
	
	public FindProceduresAndMedicinesCommand (FindDiagnosisService findDiagnosisService) {
		this.findDiagnosisService = findDiagnosisService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		
		if(role.toString().equals("ROLE_DOCTOR") || role.toString().equals("ROLE_NURSE")) {
			try {
				final int room = Integer.parseInt(request.getParameter("room"));
				final String name = request.getParameter("name");
				final String surname = request.getParameter("surname");
				Diagnosis diagnosis = findDiagnosisService.findDiagnosisByPatientId(room, name, surname);	
				
				request.setAttribute("diagnosis", diagnosis);
				request.setAttribute("room", room);
				request.setAttribute("name", name);
				request.setAttribute("surname", surname);
				
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						" нашел процедуры и медикаменты пациента, patient_room = " + room);
				return "procedures_and_medicines_page.jsp";
			}
			catch (Exception e ){
				
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
						"не нашел процедуры и медикаменты пациента");
				request.setAttribute("msg", "Patient not found");
				return "procedures_and_medicines_page.jsp";
			}
		}
		else{return "forbidden_page.jsp";}
	}

}
