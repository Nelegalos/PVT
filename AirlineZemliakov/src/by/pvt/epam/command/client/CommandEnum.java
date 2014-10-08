package by.pvt.epam.command.client;

import by.pvt.epam.command.*;

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
	TEAMADDEMPLOYEE {
		{
			this.command = new TeamAddEmployeeCommand();
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
	NEXTFLIGHT {
		{
			this.command = new NextFlightCommand();
		}
	},
	PREVIOUSFLIGHT {
		{
			this.command = new PreviousFlightCommand();
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