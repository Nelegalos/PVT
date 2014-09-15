package com.epam.project.navigation.command.general.functional;

import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_OPTION;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_STUDENTS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.StartFunctional;
import com.epam.project.entity.Role;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

public class AdminFunctionalCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String JSP_PARAMETR_NAME_TUTORS = "tutors";
	private static final String PAGES_LIST_MODIFY_USER_JSP = "listModifyUser";
	private static final String PAGES_SELECT_ROLE_JSP = "selectRole";
	private static final String PAGES_LOGIN_JSP = "login";

	@Override
	public String execute(HttpServletRequest request) {
		try {
			StartFunctional optinalName = StartFunctional.valueOf(request.getParameter(
					JSP_PARAMETR_NAME_OPTION).toUpperCase());
			switch (optinalName) {
			case ADDUSER:
				return ConfigurationManager.getProperty(PAGES_SELECT_ROLE_JSP);
			case MODIFYUSER:
				UserService userService = new UserService();
				List<User> students = userService.getUsers(Role.STUDENT);
				List<User> tutors = userService.getUsers(Role.TUTOR);
				request.setAttribute(JSP_PARAMETR_NAME_STUDENTS, students);
				request.setAttribute(JSP_PARAMETR_NAME_TUTORS, tutors);
				return ConfigurationManager
						.getProperty(PAGES_LIST_MODIFY_USER_JSP);
			default:
				throw new LogicException("Such functional doesn't exist");
			}
		} catch (LogicException | TechnicalException e) {
			logger.error(e);
			return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
		}
	}
}
