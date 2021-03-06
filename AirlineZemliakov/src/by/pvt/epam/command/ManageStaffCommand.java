package by.pvt.epam.command;

import static by.pvt.epam.constants.Constants.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;

public class ManageStaffCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(ManageStaffCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			CrewService crewService = new CrewService();
			List<Employee> employees = crewService.findAvailableEmployees();
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEES, employees);
			page = ConfigurationManager.getProperty("path.page.staff");
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEES_NULL,
					"employees.null");
			page = ConfigurationManager.getProperty("path.page.admin");
		}
		return page;
	}

}
