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
import static by.pvt.epam.constants.Constants.*;

public class LoginCommand implements ActionCommand {

	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

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

	/**
	 * Find user.
	 * 
	 * @param login
	 *            the login
	 * @param pass
	 *            the password
	 * @return the user
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private User findUser(String login, String pass) throws TechnicalException {
		UserService userService = new UserService();
		User user = userService.findUser(login, pass);
		return user;
	}

	/**
	 * Checks if there are more flights.
	 * 
	 * @param status
	 *            the status
	 * @param startElement
	 *            the start element
	 * @return true, if successful
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private boolean moreFlights(int status, int startElement)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		return !flightService.findFlightsByStatus(status, startElement)
				.isEmpty();
	}

	/**
	 * Sets the common user attributes.
	 * 
	 * @param user
	 *            the user
	 * @param request
	 *            the request
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void setCommonUserAttributes(User user, HttpServletRequest request)
			throws TechnicalException {

		String userName = user.getName() + " " + user.getSurname();
		request.getSession()
				.setAttribute(SESSION_ATTRIBUTE_NAME_USER, userName);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE,
				0);
		request.getSession().setAttribute(
				SESSION_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);

	}

	/**
	 * Sets the dispatcher attributes.
	 * 
	 * @param request
	 *            the new dispatcher attributes
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void setDispatcherAttributes(HttpServletRequest request)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		List<Flight> newFlights = flightService.findFlightsByStatus(0, 0);
		request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS, newFlights);
		request.getSession().setAttribute(
				SESSION_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE, moreFlights(0, 2));
	}

	/**
	 * Sets the administrator attributes.
	 * 
	 * @param request
	 *            the new administrator attributes
	 * @throws TechnicalException
	 *             the technical exception
	 */
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
				SESSION_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE, moreFlights(1, 2));
		request.getSession().setAttribute(
				SESSION_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);
	}
}