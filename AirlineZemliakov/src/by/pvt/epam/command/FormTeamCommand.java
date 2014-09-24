package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.controller.Controller;
import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;

public class FormTeamCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(Controller.class);
	private static final String PARAM_NAME_FLIGHT_ID = "flightId";

	@Override
	public String execute(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		List<Employee> crew = (List<Employee>) request.getSession()
				.getAttribute("crew");
		for (Employee employee : crew) {
			int id = employee.getId();
			if (id == 0) {
				request.setAttribute("teamEmpty", "team.empty");
				return ConfigurationManager.getProperty("path.page.team");
			}
		}
		boolean flag = false;
		CrewDAO cd = new CrewDAOImpl();
		for (Employee employee : crew) {
			int id = employee.getId();
			flag = cd.addToFlight(id);
			if (flag == false) {
				break;
			}
		}
		int flightId = (Integer) request.getSession().getAttribute(
				PARAM_NAME_FLIGHT_ID);
		flag = cd.formCrew(flightId, crew);
		FlightDAO flightDAO = new FlightDAOImpl();
		flag = flightDAO.setFlightOnAir(flightId);
		List<Flight> flights = null;
		try {
			flights = flightDAO.findUnformedFlights();
		} catch (TechnicalException e) {
			flag = false;
			logger.error("TechnicalException", e);
		}
		if (flag) {
			request.getSession().setAttribute("flights", flights);
			request.setAttribute("teamFormed", "team.formed");
		} else {
			request.setAttribute("teamNotFormed", "team.empty");
		}
		return ConfigurationManager.getProperty("path.page.dispatcher");
	}
}
