package by.pvt.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.service.CrewService;

public class AddEmployeeCommand implements ActionCommand {

	private static final Logger LOGGER = Logger
			.getLogger(AddEmployeeCommand.class);
	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeId";
	private static final String SESSION_ATTRIBUTE_NAME_CREW = "crew";
	private static final String SESSION_ATTRIBUTE_NAME_EMPLOYEES = "employees";
	private static final String REQUEST_ATTRIBUTE_NAME_EMPLOYEE_ADDED = "employeeAdded";
	private static final String REQUEST_ATTRIBUTE_NAME_EMPLOYEE_NOT_ADDED = "employeeNotAdded";

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Employee> availableEmployees = (List<Employee>) request
					.getSession()
					.getAttribute(SESSION_ATTRIBUTE_NAME_EMPLOYEES);
			List<Employee> crew = (List<Employee>) request.getSession()
					.getAttribute(SESSION_ATTRIBUTE_NAME_CREW);
			addEmloyeeToCrew(availableEmployees, crew, request);
		} catch (TechnicalException e) {
			request.setAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_NOT_ADDED,
					"employee.notadded");
			request.removeAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_ADDED);
			LOGGER.error("TechnicalException", e);
		}
		return ConfigurationManager.getProperty("path.page.team");
	}

	private boolean isCorrectEmployee(Employee crewEmployee,
			Position addedEmployeePosition) {
		return (crewEmployee.getPosition().equals(addedEmployeePosition) && (crewEmployee
				.getId() == 0));
	}

	private void addEmloyeeToCrew(List<Employee> availableEmployees,
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
				request.removeAttribute(REQUEST_ATTRIBUTE_NAME_EMPLOYEE_ADDED);
			}
			i++;
		}
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_CREW, crew);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_EMPLOYEES,
				availableEmployees);
	}

}
