package by.pvt.epam.command.client;

import by.pvt.epam.command.ActionCommand;
import by.pvt.epam.command.LangCommand;
import by.pvt.epam.command.LoginCommand;
import by.pvt.epam.command.LogoutCommand;
import by.pvt.epam.command.TeamCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LANG {
		{
			this.command = new LangCommand();
		}
	},
	TEAM {
		{
			this.command = new TeamCommand();
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