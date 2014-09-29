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
		switch (role) {
		case ADMIN:
			int previousPageFlights = currentFlightsPage - 2;
			boolean isPreviousFlightsPage = false;
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
				isPreviousFlightsPage = false;
				int futurePreviousPageFlights = previousPageFlights - 2;
				if (futurePreviousPageFlights >= 0) {
					isPreviousFlightsPage = true;
				}
				request.getSession().setAttribute("isPreviousFlightsPage",
						isPreviousFlightsPage);
				boolean isNextFlightsPage = true;

				request.getSession().setAttribute("isNextFlightsPage",
						isNextFlightsPage);
				page = ConfigurationManager.getProperty("path.page.admin");
			} catch (DAOException e) {
				logger.error("TechnicalException", e);
				request.setAttribute("noMore", "flight.nomore");
				return ConfigurationManager.getProperty("path.page.admin");
			}
			break;
		case DISPATCHER:
			int previousPage = currentFlightsPage - 1;
			if (currentFlightsPage >= 1) {
				request.getSession().setAttribute("flightsPage", previousPage);
			}
			page = ConfigurationManager.getProperty("path.page.dispatcher");
		}
		return page;
	}
}
