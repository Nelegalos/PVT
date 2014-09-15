package com.epam.project.navigation.command.general.test;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_TEST_ID;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.TestService;

public class DeleteTestCommand implements Command {
	private static final String PAGES_FUNCTIONAL_JSP = "functional";
	@Override
	public String execute(HttpServletRequest request) {
		TestService testService = new TestService();
		int testId = Integer.parseInt(request
				.getParameter(JSP_PARAMETR_NAME_TEST_ID));
		boolean deleteTest = testService.deleteTest(testId);
		if (deleteTest) {
			request.setAttribute(JSP_PARAMETR_NAME_SUCCESS,
					BUNDLE_KEY_INPUT_SUCCESS);
		} else {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
		}
		return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
	}

}
