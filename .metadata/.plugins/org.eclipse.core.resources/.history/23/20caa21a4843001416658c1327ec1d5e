package by.pvt.epam.logic;

import by.pvt.epam.dao.UserDAO;
import by.pvt.epam.dao.UserDAOImpl;
import by.pvt.epam.entity.User;
import by.pvt.epam.exception.LogicException;

public class LoginLogic {

	public static User checkLogin(String login, String pass)
			throws LogicException {
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.findUser(login, pass);
		return user;
	}
}