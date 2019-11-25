package controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public interface Command {
	
	final static Logger logger = Logger.getLogger(Command.class);
    String execute(HttpServletRequest request);
}