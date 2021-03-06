package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;
import static by.pvt.epam.constants.Constants.*;

public class ChangeEmployeeCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(ChangeEmployeeCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			int employeeId = Integer.parseInt(request
					.getParameter(PARAM_NAME_EMPLOYEE_ID_TO_MODIFY));
			String name = request.getParameter(PARAM_NAME_MODIFIED_NAME);
			String surname = request.getParameter(PARAM_NAME_MODIFIED_SURNAME);
			int position = Integer.valueOf(request
					.getParameter(PARAM_NAME_MODIFIED_POSITION));
			isEmpty(name, surname);
			changeEmployee(employeeId, name, surname, position);
			CrewService crewService = new CrewService();
			List<Employee> employees = crewService.findAvailableEmployees();
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEES, employees);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_WAS_MODIFIED,
					"employee.modified");
			page = ConfigurationManager.getProperty("path.page.staff");
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(
					REQUEST_ATTRIBUTE_NAME_EMPLOYEE_WAS_NOT_MODIFIED,
					"employee.notmodified");
			page = ConfigurationManager.getProperty("path.page.employee");
		}
		return page;
	}

	/**
	 * Change employee.
	 * 
	 * @param employeeId
	 *            the employee id
	 * @param name
	 *            the name
	 * @param surname
	 *            the surname
	 * @param position
	 *            the position
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void changeEmployee(int employeeId, String name, String surname,
			int position) throws TechnicalException {
		CrewService crewService = new CrewService();
		boolean flag = crewService.modifyEmployee(employeeId, name, surname,
				position);
		if (!flag) {
			throw new TechnicalException();
		}
	}

	/**
	 * Checks if input is empty.
	 * 
	 * @param name
	 *            the name
	 * @param surname
	 *            the surname
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void isEmpty(String name, String surname) throws TechnicalException {
		if (name.isEmpty() || surname.isEmpty()) {
			throw new TechnicalException();
		}
	}
}
