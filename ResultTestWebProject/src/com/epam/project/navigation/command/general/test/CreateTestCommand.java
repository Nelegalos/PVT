package com.epam.project.navigation.command.general.test;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_FAILED_WRITE_DB;
import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUCCESS;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_TEST;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.entity.test.Test;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.TestService;

public class CreateTestCommand implements Command {
	private static final String PAGES_FUNCTIONAL_JSP = "functional";
	
	@Override
	public String execute(HttpServletRequest request) {
		TestService testService = new TestService();
		Test test = (Test) request.getSession().getAttribute(
				SESSION_ATTRIBUTE_TEST);
		boolean addTest = testService.addTest(test);
		if (addTest) {
			request.setAttribute(JSP_PARAMETR_NAME_SUCCESS,
					BUNDLE_KEY_INPUT_SUCCESS);
		} else {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL,
					BUNDLE_KEY_FAILED_WRITE_DB);
		}
		return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);

	}
}
