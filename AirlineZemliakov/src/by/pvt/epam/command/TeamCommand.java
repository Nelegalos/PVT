package by.pvt.epam.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.resource.MessageManager;

public class TeamCommand implements ActionCommand {
	private static final String PARAM_NAME_FLIGHT = "flight";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.team");

		int flightId = Integer.valueOf(request.getParameter(PARAM_NAME_FLIGHT));
		FlightDAO fdi = new FlightDAOImpl();
		Flight flight = fdi.findFlightById(flightId);
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
		request.setAttribute("crew", flightCrew);

		CrewDAO cdi = new CrewDAOImpl();
		List<Employee> employees = null;
		try {
			employees = cdi.findAvailableEmployees();
		} catch (TechnicalException e) {
			request.setAttribute("errorLoginPassMessage",
					MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");

		}
		request.setAttribute("employees", employees);

		return page;
	}

}