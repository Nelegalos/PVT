package com.epam.project.navigation.command.general.functional;

import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_OPTION;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_REGISTER;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_STUDENTS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_TESTS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.StartFunctional;
import com.epam.project.entity.Register;
import com.epam.project.entity.Role;
import com.epam.project.entity.test.Test;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.TestService;
import com.epam.project.service.UserService;

public class TutorFunctionalCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String PAGES_ADD_TEST_JSP = "addTest";
	private static final String PAGES_ALL_RESULTS_JSP = "allResults";
	private static final String PAGES_ALL_STUDENTS_JSP = "allStudents";
	private static final String PAGES_LIST_MODIFY_TEST_JSP = "listModifyTest";
	private static final String PAGES_LOGIN_JSP = "login";

	@Override
	public String execute(HttpServletRequest request) {
		try {
			StartFunctional optinalName = StartFunctional.valueOf(request.getParameter(
					JSP_PARAMETR_NAME_OPTION).toUpperCase());
			UserService userService = new UserService();
			switch (optinalName) {
			case ADDTEST:
				return ConfigurationManager.getProperty(PAGES_ADD_TEST_JSP);
			case MODIFYTEST:
				TestService testService = new TestService();
				List<Test> tests = testService.getTests();
				request.setAttribute(JSP_PARAMETR_NAME_TESTS, tests);
				return ConfigurationManager
						.getProperty(PAGES_LIST_MODIFY_TEST_JSP);
			case GETALLSTUDENTS:
				List<User> students = userService.getUsers(Role.STUDENT);
				request.setAttribute(JSP_PARAMETR_NAME_STUDENTS, students);
				return ConfigurationManager.getProperty(PAGES_ALL_STUDENTS_JSP);
			case GETALLRESULTS:
				List<Register> register = userService.getRegister();
				request.setAttribute(JSP_PARAMETR_NAME_REGISTER, register);
				return ConfigurationManager.getProperty(PAGES_ALL_RESULTS_JSP);
			default:
				throw new LogicException("Such functional doesn't exist");
			}
		} catch (LogicException | TechnicalException e) {
			logger.error(e);
			return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
		}
	}
}
