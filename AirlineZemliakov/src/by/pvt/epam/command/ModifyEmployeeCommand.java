package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;
import static by.pvt.epam.constants.Constants.*;

public class ModifyEmployeeCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(ModifyEmployeeCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			int employeeId = Integer.valueOf(request
					.getParameter(PARAM_NAME_EMPLOYEE_ID));
			CrewService crewService = new CrewService();
			Employee employee = crewService.findEmployeeById(employeeId);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_TO_MODIFY,
					employee);
			page = ConfigurationManager.getProperty("path.page.employee");
		} catch (TechnicalException e) {
			request.setAttribute(
					REQUEST_ATTRIBUTE_NAME_EMPLOYEE_WAS_NOT_MODIFIED,
					"emloyee.notmodified");
			LOGGER.error("TechnicalException", e);
			page = ConfigurationManager.getProperty("path.page.staff");
		}
		return page;
	}

}
