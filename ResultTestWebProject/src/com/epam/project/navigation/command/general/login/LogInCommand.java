package com.epam.project.navigation.command.general.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_LOGIN;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_PASSWORD;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_USER;

public class LogInCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String PAGES_LOGIN_JSP = "login";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		UserService userService = new UserService();
		String login = request.getParameter(JSP_PARAMETR_NAME_LOGIN);
		String password = request.getParameter(JSP_PARAMETR_NAME_PASSWORD);
		User user;
		try {
			user = userService.getUser(login, password);
			request.getSession().setAttribute(SESSION_ATTRIBUTE_USER, user);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		} catch (TechnicalException | LogicException e) {
			logger.error(e);
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
		}
	}

}
