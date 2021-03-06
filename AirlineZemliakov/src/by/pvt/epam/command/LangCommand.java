package by.pvt.epam.command;

import javax.servlet.http.HttpServletRequest;
import by.pvt.epam.resource.ConfigurationManager;
import static by.pvt.epam.constants.Constants.*;

public class LangCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String language = request.getParameter(PARAM_NAME_LANGUAGE);
		switch (language) {
		case "en":
			request.getSession().setAttribute("lang", "en");
			break;
		case "ru":
			request.getSession().setAttribute("lang", "ru");
			break;
		}
		return ConfigurationManager.getProperty("path.page.login");
	}

}
