package by.pvt.epam.command.client;

import by.pvt.epam.command.ActionCommand;
import by.pvt.epam.command.AddEmployeeCommand;
import by.pvt.epam.command.AddFlightCommand;
import by.pvt.epam.command.CompleteFlightCommand;
import by.pvt.epam.command.FormTeamCommand;
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
	FORMTEAM {
		{
			this.command = new FormTeamCommand();
		}
	},
	ADDEMPLOYEE {
		{
			this.command = new AddEmployeeCommand();
		}
	},
	COMPLETEFLIGHT {
		{
			this.command = new CompleteFlightCommand();
		}
	},
	ADDFLIGHT {
		{
			this.command = new AddFlightCommand();
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