package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.resource.ConfigurationManager;

public class BackToAdminCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return ConfigurationManager.getProperty("path.page.admin");
	}

}
