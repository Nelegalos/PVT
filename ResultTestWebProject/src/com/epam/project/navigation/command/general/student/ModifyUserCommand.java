package com.epam.project.navigation.command.general.student;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FIRST_NAME;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_LAST_NAME;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_USER_ID;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

public class ModifyUserCommand implements Command {
	private static final String JSP_PARAMETR_NAME_OTHER = "other";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		String firstName = request.getParameter(JSP_PARAMETR_NAME_FIRST_NAME);
		String lastName = request.getParameter(JSP_PARAMETR_NAME_LAST_NAME);
		if (isEmpty(firstName, lastName)) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
		int userId = Integer.parseInt(request
				.getParameter(JSP_PARAMETR_NAME_USER_ID));
		String other = request.getParameter(JSP_PARAMETR_NAME_OTHER);
		UserService userService = new UserService();

		boolean modifyUser = userService.modifyUser(firstName, lastName, other,
				userId);
		if (modifyUser) {
			request.setAttribute(JSP_PARAMETR_NAME_SUCCESS,
					BUNDLE_KEY_INPUT_SUCCESS);
		} else {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
		}
		return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
	}

	private boolean isEmpty(String firstName, String lastName) {
		return "".equals(firstName) || "".equals(lastName);
	}
}
