package by.pvt.epam.command;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;
import by.pvt.epam.service.FlightService;
import static by.pvt.epam.constants.Constants.*;

public class TeamCommand implements ActionCommand {

	private static final Logger LOGGER = Logger.getLogger(TeamCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			int flightId = Integer.valueOf(request
					.getParameter(PARAM_NAME_FLIGHT));
			request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_FLIGHT_ID,
					flightId);
			CrewService crewService = new CrewService();
			List<Employee> employees = crewService.findAvailableEmployees();
			request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_EMPLOYEES,
					employees);
			FlightService flightService = new FlightService();
			Flight flight = flightService.findFlightById(flightId);
			List<Employee> flightCrew = createCrew(flight);
			request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_CREW,
					flightCrew);
			page = ConfigurationManager.getProperty("path.page.team");
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_TEAM_NOT_FORMED,
					"team.empty");
			page = ConfigurationManager.getProperty("path.page.dispatcher");
		}
		return page;
	}

	/**
	 * Creates the crew.
	 * 
	 * @param flight
	 *            the flight
	 * @return the list
	 */
	private List<Employee> createCrew(Flight flight) {
		Plane plane = flight.getPlane();
		List<Employee> flightCrew = new ArrayList<>();
		addToCrew(plane.getPilot(), flightCrew, Position.PILOT);
		addToCrew(plane.getNavigator(), flightCrew, Position.NAVIGATOR);
		addToCrew(plane.getRadioman(), flightCrew, Position.RADIOMAN);
		addToCrew(plane.getSteward(), flightCrew, Position.STEWARD);
		return flightCrew;
	}

	/**
	 * Adds the to crew.
	 * 
	 * @param quantity
	 *            the quantity
	 * @param flightCrew
	 *            the flight crew
	 * @param position
	 *            the position
	 */
	private void addToCrew(int quantity, List<Employee> flightCrew,
			Position position) {
		for (int i = 0; i < quantity; i++) {
			Employee employee = new Employee();
			employee.setPosition(position);
			flightCrew.add(employee);
		}
	}

}
