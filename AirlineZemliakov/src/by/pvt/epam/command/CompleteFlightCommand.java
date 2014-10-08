package by.pvt.epam.command;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;
import by.pvt.epam.service.FlightService;
import static by.pvt.epam.constants.Constants.*;

public class CompleteFlightCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(CompleteFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		try {
			int flightId = Integer.valueOf(request
					.getParameter(PARAM_NAME_COMPLETED_FLIGHT_ID));
			CrewService crewService = new CrewService();
			FlightService flightService = new FlightService();
			Set<Employee> crew = crewService.findCrewByFlightId(flightId);
			completeFlight(flightId);
			releaseCrew(crew);
			List<Flight> formedFlights = flightService
					.findFlightsByStatus(1, 0);
			List<Flight> completedFlights = flightService.findStatusFlights(2);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS,
					completedFlights);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_COMPLETED,
					"flight.completed");
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS,
					formedFlights);
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE, 0);
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);
			request.getSession()
					.setAttribute(SESSION_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE,
							isNextButton());
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_NOT_COMPLETED,
					"flight.notcompleted");
		}
		return ConfigurationManager.getProperty("path.page.admin");
	}

	/**
	 * Checks if there is next button.
	 * 
	 * @return true, if there is next button
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private boolean isNextButton() throws TechnicalException {
		FlightService flightService = new FlightService();
		boolean nextButton = false;
		if (!(flightService.findFlightsByStatus(1, 2).isEmpty())) {
			nextButton = true;
		}
		return nextButton;
	}

	/**
	 * Complete flight.
	 * 
	 * @param flightId
	 *            the flight id
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void completeFlight(int flightId) throws TechnicalException {
		FlightService flightService = new FlightService();
		boolean flag = flightService.setFlightCompleted(flightId);
		if (!flag) {
			throw new TechnicalException();
		}
	}

	/**
	 * Release crew.
	 * 
	 * @param crew
	 *            the crew
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void releaseCrew(Set<Employee> crew) throws TechnicalException {
		CrewService crewService = new CrewService();
		for (Employee employee : crew) {
			int employeeId = employee.getId();
			boolean flag = crewService.releaseEmployee(employeeId);
			if (!flag) {
				throw new TechnicalException();
			}
		}
	}

}
