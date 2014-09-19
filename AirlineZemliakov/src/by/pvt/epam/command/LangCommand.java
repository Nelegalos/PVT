package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;

import by.pvt.epam.resource.ConfigurationManager;

public class LangCommand implements ActionCommand {
	private static final String PARAM_NAME_LANGUAGE = "language";

	@Override
	public String execute(HttpServletRequest request) {		
		String page = ConfigurationManager.getProperty("path.page.login");
		String language = request.getParameter(PARAM_NAME_LANGUAGE);
		switch (language) {
		case "en":
			request.getSession().setAttribute("lang", "en");
			break;
		case "ru":
			request.getSession().setAttribute("lang", "ru");
			break;
		}
		return page;
	}

}
