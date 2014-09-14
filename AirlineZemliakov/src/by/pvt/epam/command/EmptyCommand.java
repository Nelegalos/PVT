package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		/*
		 * в случае ошибки или прямого обращения к контроллеру переадресация на
		 * страницу ввода логина
		 */
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}
}