package com.epam.project.navigation.command.general.student;

import static com.epam.project.constant.BundleKeyConstants.BUNDLE_KEY_INPUT_ERROR;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_FAIL;
import static com.epam.project.constant.JSPConstants.JSP_PARAMETR_NAME_STUDENT_REGISTER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;
import com.epam.project.entity.Register;
import com.epam.project.exceptions.TechnicalException;
import com.epam.project.navigation.command.Command;
import com.epam.project.service.UserService;

public class StudentCommand implements Command {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String JSP_PARAMETR_NAME_STUDENT = "student";
	private static final String PAGES_STUDENT_RESULTS_JSP = "studentResult";
	private static final String PAGES_FUNCTIONAL_JSP = "functional";

	@Override
	public String execute(HttpServletRequest request) {
		UserService userService = new UserService();
		String student = request.getParameter(JSP_PARAMETR_NAME_STUDENT);
		if (student == null) {
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
		int studentId = Integer.parseInt(student);
		List<Register> register;
		try {
			register = userService.getRegister(studentId);
			request.setAttribute(JSP_PARAMETR_NAME_STUDENT_REGISTER, register);
			return ConfigurationManager.getProperty(PAGES_STUDENT_RESULTS_JSP);
		} catch (TechnicalException e) {
			logger.error(e);
			request.setAttribute(JSP_PARAMETR_NAME_FAIL, BUNDLE_KEY_INPUT_ERROR);
			return ConfigurationManager.getProperty(PAGES_FUNCTIONAL_JSP);
		}
	}

}
