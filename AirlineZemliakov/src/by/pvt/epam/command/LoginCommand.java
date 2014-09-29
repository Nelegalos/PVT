package by.pvt.epam.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.dao.UserDAO;
import by.pvt.epam.dao.UserDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.entity.Role;
import by.pvt.epam.entity.User;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.resource.ConfigurationManager;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static Logger logger = Logger.getLogger(LoginCommand.class);

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
			List<Flight> statusFlights = new ArrayList<Flight>();
			for (Flight flight : flights) {
				if (flight.getStatus() == 0) {
					statusFlights.add(flight);
				}
			}
			int flightsPage = 0;
			request.getSession().setAttribute("flightsPage", flightsPage);
			Role role = body.getRole();
			request.getSession().setAttribute("role", role);
			switch (role) {
			case ADMIN:
				FlightDAO fd = new FlightDAOImpl();
				request.getSession().setAttribute("flights", flights);
				List<Plane> planes = fd.findAllPlanes();
				request.getSession().setAttribute("planes", planes);

				List<Flight> formedFlights = fd.findFlightsByStatus(1,
						flightsPage);
				request.getSession().setAttribute("formedFlights",
						formedFlights);

				boolean isNextFlightsPage = true;

				int nextPageFlights = flightsPage + 2;
				if ((fd.findFlightsByStatus(1, nextPageFlights)).isEmpty()) {
					isNextFlightsPage = false;
				}
				request.getSession().setAttribute("isNextFlightsPage",
						isNextFlightsPage);
				boolean isPreviousFlightsPage = false;
				request.getSession().setAttribute("isPreviousFlightsPage",
						isPreviousFlightsPage);

				page = ConfigurationManager.getProperty("path.page.admin");
				break;
			case DISPATCHER:
				request.getSession().setAttribute("flights", statusFlights);
				page = ConfigurationManager.getProperty("path.page.dispatcher");
				break;
			default:
				page = ConfigurationManager.getProperty("path.page.index");
			}
		} catch (DAOException e) {
			request.setAttribute("errorLoginPassMessage", "login.error");
			page = ConfigurationManager.getProperty("path.page.login");
			logger.error("TechnicalException", e);
		}
		return page;
	}
}