package com.epam.project.navigation.command.general.functional;

import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_OPTION;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_STUDENT_REGISTER;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_TESTS;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_USER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.StartFunctional;
import com.epam.project.entity.Register;
import com.epam.project.entity.test.Test;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.TestService;
import com.epam.project.service.UserService;

public class StudentFunctionalCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String PAGES_ALL_TESTS_JSP = "allTests";
	private static final String PAGES_LOGIN_JSP = "login";
	private static final String PAGES_STUDENT_RESULTS_JSP = "studentResult";
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			StartFunctional optinalName = StartFunctional.valueOf(request.getParameter(
					JSP_PARAMETR_NAME_OPTION).toUpperCase());
			switch (optinalName) {
			case SHOWTESTS:
				TestService testService = new TestService();
				List<Test> tests = testService.getTests();
				request.setAttribute(JSP_PARAMETR_NAME_TESTS, tests);
				return ConfigurationManager.getProperty(PAGES_ALL_TESTS_JSP);
			case GETMYRESULTS:
				UserService userService = new UserService();
				User user = (User) request.getSession().getAttribute(
						SESSION_ATTRIBUTE_USER);
				List<Register> register = userService.getRegister(user.getId());
				request.setAttribute(JSP_PARAMETR_NAME_STUDENT_REGISTER,
						register);
				return ConfigurationManager.getProperty(PAGES_STUDENT_RESULTS_JSP);
			default:
				throw new LogicException("Such functional doesn't exist");
			}
		} catch (LogicException | TechnicalException e) {
			logger.error(e);
			return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
		}
	}
}
