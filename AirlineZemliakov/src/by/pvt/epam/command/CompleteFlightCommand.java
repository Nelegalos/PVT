package by.pvt.epam.command;

import java.util.List;
import java.util.Set;
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

public class CompleteFlightCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(Controller.class);
	private static final String PARAM_NAME_FLIGHT_ID = "flightId";

	@Override
	public String execute(HttpServletRequest request) {

		int flightId = Integer.valueOf(request
				.getParameter(PARAM_NAME_FLIGHT_ID));
		FlightDAO fd = new FlightDAOImpl();
		CrewDAO cd = new CrewDAOImpl();
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> flights = null;
		Set<Employee> crew = null;
		boolean flag = false;
		flag = fd.setFlightCompleted(flightId);
		try {
			crew = cd.findCrewByFlightId(flightId);
			flights = flightDAO.findAllFlights();
		} catch (TechnicalException e) {
			flag = false;
			logger.error("TechnicalException", e);
		}
		for (Employee employee : crew) {
			int employeeId = employee.getId();
			flag = cd.releaseEmployee(employeeId);
			if (flag == false) {
				break;
			}
		}
		if (flag) {
			request.getSession().setAttribute("flights", flights);
			request.setAttribute("flightCompleted", "flight.completed");
		} else {
			request.setAttribute("flightNotCompleted", "flight.notcompleted");
		}
		request.removeAttribute("flightAdded");
		request.removeAttribute("flightNotAdded");
		return ConfigurationManager.getProperty("path.page.admin");
	}
}
