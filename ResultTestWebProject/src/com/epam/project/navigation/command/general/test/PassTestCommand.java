package com.epam.project.navigation.command.general.test;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_FAILED_WRITE_DB;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_MARK;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUCCESS;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_TEST;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_USER;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.entity.test.Question;
import com.epam.project.entity.test.Test;
import com.epam.project.entity.users.User;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

public class PassTestCommand implements Command {
	private static final String BUNDLE_KEY_TEST_MARK = "testMark";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				SESSION_ATTRIBUTE_USER);
		Test test = (Test) request.getSession().getAttribute(
				SESSION_ATTRIBUTE_TEST);
		Set<Question> questions = test.getQuestions();
		int mark = 0;
		for (Question question : questions) {
			String strAnswerIndex = request.getParameter(String
					.valueOf(question.getId()));
			int answerIndex = -1;
			if (strAnswerIndex != null) {
				answerIndex = Integer.parseInt(strAnswerIndex);
			}
			if (answerIndex == question.getCorrectIndex()) {
				mark += question.getMark();
			}
		}
		UserService userService = new UserService();
		boolean setRegister = userService.setRegister(test.getId(),
				user.getId(), mark);
		if (!setRegister) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL,
					BUNDLE_KEY_FAILED_WRITE_DB);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
		request.setAttribute(JSP_PARAMETR_NAME_SUCCESS, BUNDLE_KEY_TEST_MARK);
		request.setAttribute(JSP_PARAMETR_NAME_MARK, mark);
		return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
	}

}
