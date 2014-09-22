package by.pvt.epam.command;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.resource.ConfigurationManager;

public class CompleteFlightCommand implements ActionCommand {

	private static final String PARAM_NAME_FLIGHT_ID = "flightId";

	@Override
	public String execute(HttpServletRequest request) {

		int flightId = Integer.valueOf(request
				.getParameter(PARAM_NAME_FLIGHT_ID));

		FlightDAO fd = new FlightDAOImpl();
		try {
			fd.setFlightCompleted(flightId);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		Set<Employee> crew = null;
		CrewDAO cd = new CrewDAOImpl();
		crew = cd.findCrewByFlightId();
		for (Employee employee : crew) {
			int employeeId = employee.getId();

			cd.releaseEmployee(employeeId);

		}

		String page = ConfigurationManager.getProperty("path.page.admin");
		return page;
	}
}
