package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.logic.LoginLogic;
import by.pvt.epam.resource.ConfigurationManager;
import by.pvt.epam.resource.MessageManager;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		if (LoginLogic.checkLogin(login, pass)) {
			request.setAttribute("user", login);
			page = ConfigurationManager.getProperty("path.page.main");
		} else {
			request.setAttribute("errorLoginPassMessage",
					MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}
}