package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Role;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.resource.ConfigurationManager;

public class PreviousFlightCommand implements ActionCommand {

	private static Logger logger = Logger
			.getLogger(PreviousFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		int currentFlightsPage = (Integer) request.getSession().getAttribute(
				"flightsPage");
		FlightDAO fd = new FlightDAOImpl();
		Role role = (Role) request.getSession().getAttribute("role");
		int previousPageFlights = currentFlightsPage - 2;
		boolean isPreviousFlightsPage = false;
		isPreviousFlightsPage = false;
		int futurePreviousPageFlights = previousPageFlights - 2;

		switch (role) {
		case ADMIN:
			try {
				if ((fd.findFlightsByStatus(1, previousPageFlights)).isEmpty()) {
					request.setAttribute("noMore", "flight.nomore");
					return ConfigurationManager.getProperty("path.page.admin");
				}
				List<Flight> formedFlights = fd.findFlightsByStatus(1,
						previousPageFlights);
				request.getSession().setAttribute("formedFlights",
						formedFlights);
				request.getSession().setAttribute("flightsPage",
						previousPageFlights);
				if (futurePreviousPageFlights >= 0) {
					isPreviousFlightsPage = true;
				}
				page = ConfigurationManager.getProperty("path.page.admin");
			} catch (DAOException e) {
				logger.error("TechnicalException", e);
				request.setAttribute("noMore", "flight.nomore");
				return ConfigurationManager.getProperty("path.page.admin");
			}
			break;
		case DISPATCHER:
			try {
				if ((fd.findFlightsByStatus(0, previousPageFlights)).isEmpty()) {
					request.setAttribute("noMore", "flight.nomore");
					return ConfigurationManager
							.getProperty("path.page.dispatcher");
				}
				List<Flight> newFlights = fd.findFlightsByStatus(0,
						previousPageFlights);
				request.getSession().setAttribute("newFlights", newFlights);
				request.getSession().setAttribute("flightsPage",
						previousPageFlights);
				if (futurePreviousPageFlights >= 0) {
					isPreviousFlightsPage = true;
				}
				page = ConfigurationManager.getProperty("path.page.dispatcher");
			} catch (DAOException e) {
				logger.error("TechnicalException", e);
				request.setAttribute("noMore", "flight.nomore");
				return ConfigurationManager.getProperty("path.page.dispatcher");
			}
			break;
		}
		boolean isNextFlightsPage = true;
		request.getSession().setAttribute("isNextFlightsPage",
				isNextFlightsPage);
		request.getSession().setAttribute("isPreviousFlightsPage",
				isPreviousFlightsPage);
		return page;
	}
}
