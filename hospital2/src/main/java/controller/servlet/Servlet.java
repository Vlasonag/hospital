package controller.servlet;

import java.io.IOException;     
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.service.CreateWorkerService;
import model.service.DischargePatientService;
import model.service.FindDiagnosisService;
import model.service.FindNotesService;
import model.service.MakeDiagnosisService;
import model.service.SignInService;
import controller.command.AllNotesCommand;
import controller.command.ChangeDoctorCommand;
import controller.command.ChangeDoctorFormCommand;
import controller.command.Command;
import controller.command.CreateWorkerCommand;
import controller.command.CreateWorkerFormCommand;
import controller.command.DiagnosisFormCommand;
import controller.command.DischargeCommand;
import controller.command.DischargePatientCommand;
import controller.command.EditDiagnosisCommand;
import controller.command.FindDiagnosisCommand;
import controller.command.FindNotesCommand;
import controller.command.FindNotesPageCommand;
import controller.command.FindOperationsCommand;
import controller.command.FindProceduresAndMedicinesCommand;
import controller.command.LoginFormCommand;
import controller.command.LogoutCommand;
import controller.command.MakeDiagnosisCommand;
import controller.command.MyNotesCommand;
import controller.command.OperationNoteCommand;
import controller.command.OperationsCommand;
import controller.command.ProceduresAndMedicinesCommand;
import controller.command.ProceduresAndMedicinesNoteCommand;
import controller.command.SignInCommand;
import controller.command.SortAllNotesByDateCommand;
import controller.command.SortMyNotesByDateCommand;
import controller.command.SortPatientNotesByDateCommand;
import controller.command.SortedNotePaginationCommand;

public class Servlet extends HttpServlet {
	
	final static Logger logger = Logger.getLogger(Command.class);
	private static final long serialVersionUID = 1L;
	Map<String, Command> commands = new HashMap<>();
	
	public void init(){
		commands.put("login_page", new LoginFormCommand());
		commands.put("sign_in", new SignInCommand(new SignInService()));
		commands.put("diagnosis", new DiagnosisFormCommand());
		commands.put("procedures_medicines", new ProceduresAndMedicinesCommand());
		commands.put("operations", new OperationsCommand());
		commands.put("discharge", new DischargeCommand(new DischargePatientService()));
		commands.put("logout", new LogoutCommand());
		commands.put("make_diagnosis", new MakeDiagnosisCommand(new MakeDiagnosisService()));
		commands.put("find_diagnosis", new FindDiagnosisCommand(new FindDiagnosisService()));
		commands.put("find_procedures_medicines", new FindProceduresAndMedicinesCommand(new FindDiagnosisService()));
		commands.put("make_note", new ProceduresAndMedicinesNoteCommand(new FindDiagnosisService()));
		commands.put("find_operations", new FindOperationsCommand(new FindDiagnosisService()));
		commands.put("make_operation_note", new OperationNoteCommand(new FindDiagnosisService()));
		commands.put("journal_all", new AllNotesCommand(new FindNotesService()));
		commands.put("journal_my", new MyNotesCommand(new FindNotesService()));
		commands.put("journal_find", new FindNotesPageCommand());
		commands.put("find_notes", new FindNotesCommand(new FindNotesService()));
		commands.put("discharge_patient", new DischargePatientCommand(new DischargePatientService()));
		commands.put("edit_diagnosis", new EditDiagnosisCommand(new FindDiagnosisService()));
		commands.put("change_doctor_form", new ChangeDoctorFormCommand(new CreateWorkerService()));
		commands.put("creation", new CreateWorkerFormCommand());
		commands.put("register_worker", new CreateWorkerCommand(new CreateWorkerService()));
		commands.put("change_doctor", new ChangeDoctorCommand(new CreateWorkerService(), new FindDiagnosisService()));
		commands.put("journal_all_sorted_by_date", new SortAllNotesByDateCommand(new FindNotesService()));
		commands.put("journal_my_sorted_by_date", new SortMyNotesByDateCommand(new FindNotesService()));
		commands.put("journal_find_sort_by_date", new SortPatientNotesByDateCommand(new FindNotesService()));
		commands.put("journal_all_sorted", new SortedNotePaginationCommand(new FindNotesService()));
		
		
		
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        path = path.replaceAll(".*/hospital2/", "");
        Command command = commands.get(path);
        String page = command.execute(request);	
        request.getRequestDispatcher(page).forward(request,response);
        
        logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + " перешел на " + page);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        path = path.replaceAll(".*/hospital2/", "");
        Command command = commands.get(path);
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request,response);
        
        logger.info("Пользователь, id = " + request.getSession().getAttribute("user_id") + " перешел на " + page);

        
	}
}
