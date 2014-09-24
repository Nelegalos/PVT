package by.pvt.epam.command.client;

import by.pvt.epam.command.ActionCommand;
import by.pvt.epam.command.AddEmployeeCommand;
import by.pvt.epam.command.AddFlightCommand;
import by.pvt.epam.command.BackToAdminCommand;
import by.pvt.epam.command.BackToDispatcherCommand;
import by.pvt.epam.command.BackToStaffCommand;
import by.pvt.epam.command.ChangeEmployeeCommand;
import by.pvt.epam.command.CompleteFlightCommand;
import by.pvt.epam.command.DeleteFlightCommand;
import by.pvt.epam.command.FormTeamCommand;
import by.pvt.epam.command.LangCommand;
import by.pvt.epam.command.LoginCommand;
import by.pvt.epam.command.LogoutCommand;
import by.pvt.epam.command.ManageStaffCommand;
import by.pvt.epam.command.ModifyEmployeeCommand;
import by.pvt.epam.command.RegisterEmployeeCommand;
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
	MODIFYEMPLOYEE {
		{
			this.command = new ModifyEmployeeCommand();
		}
	},
	CHANGEEMPLOYEE {
		{
			this.command = new ChangeEmployeeCommand();
		}
	},
	COMPLETEFLIGHT {
		{
			this.command = new CompleteFlightCommand();
		}
	},
	DELETEFLIGHT {
		{
			this.command = new DeleteFlightCommand();
		}
	},
	ADDFLIGHT {
		{
			this.command = new AddFlightCommand();
		}
	},
	BACKTODISPATCHER {
		{
			this.command = new BackToDispatcherCommand();
		}
	},
	BACKTOADMIN {
		{
			this.command = new BackToAdminCommand();
		}
	},
	BACKTOSTAFF {
		{
			this.command = new BackToStaffCommand();
		}
	},
	MANAGESTAFF {
		{
			this.command = new ManageStaffCommand();
		}
	},
	REGISTEREMPLOYEE {
		{
			this.command = new RegisterEmployeeCommand();
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