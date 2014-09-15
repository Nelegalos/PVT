package com.epam.project.navigation.command.general.test;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.entity.test.Test;
import com.epam.project.navigation.command.Command;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_NAME;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUBJECT_AREA;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_TEST;

public class AddTestCommand implements Command {
	private static final String PAGES_ADD_QUESTION_JSP = "addQuestion";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		Test test = new Test(null, null, null, 0);
		String testName = request.getParameter(JSP_PARAMETR_NAME_NAME);
		String testSubject = request
				.getParameter(JSP_PARAMETR_NAME_SUBJECT_AREA);
		if (isEmpty(testName, testSubject)) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
		test.setName(testName);
		test.setSubjectArea(testSubject);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_TEST, test);
		return ConfigurationManager.getProperty(PAGES_ADD_QUESTION_JSP);
	}

	private boolean isEmpty(String testName, String testSubject) {
		return testName == "" || testSubject == "";
	}

}
