package by.pvt.epam.dao;

import by.pvt.epam.entity.User;
import by.pvt.epam.exception.TechnicalException;

public abstract class UserDAO extends AbstractDAO {

	public abstract User findUser(String login, String password)
			throws TechnicalException;

}
