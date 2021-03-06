package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;
import by.pvt.epam.service.FlightService;
import static by.pvt.epam.constants.Constants.*;

public class FormTeamCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(FormTeamCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request) {

		List<Employee> crew = (List<Employee>) request.getSession()
				.getAttribute(SESSION_ATTRIBUTE_NAME_CREW);

		if (!isTeamFormed(crew)) {
			request.setAttribute(REQUEST_ATTRIBUTE_TEAM_EMPTY, "team.empty");
			return ConfigurationManager.getProperty("path.page.team");
		}
		try {
			int flightId = (Integer) request.getSession().getAttribute(
					SESSION_ATTRIBUTE_NAME_FLIGHT_ID);
			formCrew(crew, flightId);
			setCrewOnAir(crew);
			setFlightOnAir(flightId);
			FlightService flightService = new FlightService();
			List<Flight> newFlights = flightService.findFlightsByStatus(0, 0);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS,
					newFlights);
			request.setAttribute(REQUEST_ATTRIBUTE_TEAM_FORMED, "team.formed");
			request.getSession().setAttribute(
					SESSION_ATTRIBUTE_NAME_FLIGHTS_PAGE, 0);
			request.setAttribute(
					SESSION_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE, false);
			request.setAttribute(SESSION_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE,
					isNextFlightsPage());
		} catch (TechnicalException e) {
			request.setAttribute(REQUEST_ATTRIBUTE_TEAM_NOT_FORMED,
					"team.empty");
			LOGGER.error("TechnicalException", e);
		}
		return ConfigurationManager.getProperty("path.page.dispatcher");
	}

	/**
	 * Checks if team is formed.
	 * 
	 * @param crew
	 *            the crew
	 * @return true, if is team formed
	 */
	private boolean isTeamFormed(List<Employee> crew) {
		for (Employee employee : crew) {
			int id = employee.getId();
			if (id == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if there is next flights page.
	 * 
	 * @return true, if is next flights page
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private boolean isNextFlightsPage() throws TechnicalException {
		FlightService flightService = new FlightService();
		return (flightService.findFlightsByStatus(0, 2)).size() > 0;
	}

	/**
	 * Sets the crew on air.
	 * 
	 * @param crew
	 *            the new crew on air
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void setCrewOnAir(List<Employee> crew) throws TechnicalException {
		CrewService crewService = new CrewService();
		for (Employee employee : crew) {
			int id = employee.getId();
			boolean flag = crewService.addToFlight(id);
			if (!flag) {
				throw new TechnicalException();
			}
		}
	}

	/**
	 * Forms crew.
	 * 
	 * @param crew
	 *            the crew
	 * @param flightId
	 *            the flight id
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void formCrew(List<Employee> crew, int flightId)
			throws TechnicalException {
		CrewService crewService = new CrewService();
		boolean flag = crewService.formCrew(flightId, crew);
		if (!flag) {
			throw new TechnicalException();
		}
	}

	/**
	 * Sets the flight on air.
	 * 
	 * @param flightId
	 *            the new flight on air
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void setFlightOnAir(int flightId) throws TechnicalException {
		FlightService flightService = new FlightService();
		boolean flag = flightService.setFlightOnAir(flightId);
		if (!flag) {
			throw new TechnicalException();
		}
	}
}
