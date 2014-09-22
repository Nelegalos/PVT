package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.controller.Controller;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.entity.Role;
import by.pvt.epam.entity.User;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.exception.LogicException;
import by.pvt.epam.logic.FlightLogic;
import by.pvt.epam.logic.LoginLogic;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.resource.MessageManager;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static Logger logger = Logger.getLogger(Controller.class);

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		String page = null;
		try {
			User body = LoginLogic.checkLogin(login, pass);
			String user = body.getName() + " " + body.getSurname();
			request.getSession().setAttribute("user", user);
			List<Flight> flights = FlightLogic.findAllFlights();
			request.getSession().setAttribute("flights", flights);
			Role role = body.getRole();
			switch (role) {
			case ADMIN:
				List<Plane> planes = FlightLogic.findAllPlanes();
				request.getSession().setAttribute("planes", planes);
				page = ConfigurationManager.getProperty("path.page.admin");
				break;
			case DISPATCHER:
				page = ConfigurationManager.getProperty("path.page.dispatcher");
				break;
			}
		} catch (LogicException e) {
			request.setAttribute("errorLoginPassMessage",
					MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
			logger.error(e);
		} catch (DAOException e) {
			request.setAttribute("wrongAction",
					MessageManager.getProperty("message.daofail"));
			page = ConfigurationManager.getProperty("path.page.login");
			logger.error(e);
		}
		return page;
	}
}