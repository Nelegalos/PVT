package by.pvt.epam.command;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;

public class ManageStaffCommand implements ActionCommand {

	private static Logger logger = Logger.getLogger(ManageStaffCommand.class);

	@Override
	public String execute(HttpServletRequest request) {

		CrewDAO cdi = new CrewDAOImpl();
		List<Employee> employees = null;
		try {
			employees = cdi.findAvailableEmployees();
			List<Position> positions = Arrays.asList(Position.values());
			request.getSession().setAttribute("positions", positions);
			request.getSession().setAttribute("employees", employees);
			request.removeAttribute("employeesNull");
			request.getSession().setAttribute("employees", employees);
			request.getSession().setAttribute("pilot", "PILOT");
			request.getSession().setAttribute("navigator", "NAVIGATOR");
			request.getSession().setAttribute("radioman", "RADIOMAN");
			request.getSession().setAttribute("steward", "STEWARD");
		} catch (TechnicalException e) {
			logger.error("TechnicalException", e);
			request.setAttribute("employeesNull", "employees.null");
			return ConfigurationManager.getProperty("path.page.admin");
		}

		return ConfigurationManager.getProperty("path.page.staff");
	}

}
