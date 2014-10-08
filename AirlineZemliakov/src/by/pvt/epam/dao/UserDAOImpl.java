package by.pvt.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import by.pvt.epam.entity.Role;
import by.pvt.epam.entity.User;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.pool.ConnectionPool;

public class UserDAOImpl extends UserDAO {

	private static final String SQL_QUERY_FIND_USER = "SELECT user.login, user.name, user.surname, role.role FROM user LEFT JOIN role on user.role_id=role.id WHERE user.login = ( SELECT login FROM user WHERE login = ? AND pass = ?)";

	@Override
	public User findUser(String login, String password)
			throws TechnicalException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User user = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_FIND_USER);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			String log = rs.getString(1);
			String name = rs.getString(2);
			String surname = rs.getString(3);
			String role = rs.getString(4);
			Role userRole = Role.valueOf(role.toUpperCase());
			user = new User(name, surname, userRole, log);
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			AbstractDAO.close(preparedStatement);
			pool.close(connection);
		}
		return user;
	}
}
