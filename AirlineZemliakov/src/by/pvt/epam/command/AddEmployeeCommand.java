package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.dao.CrewDAO;
import by.pvt.epam.dao.CrewDAOImpl;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.resource.MessageManager;

public class AddEmployeeCommand implements ActionCommand {

	private static final String PARAM_NAME_EMPLOYEE_ID = "employeeId";

	@Override
	public String execute(HttpServletRequest request) {
		int employeeId = Integer.valueOf(request
				.getParameter(PARAM_NAME_EMPLOYEE_ID));
		CrewDAO cd = new CrewDAOImpl();
		Employee employee = null;
		try {
			employee = cd.findEmployeeById(employeeId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		Position pos = employee.getPosition();

		@SuppressWarnings("unchecked")
		List<Employee> employees = (List<Employee>) request.getSession()
				.getAttribute("employees");

		@SuppressWarnings("unchecked")
		List<Employee> crew = (List<Employee>) request.getSession()
				.getAttribute("crew");
		for (int i = 0; i < crew.size(); i++) {
			Employee empl = crew.get(i);
			if (empl.getPosition().equals(pos) && (empl.getId() == 0)) {
				crew.set(i, employee);
				employees.remove(employee);
				request.setAttribute("wrongEmployee",
						MessageManager.getProperty("team.added"));
				break;
			} else {
				request.setAttribute("wrongEmployee",
						MessageManager.getProperty("team.message"));
			}
		}

		request.getSession().setAttribute("crew", crew);
		request.getSession().setAttribute("employees", employees);

		String page = ConfigurationManager.getProperty("path.page.team");
		return page;
	}
}
