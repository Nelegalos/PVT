package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.resource.ConfigurationManager;

public class FormTeamCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(FormTeamCommand.class);
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
		int flightsPage = 0;
		int nextPageFlights = flightsPage + 2;
		request.getSession().setAttribute("flightsPage", flightsPage);
		boolean isPreviousFlightsPage = false;
		request.getSession().setAttribute("isPreviousFlightsPage",
				isPreviousFlightsPage);
		boolean flag = false;
		boolean isNextFlightsPage = false;
		CrewDAO cd = new CrewDAOImpl();
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> newFlights = null;
		try {
			
			for (Employee employee : crew) {
				int id = employee.getId();
				flag = cd.addToFlight(id);
				if (flag == false) {
					throw new DAOException();
				}
			}
			int flightId = (Integer) request.getSession().getAttribute(
					PARAM_NAME_FLIGHT_ID);
			flag = flightDAO.setFlightOnAir(flightId);
			if ((flightDAO.findFlightsByStatus(0, nextPageFlights)).size() > 0) {
				isNextFlightsPage = true;
			}
			
			
			if (flag == false) {
				throw new DAOException();
			}
			flag = cd.formCrew(flightId, crew);
			if (flag == false) {
				throw new DAOException();
			}
			newFlights = flightDAO.findFlightsByStatus(0, flightsPage);
		} catch (DAOException e) {
			request.getSession().setAttribute("isNextFlightsPage",
					isNextFlightsPage);
			request.setAttribute("teamNotFormed", "team.empty");
			logger.error("TechnicalException", e);
			return ConfigurationManager.getProperty("path.page.dispatcher");
		}
		request.getSession().setAttribute("newFlights", newFlights);
		request.setAttribute("teamFormed", "team.formed");
		request.getSession().setAttribute("isNextFlightsPage",
				isNextFlightsPage);
		return ConfigurationManager.getProperty("path.page.dispatcher");
	}
}
