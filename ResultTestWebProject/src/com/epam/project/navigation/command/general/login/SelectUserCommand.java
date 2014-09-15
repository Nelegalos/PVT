package com.epam.project.navigation.command.general.login;

import static com.epam.project.constant.SessionConstants.SESSION_ATTRIBUTE_ROLE;

import javax.servlet.http.HttpServletRequest;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.entity.Role;
import com.epam.project.navigation.command.Command;

public class SelectUserCommand implements Command {
	private static final String JSP_PARAMETR_NAME_ROLE = "role";
	private static final String PAGES_ADD_USER_JSP = "addUser";
	
	@Override
	public String execute(HttpServletRequest request) {
		Role role = Role.valueOf(request.getParameter(JSP_PARAMETR_NAME_ROLE)
				.toUpperCase());
		request.getSession().setAttribute(SESSION_ATTRIBUTE_ROLE, role);
		return ConfigurationManager.getProperty(PAGES_ADD_USER_JSP);
	}

}
