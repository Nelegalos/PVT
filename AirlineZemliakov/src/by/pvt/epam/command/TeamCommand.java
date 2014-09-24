package by.pvt.epam.command;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.resource.ConfigurationManager;

public class TeamCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(TeamCommand.class);
	private static final String PARAM_NAME_FLIGHT_ID = "flight";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.dispatcher");
		int flightId = Integer.valueOf(request
				.getParameter(PARAM_NAME_FLIGHT_ID));
		request.getSession().setAttribute("flightId", flightId);
		FlightDAO fdi = new FlightDAOImpl();
		CrewDAO cdi = new CrewDAOImpl();
		List<Employee> employees = null;
		Flight flight = null;
		try {
			flight = fdi.findFlightById(flightId);
			employees = cdi.findAvailableEmployees();
			request.getSession().setAttribute("employees", employees);
		} catch (DAOException e) {
			logger.error("TechnicalException", e);
			request.setAttribute("teamNotFormed", "team.empty");
			return page;
		}
		Plane plane = flight.getPlane();
		List<Employee> flightCrew = new ArrayList<>();
		int pilot = plane.getPilot();
		for (int i = 0; i < pilot; i++) {
			Employee employee = new Employee();
			employee.setPosition(Position.PILOT);
			flightCrew.add(employee);
		}
		int navigator = plane.getNavigator();
		for (int i = 0; i < navigator; i++) {
			Employee employee = new Employee();
			employee.setPosition(Position.NAVIGATOR);
			flightCrew.add(employee);
		}
		int radioman = plane.getRadioman();
		for (int i = 0; i < radioman; i++) {
			Employee employee = new Employee();
			employee.setPosition(Position.RADIOMAN);
			flightCrew.add(employee);
		}
		int steward = plane.getSteward();
		for (int i = 0; i < steward; i++) {
			Employee employee = new Employee();
			employee.setPosition(Position.STEWARD);
			flightCrew.add(employee);
		}
		request.getSession().setAttribute("crew", flightCrew);
		page = ConfigurationManager.getProperty("path.page.team");
		return page;
	}

}
