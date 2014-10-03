package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Role;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.FlightService;

public class NextFlightCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(NextFlightCommand.class);
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
			} catch (TechnicalException e) {
				LOGGER.error("TechnicalException", e);
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_ERROR, "error");
				return ConfigurationManager.getProperty("path.page.login");
			}
			page = ConfigurationManager.getProperty("path.page.admin");
			break;
		case DISPATCHER:
			page = ConfigurationManager.getProperty("path.page.dispatcher");
			break;
		}
		goToNextPage(flightsStatus, request);
		return page;
	}

	private void goToNextPage(int flightsStatus, HttpServletRequest request) {
		int currentFlightsPage = (Integer) request.getSession().getAttribute(
				REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE);
		int newPageFlights = currentFlightsPage + 2;
		boolean nextButton = false;
		int nextPageFlights = newPageFlights + 2;
		FlightService flightService = new FlightService();
		try {
			System.out.println(1);
			if (moreFlights(flightsStatus, newPageFlights)) {
				System.out.println(2);
				List<Flight> statusFlights = flightService.findFlightsByStatus(
						flightsStatus, newPageFlights);
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS,
						statusFlights);
				System.out.println(3);
				nextButton = isNextButton(flightsStatus, nextPageFlights);
				request.getSession()
						.setAttribute(
								REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE,
								nextButton);
				request.getSession().setAttribute(
						REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, true);
				request.getSession().setAttribute(
						REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE, newPageFlights);
				System.out.println(4);
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

	private boolean isNextButton(int status, int startElement)
			throws TechnicalException {
		boolean nextButton = false;
		if (moreFlights(status, startElement)) {
			nextButton = true;
		}
		return nextButton;
	}

}
