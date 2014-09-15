package com.epam.project.navigation.command.general.student;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_USER_ID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

public class ListModifyUserCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String JSP_PARAMETR_NAME_USER = "modifyUser";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";
	private static final String PAGES_MODIFY_USER_JSP = "modifyUser";

	@Override
	public String execute(HttpServletRequest request) {
		UserService userService = new UserService();
		String userStrId = request.getParameter(JSP_PARAMETR_NAME_USER_ID);

		if (userStrId == null) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}

		int userId = Integer.parseInt(userStrId);
		try {
			User modifyUser = userService.getUser(userId);
			request.setAttribute(JSP_PARAMETR_NAME_USER, modifyUser);
			return ConfigurationManager.getProperty(PAGES_MODIFY_USER_JSP);
		} catch (TechnicalException | LogicException e) {
			logger.error(e);
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
	}

}
