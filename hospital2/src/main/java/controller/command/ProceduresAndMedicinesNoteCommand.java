package controller.command;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import model.entity.Diagnosis;
import model.service.FindDiagnosisService;

public class ProceduresAndMedicinesNoteCommand implements Command{
	
	FindDiagnosisService findDiagnosisService;
	
	public ProceduresAndMedicinesNoteCommand(FindDiagnosisService findDiagnosisService) {
		this.findDiagnosisService = findDiagnosisService;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		try{
			final int worker_id = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
			final int room = Integer.parseInt(request.getParameter("room"));
			final String name = request.getParameter("name");
			final String surname = request.getParameter("surname");
			final String commentary = request.getParameter("commentary");
			int patient_id = findDiagnosisService.findPatientIdByRoomNameSurname(room, name, surname); 
			LocalDateTime now = LocalDateTime.now();
			Timestamp sqlNow = Timestamp.valueOf(now);
			sqlNow.toInstant().minus(Duration.ofHours(3));
			Diagnosis diagnosis = findDiagnosisService.findDiagnosisByPatientId(room, name, surname);
			String path = request.getRequestURI();
	        path = path.replaceAll(".*/hospital2/", "");
			findDiagnosisService.createNote(worker_id, diagnosis.getId(), commentary, sqlNow, patient_id);
			logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + 
					" создал запись: \"" + commentary + "\" по процедурам и медикаментам, diagnosis_id = " + diagnosis.getId());
			return "procedures_and_medicines_page.jsp";
		}
		catch(Exception e) {
			return "procedures_and_medicines_page.jsp";
		}
	}

}
