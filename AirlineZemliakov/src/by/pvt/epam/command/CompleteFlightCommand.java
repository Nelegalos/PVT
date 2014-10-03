package by.pvt.epam.command;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;

public class CompleteFlightCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(CompleteFlightCommand.class);
	private static final String PARAM_NAME_FLIGHT_ID = "flightId";
	private static final String REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS = "completedFlights";
	private static final String REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS = "userFlights";
	private static final String REQUEST_ATTRIBUTE_NAME_FLIGHT_COMPLETED = "flightCompleted";
	private static final String REQUEST_ATTRIBUTE_NAME_FLIGHT_NOT_COMPLETED = "flightNotCompleted";

	private static final String REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE = "flightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE = "isPreviousFlightsPage";
	private static final String REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE = "isNextFlightsPage";

	private static final String REQUEST_ATTRIBUTE_NAME_NO_MORE_FLIGHTS = "noMore";

	@Override
	public String execute(HttpServletRequest request) {

		int flightId = Integer.valueOf(request
				.getParameter(PARAM_NAME_FLIGHT_ID));
		FlightDAO fd = new FlightDAOImpl();
		CrewDAO cd = new CrewDAOImpl();
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> completedFlights = null;
		List<Flight> formedFlights = null;
		Set<Employee> crew = null;
		boolean flag = false;
		flag = fd.setFlightCompleted(flightId);
		try {
			crew = cd.findCrewByFlightId(flightId);
			completedFlights = flightDAO.findStatusFlights(2);
			formedFlights = fd.findFlightsByStatus(1, 0);
		} catch (TechnicalException e) {
			flag = false;
			LOGGER.error("TechnicalException", e);
		}
		for (Employee employee : crew) {
			int employeeId = employee.getId();
			flag = cd.releaseEmployee(employeeId);
			if (flag == false) {
				break;
			}
		}
		if (flag) {
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_COMPLETED_FLIGHTS,
					completedFlights);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_COMPLETED,
					"flight.completed");

			request.setAttribute(REQUEST_ATTRIBUTE_NAME_USER_FLIGHTS,
					formedFlights);
			boolean isPreviousFlightsPage = false;
			request.getSession().setAttribute(
					REQUEST_ATTRIBUTE_NAME_FLIGHTS_PAGE, 0);
			request.getSession().setAttribute(
					REQUEST_ATTRIBUTE_NAME_IS_PREVIOUS_FLIGHTS_PAGE,
					isPreviousFlightsPage);
			boolean isNextFlightsPage = true;
			try {
				if ((fd.findFlightsByStatus(1, 2)).isEmpty()) {
					isNextFlightsPage = false;
				}
				request.getSession().setAttribute(
						REQUEST_ATTRIBUTE_NAME_IS_NEXT_FLIGHTS_PAGE,
						isNextFlightsPage);
			} catch (TechnicalException e) {
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_NO_MORE_FLIGHTS,
						"flight.nomore");
				return ConfigurationManager.getProperty("path.page.admin");
			}

		} else {
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_FLIGHT_NOT_COMPLETED,
					"flight.notcompleted");
		}

		return ConfigurationManager.getProperty("path.page.admin");
	}
}
