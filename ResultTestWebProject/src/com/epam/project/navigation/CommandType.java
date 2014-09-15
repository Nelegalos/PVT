package com.epam.project.navigation;

import com.epam.project.navigation.command.Command;
import com.epam.project.navigation.command.general.BackCommand;
import com.epam.project.navigation.command.general.ErrorCommand;
import com.epam.project.navigation.command.general.LanguageCommand;
import com.epam.project.navigation.command.general.LogOutCommand;
import com.epam.project.navigation.command.general.functional.AdminFunctionalCommand;
import com.epam.project.navigation.command.general.functional.StudentFunctionalCommand;
import com.epam.project.navigation.command.general.functional.TutorFunctionalCommand;
import com.epam.project.navigation.command.general.login.LogInCommand;
import com.epam.project.navigation.command.general.login.SelectUserCommand;
import com.epam.project.navigation.command.general.login.SignUpCommand;
import com.epam.project.navigation.command.general.student.AllResultsCommand;
import com.epam.project.navigation.command.general.student.DeleteUserCommand;
import com.epam.project.navigation.command.general.student.ListModifyUserCommand;
import com.epam.project.navigation.command.general.student.ModifyUserCommand;
import com.epam.project.navigation.command.general.student.StudentCommand;
import com.epam.project.navigation.command.general.test.AddQuestionCommand;
import com.epam.project.navigation.command.general.test.AddTestCommand;
import com.epam.project.navigation.command.general.test.CreateQuestionCommand;
import com.epam.project.navigation.command.general.test.CreateTestCommand;
import com.epam.project.navigation.command.general.test.DeleteTestCommand;
import com.epam.project.navigation.command.general.test.ListModifyTestCommand;
import com.epam.project.navigation.command.general.test.ModifyTestCommand;
import com.epam.project.navigation.command.general.test.PassTestCommand;
import com.epam.project.navigation.command.general.test.TestCommand;

public enum CommandType {
	LOGIN(new LogInCommand()),
	SIGNUP(new SignUpCommand()),
	ADMINFUNCTIONAL(new AdminFunctionalCommand()),
	TUTORFUNCTIONAL( new TutorFunctionalCommand()),
	STUDENTFUNCTIONAL(new StudentFunctionalCommand()),
	ADDUSER(new SignUpCommand()),
	ADDTEST(new AddTestCommand()),
	GETALLSTUDENTS(new StudentCommand()),
	ALLTESTS(new TestCommand()),
	PASSTEST(new PassTestCommand()),
	ADDQUESTION(new AddQuestionCommand()),
	SELECTROLE(new SelectUserCommand()),
	BACK(new BackCommand()),
	LOGOUT(new LogOutCommand()),
	LANG(new LanguageCommand()),
	LISTMODIFYUSER( new ListModifyUserCommand()),
	MODIFYUSER(new ModifyUserCommand()),
	ALLRESULTS(new AllResultsCommand()),
	LISTMODIFYTEST(new ListModifyTestCommand()),
	MODIFYTEST(new ModifyTestCommand()),
	DELETEUSER(new DeleteUserCommand()),
	DELETETEST(new DeleteTestCommand()),
	CREATEQUESTION(new CreateQuestionCommand()),
	CREATETEST(new CreateTestCommand()),
	ERROR(new ErrorCommand());
	
	private final Command command;	
	
	private CommandType (Command command) { 
		this.command = command; 
	}
	
	public Command getCommand() { return command; }

}
