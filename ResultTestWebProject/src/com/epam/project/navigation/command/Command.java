package com.epam.project.navigation.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	String execute(HttpServletRequest request);
}
