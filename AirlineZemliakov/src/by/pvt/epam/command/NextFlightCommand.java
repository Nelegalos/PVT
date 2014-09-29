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

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		int currentFlightsPage = (Integer) request.getSession().getAttribute(
				"flightsPage");
		FlightDAO fd = new FlightDAOImpl();
		Role role = (Role) request.getSession().getAttribute("role");
		switch (role) {
		case ADMIN:
			int nextPageFlights = currentFlightsPage + 2;
			try {
				if ((fd.findFlightsByStatus(1, nextPageFlights)).isEmpty()) {
					request.setAttribute("noMore", "flight.nomore");
					return ConfigurationManager.getProperty("path.page.admin");
				}
				List<Flight> formedFlights = fd.findFlightsByStatus(1,
						nextPageFlights);
				request.getSession().setAttribute("formedFlights",
						formedFlights);
				request.getSession().setAttribute("flightsPage",
						nextPageFlights);
				boolean isNextFlightsPage = true;
				int futureNextPageFlights = nextPageFlights + 2;
				if ((fd.findFlightsByStatus(1, futureNextPageFlights))
						.isEmpty()) {
					isNextFlightsPage = false;
				}
				request.getSession().setAttribute("isNextFlightsPage",
						isNextFlightsPage);
				boolean isPreviousFlightsPage = true;
				request.getSession().setAttribute("isPreviousFlightsPage",
						isPreviousFlightsPage);
				page = ConfigurationManager.getProperty("path.page.admin");
			} catch (DAOException e) {
				logger.error("TechnicalException", e);
				request.setAttribute("noMore", "flight.nomore");
				return ConfigurationManager.getProperty("path.page.admin");				
			}
			break;
		case DISPATCHER:
			List<Flight> flights = (List<Flight>) request.getSession()
					.getAttribute("flights");
			int nextPage = currentFlightsPage + 1;
			if (flights.size() > nextPage) {
				request.getSession().setAttribute("flightsPage", nextPage);
			}
			page = ConfigurationManager.getProperty("path.page.dispatcher");
			break;
		}
		return page;
	}
}
