package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.entity.Patient;
import model.entity.User;
import model.enumerations.ROLE;
import model.service.CreateWorkerService;
import model.service.FindDiagnosisService;

public class ChangeDoctorCommand implements Command{
	
	CreateWorkerService cws;
	FindDiagnosisService fds;
	
	public ChangeDoctorCommand(CreateWorkerService cws, FindDiagnosisService fds) {
		this.cws = cws;
		this.fds = fds;
	}
	@Override
	public String execute(HttpServletRequest request) {
		
		try {
			ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
			if(role.toString().equals("ROLE_ADMIN")) {
				final int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
				final int room = Integer.parseInt(request.getParameter("room"));
				final String name = request.getParameter("name");
				final String surname = request.getParameter("surname");
				Patient patient = fds.findPatientByRoomNameSurname(room, name, surname);
				cws.changeDoctor(patient, doctor_id);
				List<User> listOfDoctors = cws.getListOfDoctors();
				request.setAttribute("listOfDoctors", listOfDoctors);
				logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") +
						" поменял пациену id = " + patient.getId() + ", доктора на doctor_id = " + doctor_id);
				return "change_doctor.jsp";
			}
			else {return "forbidden_page.jsp";}
		}
			catch(Exception e) {
				List<User> listOfDoctors = cws.getListOfDoctors();
				request.setAttribute("listOfDoctors", listOfDoctors);
				request.setAttribute("msg", "Patient not found");
				return "change_doctor.jsp";
			}
		}		
	}
