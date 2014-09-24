package com.epam.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.project.database.connection.DBPoolConnection;
import com.epam.project.database.connection.PoolSingleton;
import com.epam.project.navigation.CommandType;
import com.epam.project.navigation.command.Command;

public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TestController.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String log4jLocation = config.getInitParameter("log4j-pass");
		String path = getServletContext().getRealPath(log4jLocation);
		PropertyConfigurator.configure(path);
		PoolSingleton.INSTANCE.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		DBPoolConnection.removePool();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performAction(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performAction(request, response);
	}

	private void performAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		Command command;
		try {
			CommandType currentCommand = CommandType
					.valueOf(page.toUpperCase());
			command = currentCommand.getCommand();
			String nextPage = command.execute(request);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException | IllegalArgumentException
				| NullPointerException e) {
			logger.error("TechnicalException", e);
			request.setAttribute("error", e);
			response.sendError(404);
		}
	}
}
