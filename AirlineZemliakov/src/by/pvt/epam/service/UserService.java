package by.pvt.epam.service;

import by.pvt.epam.dao.UserDAO;
import by.pvt.epam.dao.UserDAOImpl;
import by.pvt.epam.entity.User;
import by.pvt.epam.exception.TechnicalException;

public class UserService {

	private UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAOImpl();
	}

	public User findUser(String login, String password) throws TechnicalException {
		return userDAO.findUser(login, password);
	}

}
