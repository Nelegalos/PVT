package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.resource.ConfigurationManager;

public class DeleteFlightCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(DeleteFlightCommand.class);
	private static final String PARAM_NAME_DELETED_FLIGHT_ID = "delFlightId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.admin");
		String id = request.getParameter(PARAM_NAME_DELETED_FLIGHT_ID);
		if (id == null) {
			return page;
		}
		int flightId = Integer.valueOf(id);
		boolean flag = false;
		FlightDAO fd = new FlightDAOImpl();
		flag = fd.deleteFlight(flightId);
		List<Flight> flights = null;
		try {
			flights = fd.findAllFlights();
		} catch (DAOException e) {
			flag = false;
			logger.error("TechnicalException", e);
		}
		if (flag) {
			request.getSession().setAttribute("flights", flights);
			request.setAttribute("flightDeleted", "flight.deleted");
			request.removeAttribute("flightNotDeleted");
		} else {
			request.setAttribute("flightNotDeleted", "flight.notdeleted");
		}
		return page;
	}
}
