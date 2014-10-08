package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().invalidate();
		return ConfigurationManager.getProperty("path.page.index");
	}
}