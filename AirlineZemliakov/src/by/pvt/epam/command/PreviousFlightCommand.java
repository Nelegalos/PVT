package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Role;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.FlightService;
import static by.pvt.epam.constants.Constants.*;

public class PreviousFlightCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(PreviousFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Role role = (Role) request.getSession().getAttribute(
				SESSION_ATTRIBUTE_NAME_ROLE);
		int flightsStatus = 0;
		switch (role) {
		case ADMIN:
			flightsStatus = 1;
			try {
				FlightService flightService = new FlightService();
				List<Flight> completedFlights = flightService
						.findStatusFlights(2);
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS,
						completedFlights);
				page = ConfigurationManager.getProperty("path.page.admin");
			} catch (TechnicalException e) {
				LOGGER.error("TechnicalException", e);
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_ERROR, "error");
				page = ConfigurationManager.getProperty("path.page.login");
			}
			break;
		case DISPATCHER:
			page = ConfigurationManager.getProperty("path.page.dispatcher");
			break;
		}
		goToPreviousPage(flightsStatus, request);
		return page;
	}

	/**
	 * Goes to the previous page.
	 * 
	 * @param flightsStatus
	 *            the flights status
	 * @param request
	 *            the request
	 */
	private void goToPreviousPage(int flightsStatus, HttpServletRequest request) {
		int currentFlightsPage = (Integer) request.getSession().getAttribute(
				SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE);
		int newPageFlights = currentFlightsPage - 2;
		boolean previousButton = false;
		int previousPageFlights = newPageFlights - 2;
		FlightService flightService = new FlightService();
		try {
			if (moreFlights(flightsStatus, newPageFlights)) {
				List<Flight> newFlights = flightService.findFlightsByStatus(
						flightsStatus, newPageFlights);
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS,
						newFlights);
				previousButton = isPreviousButton(previousPageFlights);
				request.getSession().setAttribute(
						SESSION_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE,
						previousButton);
				request.getSession().setAttribute(
						SESSION_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE, true);
				request.getSession().setAttribute(
						SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE, newPageFlights);
			} else {
				throw new TechnicalException();
			}
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_NO_MORE_FLIGHTS,
					"flight.nomore");
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE, currentFlightsPage);
		}
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
	 * Checks if there is previous button.
	 * 
	 * @param previousPageFlights
	 *            the previous page flights
	 * @return true, if is previous button
	 */
	private boolean isPreviousButton(int previousPageFlights) {
		boolean previousButton = false;
		if (previousPageFlights >= 0) {
			previousButton = true;
		}
		return previousButton;
	}

}
