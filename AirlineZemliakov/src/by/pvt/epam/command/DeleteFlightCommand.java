package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.FlightService;
import static by.pvt.epam.constants.Constants.*;

public class DeleteFlightCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(DeleteFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		try {
			int flightId = Integer.valueOf(request
					.getParameter(PARAM_NAME_DELETED_FLIGHT_ID));
			deleteFlight(flightId);
			FlightService flightService = new FlightService();
			List<Flight> completedFlights = flightService.findStatusFlights(2);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS,
					completedFlights);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_DELETED,
					"flight.deleted");
			List<Flight> formedFlights = flightService
					.findFlightsByStatus(1, 0);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS,
					formedFlights);
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE, 0);
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE,
					moreFlights(1, 2));
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_NOT_DELETED,
					"flight.notdeleted");
		}
		return ConfigurationManager.getProperty("path.page.admin");
	}

	/**
	 * Delete flight.
	 * 
	 * @param flightId
	 *            the flight id
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void deleteFlight(int flightId) throws TechnicalException {
		FlightService flightService = new FlightService();
		boolean flag = flightService.deleteFlight(flightId);
		if (!flag) {
			throw new TechnicalException();
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

}
