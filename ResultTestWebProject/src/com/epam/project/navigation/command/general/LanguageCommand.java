package com.epam.project.navigation.command.general;

import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_BUNDLE;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_LANG;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.navigation.command.Command;

public class LanguageCommand implements Command {
	private static final String JSP_PARAMETR_NAME_LANGUAGE = "language";
	private static final String BUNDLE_PATH = "bundle";
	private static final String PAGES_LOGIN_JSP = "login";

	@Override
	public String execute(HttpServletRequest request) {
		String lang = request.getParameter(JSP_PARAMETR_NAME_LANGUAGE);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_LANG, lang);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_BUNDLE,
				ConfigurationManager.getProperty(BUNDLE_PATH));
		return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
	}

}
