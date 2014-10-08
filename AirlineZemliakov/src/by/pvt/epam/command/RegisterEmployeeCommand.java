package by.pvt.epam.command;

import static by.pvt.epam.constants.Constants.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;

public class RegisterEmployeeCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(RegisterEmployeeCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		try {
			String name = request.getParameter(PARAM_NAME_EMPLOYEE_NAME);
			String surname = request.getParameter(PARAM_NAME_EMPLOYEE_SURNAME);
			int position = Integer.parseInt(request
					.getParameter(PARAM_NAME_EMPLOYEE_POSITION));
			isEmpty(name, surname);
			registerEmployee(name, surname, position);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_ADDED,
					"employee.registered");
			CrewService crewService = new CrewService();
			List<Employee> employees = crewService.findAvailableEmployees();
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEES, employees);
		} catch (TechnicalException e) {
			LOGGER.error("TechnicalException", e);
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_NOT_ADDED,
					"employee.notregistered");
		}
		return ConfigurationManager.getProperty("path.page.staff");
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

	/**
	 * Registers employee.
	 * 
	 * @param name
	 *            the name
	 * @param surname
	 *            the surname
	 * @param position
	 *            the position
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void registerEmployee(String name, String surname, int position)
			throws TechnicalException {
		CrewService crewService = new CrewService();
		boolean flag = crewService.addEmployee(name, surname, position);
		if (!flag) {
			throw new TechnicalException();
		}
	}
}
