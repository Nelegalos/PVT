package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.logic.FlightLogic;
import by.pvt.epam.resource.ConfigurationManager;

public class FormTeamCommand implements ActionCommand {
	private static final String PARAM_NAME_FLIGHT_ID = "flightId";

	@Override
	public String execute(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		List<Employee> crew = (List<Employee>) request.getSession()
				.getAttribute("crew");
		CrewDAO cd = new CrewDAOImpl();
		for (Employee employee : crew) {
			int id = employee.getId();
			try {
				cd.addToFlight(id);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

		int flightId = (Integer) request.getSession().getAttribute(
				PARAM_NAME_FLIGHT_ID);
		FlightDAO fd = new FlightDAOImpl();
		try {
			fd.setFlightOnAir(flightId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		List<Flight> flights = FlightLogic.findUnformedFlights();
		request.getSession().setAttribute("flights", flights);
		String page = ConfigurationManager.getProperty("path.page.dispatcher");
		return page;
	}
}
