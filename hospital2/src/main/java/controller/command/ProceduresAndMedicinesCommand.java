package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.enumerations.ROLE;

public class ProceduresAndMedicinesCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		ROLE role = (ROLE)request.getSession().getAttribute("ROLE");
		
		if(role.toString().equals("ROLE_DOCTOR") || role.toString().equals("ROLE_NURSE")) {return "procedures_and_medicines_page.jsp";}
		else {return "forbidden_page.jsp";}
	}

}
