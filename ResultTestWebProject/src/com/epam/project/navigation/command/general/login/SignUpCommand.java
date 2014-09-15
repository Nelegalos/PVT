package com.epam.project.navigation.command.general.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.Role;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_FAILED_WRITE_DB;
import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_LOGIN;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_PASSWORD;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FIRST_NAME;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_LAST_NAME;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_USER;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_ROLE;

public class SignUpCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String JSP_PARAMETR_NAME_PHONE = "phone";
	private static final String JSP_PARAMETR_NAME_UNIVERSITY = "university";
	private static final String JSP_PARAMETR_NAME_EXPERIENCE = "experience";
	private static final String PAGES_LOGIN_JSP = "login";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				SESSION_ATTRIBUTE_USER);
		String firstName = request.getParameter(JSP_PARAMETR_NAME_FIRST_NAME);
		String lastName = request.getParameter(JSP_PARAMETR_NAME_LAST_NAME);
		String login = request.getParameter(JSP_PARAMETR_NAME_LOGIN);
		String password = request.getParameter(JSP_PARAMETR_NAME_PASSWORD);

		if (isEmpty(firstName, lastName, login, password)) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			if (isNullUser(user)) {
				return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
			}
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}

		UserService userService = new UserService();
		String other = null;
		Role role = null;
		try {
			if (isNullUser(user)) {
				role = Role.STUDENT;
				other = request.getParameter(JSP_PARAMETR_NAME_UNIVERSITY);
			} else {
				role = (Role) request.getSession().getAttribute(
						SESSION_ATTRIBUTE_ROLE);
				other = getOther(request, role);
			}
		} catch (LogicException e) {
			logger.error(e);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}

		boolean createUser = userService.addUser(firstName, lastName, login,
				password, role, other);

		if (!createUser) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL,
					BUNDLE_KEY_FAILED_WRITE_DB);
			if (isNullUser(user)) {
				return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
			}
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}

		if (isNullUser(user)) {
			try {
				user = userService.getUser(login, password);
				request.getSession().setAttribute(SESSION_ATTRIBUTE_USER, user);
			} catch (TechnicalException | LogicException e) {
				logger.error(e);
				request.setAttribute(JSP_PARAMETR_NAME_FAIL,
						BUNDLE_KEY_INPUT_ERROR);
				return ConfigurationManager.getProperty(PAGES_LOGIN_JSP);
			}
		} else {
			request.setAttribute(JSP_PARAMETR_NAME_SUCCESS,
					BUNDLE_KEY_INPUT_SUCCESS);
		}
		return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
	}

	private String getOther(HttpServletRequest request, Role role)
			throws LogicException {
		String other = null;
		switch (role) {
		case ADMIN:
			other = request.getParameter(JSP_PARAMETR_NAME_PHONE);
			break;
		case TUTOR:
			other = request.getParameter(JSP_PARAMETR_NAME_EXPERIENCE);
			try {
				if (isNotEmpty(other)) {
					Integer.parseInt(other);
				}
			} catch (NumberFormatException e) {
				throw new LogicException(e.getClass().getSimpleName() + " "
						+ e.getMessage());
			}
			break;
		case STUDENT:
			other = request.getParameter(JSP_PARAMETR_NAME_UNIVERSITY);
			break;
		default:
			throw new LogicException("Unknown User");
		}
		return other;
	}

	private boolean isNotEmpty(String other) {
		return !"".equals(other);
	}

	private static boolean isNullUser(User user) {
		return user == null;
	}

	private static boolean isEmpty(String firstName, String lastName,
			String login, String password) {
		return firstName == "" || lastName == "" || login == ""
				|| password == "";
	}

}
