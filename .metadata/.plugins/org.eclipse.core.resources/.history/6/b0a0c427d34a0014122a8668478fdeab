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

public class NextFlightCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(NextFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		int currentFlightsPage = (Integer) request.getSession().getAttribute(
				"flightsPage");
		FlightDAO fd = new FlightDAOImpl();
		Role role = (Role) request.getSession().getAttribute("role");
		int nextPageFlights = currentFlightsPage + 2;
		boolean isNextFlightsPage = true;
		int futureNextPageFlights = nextPageFlights + 2;
		switch (role) {
		case ADMIN:
			try {
				if ((fd.findFlightsByStatus(1, nextPageFlights)).isEmpty()) {
					request.setAttribute("noMore", "flight.nomore");
					return ConfigurationManager.getProperty("path.page.admin");
				}
				List<Flight> formedFlights = fd.findFlightsByStatus(1,
						nextPageFlights);
				request.getSession().setAttribute("formedFlights",
						formedFlights);
				if ((fd.findFlightsByStatus(1, futureNextPageFlights))
						.isEmpty()) {
					isNextFlightsPage = false;
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
				if ((fd.findFlightsByStatus(0, nextPageFlights)).isEmpty()) {
					request.setAttribute("noMore", "flight.nomore");
					return ConfigurationManager
							.getProperty("path.page.dispatcher");
				}
				List<Flight> newFlights = fd.findFlightsByStatus(0,
						nextPageFlights);
				request.getSession().setAttribute("newFlights", newFlights);
				if ((fd.findFlightsByStatus(0, futureNextPageFlights))
						.isEmpty()) {
					isNextFlightsPage = false;
				}
				page = ConfigurationManager.getProperty("path.page.dispatcher");
			} catch (DAOException e) {
				logger.error("TechnicalException", e);
				request.setAttribute("noMore", "flight.nomore");
				return ConfigurationManager.getProperty("path.page.dispatcher");
			}
			break;
		}
		request.getSession().setAttribute("flightsPage", nextPageFlights);
		boolean isPreviousFlightsPage = true;
		request.getSession().setAttribute("isPreviousFlightsPage",
				isPreviousFlightsPage);
		request.getSession().setAttribute("isNextFlightsPage",
				isNextFlightsPage);
		return page;
	}
}
