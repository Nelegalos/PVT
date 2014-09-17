package by.pvt.epam.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.dao.FlightDAO;
import by.pvt.epam.dao.FlightDAOImpl;
import by.pvt.epam.dao.UserDAO;
import by.pvt.epam.dao.UserDAOImpl;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Role;
import by.pvt.epam.entity.User;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.resource.MessageManager;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.findUser(login, pass);
		FlightDAO flightDAO = new FlightDAOImpl();
		List<Flight> flights = flightDAO.findAllFlights();
		String page = null;
		if (user != null) {
			Role role = user.getRole();
			String welcomeUser = role + ": " + user.getName() + " "
					+ user.getSurname();
			request.getSession().setAttribute("role", role);
			request.setAttribute("user", welcomeUser);
			request.setAttribute("flights", flights);
			switch (role) {
			case ADMIN:
				page = ConfigurationManager.getProperty("path.page.admin");
				break;
			case DISPATCHER:
				page = ConfigurationManager.getProperty("path.page.dispatcher");
				break;
			}
		} else {
			request.setAttribute("errorLoginPassMessage",
					MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}
}