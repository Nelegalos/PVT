package com.epam.project.navigation.command.general.test;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_TEST;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_TEST;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.test.Test;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.TestService;

public class TestCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String PAGES_PASS_TEST_JSP = "passTest";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		TestService testService = new TestService();
		String strId = request.getParameter(JSP_PARAMETR_NAME_TEST);
		if (strId == null) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
		int testId = Integer.parseInt(strId);
		Test test;
		try {
			test = testService.getTestByID(testId);
		} catch (TechnicalException e) {
			logger.error(e);
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
		request.getSession().setAttribute(SESSION_ATTRIBUTE_TEST, test);
		return ConfigurationManager.getProperty(PAGES_PASS_TEST_JSP);
	}

}
