package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Role;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.FlightService;

public class PreviousFlightCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(PreviousFlightCommand.class);
	private static final String SESSION_ATTRIBUTE_NAME_ROLE = "role";

	private static final String REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE = "flightsPage";

	private static final String REQUEST_ATTRIBUTE_NAME_NO_MORE_FLIGHTS = "noMore";

	private static final String REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE = "isPreviousFlightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE = "isNextFlightsPage";

	private static final String REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS = "userFlights";
	private static final String REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS = "completedFlights";
	private static final String REQUEST_ATTRIBUTE_NAME_ERROR = "error";

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

	private void goToPreviousPage(int flightsStatus, HttpServletRequest request) {
		int currentFlightsPage = (Integer) request.getSession().getAttribute(
				REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE);
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
						REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE,
						previousButton);
				request.getSession().setAttribute(
						REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE, true);
				request.getSession().setAttribute(
						REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE, newPageFlights);
			} else {
				throw new TechnicalException();
			}
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_NO_MORE_FLIGHTS,
					"flight.nomore");
			request.getSession().setAttribute(
					REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE, currentFlightsPage);
		}
	}

	private boolean moreFlights(int status, int startElement)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		return !flightService.findFlightsByStatus(status, startElement)
				.isEmpty();
	}

	private boolean isPreviousButton(int previousPageFlights) {
		boolean previousButton = false;
		if (previousPageFlights >= 0) {
			previousButton = true;
		}
		return previousButton;
	}

}
