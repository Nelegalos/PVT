package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;

public class ModifyEmployeeCommand implements ActionCommand {

	private static Logger logger = Logger
			.getLogger(ModifyEmployeeCommand.class);
	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeId";

	@Override
	public String execute(HttpServletRequest request) {

		int employeeId = Integer.valueOf(request
				.getParameter(PARAM_NAME_EMPLOYEE_ID));
		CrewDAO cdi = new CrewDAOImpl();
		try {
			Employee employee = cdi.findEmployeeById(employeeId);
			request.setAttribute("employeeToModify", employee);
			request.getSession().setAttribute("employeeIdToModify", employeeId);
		} catch (TechnicalException e) {
			request.setAttribute("employeeWasntModified", "emloyee.notmodified");
			logger.error("TechnicalException", e);
			return ConfigurationManager.getProperty("path.page.staff");
		}
		return ConfigurationManager.getProperty("path.page.employee");
	}

}
