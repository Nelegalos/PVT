package by.pvt.epam.command.client;

import by.pvt.epam.command.ActionCommand;
import by.pvt.epam.command.LoginCommand;
import by.pvt.epam.command.LogoutCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}