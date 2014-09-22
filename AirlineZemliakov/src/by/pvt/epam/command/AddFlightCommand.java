package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.logic.FlightLogic;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.resource.MessageManager;

public class AddFlightCommand implements ActionCommand {
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

		if (FlightLogic.validateAddFlight(flightId, to, from, date, plane)) {
			request.setAttribute("flightAdded",
					MessageManager.getProperty("flight.added"));
		} else {
			request.setAttribute("flightAdded",
					MessageManager.getProperty("flight.notadded"));
		}

		String page = ConfigurationManager.getProperty("path.page.admin");
		return page;
	}

}
