package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.entity.Role;
import by.pvt.epam.entity.User;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.FlightService;
import by.pvt.epam.service.UserService;

public class LoginCommand implements ActionCommand {

	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String SESSION_ATTRIBUTE_NAME_ROLE = "role";
	private static final String SESSION_ATTRIBUTE_NAME_USER = "user";
	private static final String SESSION_ATTRIBUTE_NAME_PLANES = "planes";

	private static final String REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE = "flightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE = "isPreviousFlightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE = "isNextFlightsPage";

	private static final String REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS = "completedFlights";
	private static final String REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS = "userFlights";
	private static final String REQUEST_ATTRIBUTE_NAME_LOGIN_ERROR = "errorLoginPassMessage";

	@Override
	public String execute(HttpServletRequest request) {

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		String page = null;
		try {
			User user = findUser(login, pass);
			Role role = user.getRole();
			request.getSession()
					.setAttribute(SESSION_ATTRIBUTE_NAME_ROLE, role);
			setCommonUserAttributes(user, request);
			switch (role) {
			case ADMIN:
				setAdminAttributes(request);
				page = ConfigurationManager.getProperty("path.page.admin");
				break;
			case DISPATCHER:
				setDispatcherAttributes(request);
				page = ConfigurationManager.getProperty("path.page.dispatcher");
				break;
			default:
				page = ConfigurationManager.getProperty("path.page.index");
			}
		} catch (TechnicalException e) {
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_LOGIN_ERROR,
					"login.error");
			page = ConfigurationManager.getProperty("path.page.login");
			LOGGER.error("TechnicalException", e);
		}
		return page;
	}

	private User findUser(String login, String pass) throws TechnicalException {
		UserService userService = new UserService();
		User user = userService.findUser(login, pass);
		return user;
	}

	private boolean moreFlights(int status, int startElement)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		return !flightService.findFlightsByStatus(status, startElement)
				.isEmpty();
	}

	private void setCommonUserAttributes(User user, HttpServletRequest request)
			throws TechnicalException {

		String userName = user.getName() + " " + user.getSurname();
		request.getSession()
				.setAttribute(SESSION_ATTRIBUTE_NAME_USER, userName);
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE,
				0);
		request.getSession().setAttribute(
				REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);

	}

	private void setDispatcherAttributes(HttpServletRequest request)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		List<Flight> newFlights = flightService.findFlightsByStatus(0, 0);
		request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS, newFlights);
		request.getSession().setAttribute(
				REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE, moreFlights(0, 2));
	}

	private void setAdminAttributes(HttpServletRequest request)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		List<Flight> completedFlights = flightService.findStatusFlights(2);
		request.setAttribute(REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS,
				completedFlights);
		List<Plane> planes = flightService.findAllPlanes();
		request.getSession()
				.setAttribute(SESSION_ATTRIBUTE_NAME_PLANES, planes);
		List<Flight> formedFlights = flightService.findFlightsByStatus(1, 0);
		request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS, formedFlights);
		request.getSession().setAttribute(
				REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE, moreFlights(1, 2));
		request.getSession().setAttribute(
				REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);
	}
}