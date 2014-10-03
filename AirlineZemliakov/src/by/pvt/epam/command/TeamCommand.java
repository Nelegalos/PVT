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

public class TeamCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(TeamCommand.class);
	private static final String PARAM_NAME_FLIGHT_ID = "flight";
	private static final String SESSION_ATTRIBUTE_NAME_EMPLOYEES = "employees";
	private static final String SESSION_ATTRIBUTE_NAME_CREW = "crew";
	private static final String SESSION_ATTRIBUTE_NAME_FLIGHT_ID = "flightId";
	private static final String SESSION_ATTRIBUTE_NAME_PILOT = "pilot";
	private static final String SESSION_ATTRIBUTE_NAME_NAVIGATOR = "navigator";
	private static final String SESSION_ATTRIBUTE_NAME_RADIOMAN = "radioman";
	private static final String SESSION_ATTRIBUTE_NAME_STEWARD = "steward";
	private static final String REQUEST_ATTRIBUTE_TEAM_NOT_FORMED = "teamNotFormed";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			Flight flight = findFlight(request);
			findEmployees(request);
			createCrew(flight, request);
			page = ConfigurationManager.getProperty("path.page.team");
		} catch (TechnicalException e) {
			logger.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_TEAM_NOT_FORMED,
					"team.empty");
			page = ConfigurationManager.getProperty("path.page.dispatcher");
		}
		return page;
	}

	private void findEmployees(HttpServletRequest request) throws TechnicalException {
		CrewService crewService = new CrewService();
		List<Employee> availableEmployees = crewService
				.findAvailableEmployees();
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_EMPLOYEES,
				availableEmployees);
		request.getSession()
				.setAttribute(SESSION_ATTRIBUTE_NAME_PILOT, "PILOT");
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_NAVIGATOR,
				"NAVIGATOR");
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_RADIOMAN,
				"RADIOMAN");
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_STEWARD,
				"STEWARD");
	}

	private Flight findFlight(HttpServletRequest request) throws TechnicalException {
		int flightId = Integer.valueOf(request
				.getParameter(PARAM_NAME_FLIGHT_ID));
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_FLIGHT_ID,
				flightId);
		FlightService flightService = new FlightService();
		return flightService.findFlightById(flightId);
	}

	private void createCrew(Flight flight, HttpServletRequest request) {
		Plane plane = flight.getPlane();
		List<Employee> flightCrew = new ArrayList<>();
		addToCrew(plane.getPilot(), flightCrew, Position.PILOT);
		addToCrew(plane.getNavigator(), flightCrew, Position.NAVIGATOR);
		addToCrew(plane.getRadioman(), flightCrew, Position.RADIOMAN);
		addToCrew(plane.getSteward(), flightCrew, Position.STEWARD);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_CREW,
				flightCrew);
	}

	private void addToCrew(int quantity, List<Employee> flightCrew,
			Position position) {
		for (int i = 0; i < quantity; i++) {
			Employee employee = new Employee();
			employee.setPosition(position);
			flightCrew.add(employee);
		}
	}

}
