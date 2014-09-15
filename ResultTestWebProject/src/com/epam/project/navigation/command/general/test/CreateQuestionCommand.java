package com.epam.project.navigation.command.general.test;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.navigation.command.Command;

public class CreateQuestionCommand implements Command {
	private static final String PAGES_ADD_QUESTION_JSP = "addQuestion";
	
	@Override
	public String execute(HttpServletRequest request) {
		return ConfigurationManager.getProperty(PAGES_ADD_QUESTION_JSP);
	}

}
