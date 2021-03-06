package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.FlightService;
import static by.pvt.epam.constants.Constants.*;

public class AddFlightCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(AddFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String flightId = request.getParameter(PARAM_NAME_FLIGHT_ID);
		String to = request.getParameter(PARAM_NAME_TO);
		String from = request.getParameter(PARAM_NAME_FROM);
		String date = request.getParameter(PARAM_NAME_DATE);
		String plane = request.getParameter(PARAM_NAME_PLANE_ID);
		try {
			FlightService flightService = new FlightService();
			List<Flight> completedFlights = flightService.findStatusFlights(2);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS,
					completedFlights);
			List<Flight> formedFlights = flightService
					.findFlightsByStatus(1, 0);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS,
					formedFlights);
			addFlight(flightId, to, from, date, plane);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_ADDED,
					"flight.added");
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE, 0);
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE,
					moreFlights(1, 2));
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_NOT_ADDED,
					"flight.notadded");
		}
		return ConfigurationManager.getProperty("path.page.admin");
	}

	/**
	 * Adds the flight.
	 * 
	 * @param idInput
	 *            the id of the inputed flight
	 * @param to
	 *            destination
	 * @param from
	 *            departure
	 * @param dateInput
	 *            the date input
	 * @param planeInput
	 *            the plane input
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void addFlight(String idInput, String to, String from,
			String dateInput, String planeInput) throws TechnicalException {
		if (isEmpty(to, from, dateInput)) {
			throw new TechnicalException();
		}
		int flightId = 0;
		int plane = 0;
		try {
			flightId = Integer.valueOf(idInput);
			plane = Integer.valueOf(planeInput);
		} catch (IllegalArgumentException e) {
			throw new TechnicalException(e);
		}
		FlightService flightService = new FlightService();
		List<Flight> allFlights = flightService.findAllFlights();
		for (Flight flight : allFlights) {
			if (flightId == flight.getId()) {
				throw new TechnicalException();
			}
		}
		boolean flag = flightService.addFlight(flightId, to, from, dateInput,
				plane);
		if (!flag) {
			throw new TechnicalException();
		}
	}

	/**
	 * Checks if input is empty.
	 * 
	 * @param to
	 *            the to
	 * @param from
	 *            the from
	 * @param dateInput
	 *            the date input
	 * @return true, if is empty
	 */
	private boolean isEmpty(String to, String from, String dateInput) {
		return to.isEmpty() || from.isEmpty() || dateInput.isEmpty();
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
