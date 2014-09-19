package by.pvt.epam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;
import by.pvt.epam.command.ActionCommand;
import by.pvt.epam.command.factory.ActionFactory;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.resource.MessageManager;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -247767155410348813L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String log4jLocation = config.getInitParameter("log4j-location");
		String path = getServletContext().getRealPath(log4jLocation);
		PropertyConfigurator.configure(path);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		ActionFactory actionFactory = new ActionFactory();
		ActionCommand command = actionFactory.defineCommand(request);
		page = command.execute(request);
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			page = ConfigurationManager.getProperty("path.page.index");
			request.getSession().setAttribute("nullPage",
					MessageManager.getProperty("message.nullpage"));
			response.sendRedirect(request.getContextPath() + page);
		}
	}

}
