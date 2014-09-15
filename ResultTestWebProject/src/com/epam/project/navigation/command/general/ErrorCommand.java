package com.epam.project.navigation.command.general;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.navigation.command.Command;

public class ErrorCommand implements Command{
	private static final String PAGES_LANGUAGE_JSP = "language";

	@Override
	public String execute(HttpServletRequest request) {
		return ConfigurationManager.getProperty(PAGES_LANGUAGE_JSP);
	}
}
