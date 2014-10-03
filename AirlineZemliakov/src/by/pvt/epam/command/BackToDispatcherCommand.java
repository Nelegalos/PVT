package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.FlightService;

public class BackToDispatcherCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(BackToDispatcherCommand.class);
	private static final String REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE = "flightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE = "isPreviousFlightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE = "isNextFlightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS = "userFlights";
	private static final String REQUEST_ATTRIBUTE_NAME_ERROR = "error";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		try {
			setDispatcherAttributes(request);
			page = ConfigurationManager.getProperty("path.page.dispatcher");
		} catch (TechnicalException e) {
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_ERROR, "error");
			page = ConfigurationManager.getProperty("path.page.login");
			LOGGER.error("TechnicalException", e);
		}
		return page;
	}

	private void setDispatcherAttributes(HttpServletRequest request)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		List<Flight> newFlights = flightService.findFlightsByStatus(0, 0);
		request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS, newFlights);
		request.setAttribute(REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE,
				moreFlights(0, 2));
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE,
				0);
		request.setAttribute(REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE,
				false);
	}

	private boolean moreFlights(int status, int startElement)
			throws TechnicalException {
		FlightService flightService = new FlightService();
		return !flightService.findFlightsByStatus(status, startElement)
				.isEmpty();
	}

}
