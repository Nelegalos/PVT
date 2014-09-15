package com.epam.project.navigation.command.general.student;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_REGISTER;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUCCESS;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

public class AllResultsCommand implements Command {
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		UserService userService = new UserService();
		String registerIdStr = request.getParameter(JSP_PARAMETR_NAME_REGISTER);

		if (registerIdStr == null) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}

		int registerId = Integer.valueOf(registerIdStr);
		boolean deleteRegister = userService.deleteRegister(registerId);

		if (deleteRegister) {
			request.setAttribute(JSP_PARAMETR_NAME_SUCCESS,
					BUNDLE_KEY_INPUT_SUCCESS);
		} else {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
		}
		return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);

	}

}
