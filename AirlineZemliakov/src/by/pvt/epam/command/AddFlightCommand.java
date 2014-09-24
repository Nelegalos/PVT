package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.controller.Controller;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;

public class AddFlightCommand implements ActionCommand {
	private static Logger logger = Logger.getLogger(Controller.class);
	private static final String PARAM_NAME_FLIGHT_ID = "addedflight";
	private static final String PARAM_NAME_TO = "to";
	private static final String PARAM_NAME_FROM = "from";
	private static final String PARAM_NAME_DATE = "date";
	private static final String PARAM_NAME_PLANE_ID = "plane";

	@Override
	public String execute(HttpServletRequest request) {
		String flightId = request.getParameter(PARAM_NAME_FLIGHT_ID);
		String to = request.getParameter(PARAM_NAME_TO);
		String from = request.getParameter(PARAM_NAME_FROM);
		String date = request.getParameter(PARAM_NAME_DATE);
		String plane = request.getParameter(PARAM_NAME_PLANE_ID);
		String page = ConfigurationManager.getProperty("path.page.admin");
		boolean flag = false;
		try {
			flag = addFlight(flightId, to, from, date, plane);
		} catch (TechnicalException e) {
			logger.error("TechnicalException", e);
		}
		if (flag) {
			request.setAttribute("flightAdded", "flight.added");
		} else {
			request.setAttribute("flightNotAdded", "flight.notadded");
		}
		request.removeAttribute("flightCompleted");
		request.removeAttribute("flightNotCompleted");
		return page;
	}

	private static boolean addFlight(String idInput, String to, String from,
			String dateInput, String planeInput) throws TechnicalException {
		boolean flag = false;
		if (isEmpty(to, from, dateInput)) {
			return flag;
		}
		int flightId;
		int plane;
		try {
			flightId = Integer.valueOf(idInput);
			plane = Integer.valueOf(planeInput);
		} catch (IllegalArgumentException e) {
			return flag;
		}
		FlightDAO fd = new FlightDAOImpl();
		List<Flight> allFlights = fd.findAllFlights();
		for (Flight flight : allFlights) {
			if (flightId == flight.getId()) {
				return flag;
			}
		}
		flag = fd.addFlight(flightId, to, from, dateInput, plane);
		return flag;
	}

	private static boolean isEmpty(String to, String from, String dateInput) {
		return to == "" || from == "" || dateInput == "";
	}

}
