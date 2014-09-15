package com.epam.project.navigation.command.general.test;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_ANSWER;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_MARK;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_NAME;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUBJECT_AREA;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_SUCCESS;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_TEST_ID;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.test.Answer;
import com.epam.project.entity.test.Question;
import com.epam.project.entity.test.Test;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.TestService;

public class ModifyTestCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String JSP_PARAMETR_NAME_QUESTION = "question";
	private static final String JSP_PARAMETR_NAME_INDEX = "index";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		TestService testService = new TestService();
		int testId = Integer.parseInt(request
				.getParameter(JSP_PARAMETR_NAME_TEST_ID));
		Test test;

		try {
			test = testService.getTestByID(testId);
		} catch (TechnicalException e) {
			logger.error(e);
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}

		Set<Question> modifyQuestions = new HashSet<>();
		Set<Question> questions = test.getQuestions();
		int maxMark = 0;
		for (Question question : questions) {
			String markStr = request.getParameter(question.getId()
					+ JSP_PARAMETR_NAME_MARK);
			String questionText = request.getParameter(question.getId()
					+ JSP_PARAMETR_NAME_QUESTION);

			if (isEmpty(markStr, questionText)) {
				request.setAttribute(JSP_PARAMETR_NAME_FAIL,
						BUNDLE_KEY_INPUT_ERROR);
				return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
			}

			int mark = 0;

			try {
				mark = Integer.valueOf(markStr);
			} catch (NumberFormatException e) {
				request.setAttribute(JSP_PARAMETR_NAME_FAIL,
						BUNDLE_KEY_INPUT_ERROR);
				return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
			}

			maxMark += mark;
			int currentIndex = Integer.valueOf(request.getParameter(question
					.getId() + JSP_PARAMETR_NAME_INDEX));
			List<Answer> modifyAnswers = new ArrayList<>();
			List<Answer> answers = question.getAnswers();
			for (Answer answer : answers) {
				String answerText = request.getParameter(answer.getId()
						+ JSP_PARAMETR_NAME_ANSWER);

				if ("".equals(answerText)) {
					request.setAttribute(JSP_PARAMETR_NAME_FAIL,
							BUNDLE_KEY_INPUT_ERROR);
					return ConfigurationManager
							.getProperty(PAGES_FUNCTIONAL_JSP);
				}

				Answer modifyAnswer = new Answer(answerText, answer.getId());
				modifyAnswers.add(modifyAnswer);
			}
			Question modifyQuestion = new Question(questionText, modifyAnswers,
					currentIndex, mark);
			modifyQuestion.setId(question.getId());
			modifyQuestions.add(modifyQuestion);
		}

		String testName = request.getParameter(JSP_PARAMETR_NAME_NAME);
		String testArea = request.getParameter(JSP_PARAMETR_NAME_SUBJECT_AREA);
		Test modifyTest = new Test(modifyQuestions, testName, testArea, maxMark);
		modifyTest.setId(testId);

		boolean modify = testService.modifyTest(modifyTest, testId);
		if (modify) {
			request.setAttribute(JSP_PARAMETR_NAME_SUCCESS,
					BUNDLE_KEY_INPUT_SUCCESS);
		} else {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
		}

		return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
	}

	private boolean isEmpty(String markStr, String questionText) {
		return "".equals(questionText) || "".equals(markStr);
	}

}
