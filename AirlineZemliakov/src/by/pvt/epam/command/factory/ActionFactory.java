package by.pvt.epam.command.factory;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import by.pvt.epam.command.ActionCommand;
import by.pvt.epam.command.EmptyCommand;
import by.pvt.epam.command.client.CommandEnum;

/**
 * A factory for creating Action objects.
 */
public class ActionFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

	/**
	 * Define command.
	 * 
	 * @param request
	 *            the request
	 * @return the action command
	 */
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			LOGGER.error("Technical Exception", e);
			request.setAttribute("wrongAction", "message.wrongaction");
		}
		return current;
	}
}