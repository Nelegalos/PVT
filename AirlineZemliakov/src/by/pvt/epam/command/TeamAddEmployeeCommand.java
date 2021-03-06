package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;
import static by.pvt.epam.constants.Constants.*;

public class TeamAddEmployeeCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(TeamAddEmployeeCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request) {
		try {
			CrewService crewService = new CrewService();
			List<Employee> availableEmployees = crewService
					.findAvailableEmployees();
			List<Employee> crew = (List<Employee>) request.getSession()
					.getAttribute(SESSION_ATTRIBUTE_NAME_CREW);
			addEmployeeToCrew(availableEmployees, crew, request);
		} catch (TechnicalException e) {
			System.out.println("1");
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_NOT_ADDED,
					"employee.notadded");
			LOGGER.error("TechnicalException", e);
		}
		return ConfigurationManager.getProperty("path.page.team");
	}

	/**
	 * Adds the employee to crew.
	 * 
	 * @param availableEmployees
	 *            the available employees
	 * @param crew
	 *            the crew
	 * @param request
	 *            the request
	 * @throws TechnicalException
	 *             the technical exception
	 */
	private void addEmployeeToCrew(List<Employee> availableEmployees,
			List<Employee> crew, HttpServletRequest request)
			throws TechnicalException {
		int addedEmployeeId = Integer.valueOf(request
				.getParameter(PARAM_NAME_EMPLOYEE_ID));
		CrewService cs = new CrewService();
		Employee addedEmployee = cs.findEmployeeById(addedEmployeeId);
		Position addedEmployeePosition = addedEmployee.getPosition();

		int i = 0;
		while (i < crew.size()) {
			Employee crewEmployee = crew.get(i);
			if (isCorrectEmployee(crewEmployee, addedEmployeePosition)) {
				crew.set(i, addedEmployee);
				availableEmployees.remove(addedEmployee);
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_ADDED,
						"employee.added");
				request.removeAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_NOT_ADDED);
				break;
			} else {
				request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_NOT_ADDED,
						"employee.notadded");
			}
			i++;
		}

		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_CREW, crew);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_EMPLOYEES,
				availableEmployees);
	}

	/**
	 * Checks if employee is correct .
	 * 
	 * @param crewEmployee
	 *            the crew employee
	 * @param addedEmployeePosition
	 *            the added employee position
	 * @return true, if is correct employee
	 */
	private boolean isCorrectEmployee(Employee crewEmployee,
			Position addedEmployeePosition) {
		return (crewEmployee.getPosition().equals(addedEmployeePosition) && (crewEmployee
				.getId() == 0));
	}

}
