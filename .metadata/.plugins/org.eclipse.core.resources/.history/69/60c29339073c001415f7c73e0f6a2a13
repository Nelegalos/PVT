package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		// уничтожение сессии
		request.getSession().invalidate();
		return page;
	}
}