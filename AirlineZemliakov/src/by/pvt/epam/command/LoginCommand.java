package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.controller.Controller;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.dao.UserDAO;
import by.pvt.epam.dao.UserDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.entity.Role;
import by.pvt.epam.entity.User;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;

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
			UserDAO userDAO = new UserDAOImpl();
			User body = userDAO.findUser(login, pass);
			String user = body.getName() + " " + body.getSurname();
			request.getSession().setAttribute("user", user);
			FlightDAO flightDAO = new FlightDAOImpl();
			List<Flight> flights = flightDAO.findAllFlights();
			request.getSession().setAttribute("flights", flights);
			Role role = body.getRole();
			switch (role) {
			case ADMIN:
				FlightDAO fd = new FlightDAOImpl();
				List<Plane> planes = fd.findAllPlanes();
				request.getSession().setAttribute("planes", planes);
				page = ConfigurationManager.getProperty("path.page.admin");
				break;
			case DISPATCHER:
				page = ConfigurationManager.getProperty("path.page.dispatcher");
				break;
			default:
				page = ConfigurationManager.getProperty("path.page.index");
			}
		} catch (TechnicalException e) {
			request.setAttribute("errorLoginPassMessage", "login.error");
			page = ConfigurationManager.getProperty("path.page.login");
			logger.error("TechnicalException", e);
		}
		return page;
	}
}