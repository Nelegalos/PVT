package com.epam.project.navigation.command.general.test;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_ANSWER;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_MARK;
import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_TEST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.entity.test.Answer;
import com.epam.project.entity.test.Question;
import com.epam.project.entity.test.Test;
import com.epam.project.navigation.command.Command;

public class AddQuestionCommand implements Command {
	private static final String PAGES_CREATE_TEST_JSP = "createTest";
	private static final String JSP_PARAMETR_NAME_TEXT = "text";
	private static final String PAGES_ADD_QUESTION_JSP = "addQuestion";	

	@Override
	public String execute(HttpServletRequest request) {
		String text = request.getParameter(JSP_PARAMETR_NAME_TEXT);
		String strCorrectIndex = request.getParameter(JSP_PARAMETR_NAME_ANSWER);
		String strMark = request.getParameter(JSP_PARAMETR_NAME_MARK);
		String answerA = request.getParameter("answerA");
		String answerB = request.getParameter("answerB");
		String answerC = request.getParameter("answerC");
		String answerD = request.getParameter("answerD");

		if (isEmpty(text, answerA, answerB, answerC, answerD, strCorrectIndex,
				strMark)) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_ADD_QUESTION_JSP);
		}

		Test test = (Test) request.getSession().getAttribute(
				SESSION_ATTRIBUTE_TEST);
		Set<Question> questions = test.getQuestions();

		if (questions == null) {
			questions = new HashSet<>();
		}

		List<Answer> answers = new ArrayList<>();
		int correctIndex = Integer.parseInt(strCorrectIndex);
		int mark = 0;
		try {
			mark = Integer.parseInt(strMark);
		} catch (NumberFormatException e) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_ADD_QUESTION_JSP);
		}
		answers.add(new Answer(answerA, 0));
		answers.add(new Answer(answerB, 0));
		answers.add(new Answer(answerC, 0));
		answers.add(new Answer(answerD, 0));
		Question question = new Question(text, answers, correctIndex, mark);
		questions.add(question);
		test.setQuestions(questions);
		test.setMaxMark(test.getMaxMark() + mark);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_TEST, test);

		return ConfigurationManager.getProperty(PAGES_CREATE_TEST_JSP);
	}

	private boolean isEmpty(String text, String answerA, String answerB,
			String answerC, String answerD, String strCorrectIndex,
			String strMark) {
		return text == "" || answerA == "" || answerB == "" || answerC == ""
				|| answerD == "" || strCorrectIndex == "" || strMark == "";
	}
}
