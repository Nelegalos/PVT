package by.pvt.epam.dao;

import static by.pvt.epam.test.Test.logger;

import java.sql.SQLException;
import java.sql.Statement;

import by.pvt.epam.entity.User;

public abstract class UserDAO {
	public abstract User getUser(String login, String password);

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
	}

}
