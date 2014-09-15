package com.epam.project.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.project.controller.TestController;
import com.epam.project.database.connection.PoolSingleton;
import com.epam.project.database.dao.util.StatementCloseUtil;
import com.epam.project.entity.Register;
import com.epam.project.entity.Role;
import com.epam.project.entity.test.Test;
import com.epam.project.entity.users.Admin;
import com.epam.project.entity.users.Student;
import com.epam.project.entity.users.Tutor;
import com.epam.project.entity.users.User;
import com.epam.project.exceptions.LogicException;
import com.epam.project.exceptions.TechnicalException;

public class UserDAOImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String SQL_QUERY_UPDATE_PEOPLE = "UPDATE people SET first_name = ?, last_name = ?, other = ? WHERE id = ?";
	private static final String SQL_QUERY_GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
	private static final String SQL_QUERY_GET_USER = "SELECT id,first_name, last_name, role_id, other FROM people WHERE id = ( SELECT people_id FROM user WHERE (login = ?) AND (password = ?))";
	private static final String SQL_QUERY_GET_USER_BY_ID = "SELECT id,first_name, last_name, role_id, other FROM people WHERE id = ?";
	private static final String SQL_QUERY_ADD_USER = "INSERT INTO user (login, password, people_id) VALUES (?,?,?)";
	private static final String SQL_QUERY_ADD_REGISTER = "INSERT INTO register (mark, people_id, test_id) VALUES (?,?,?)";
	private static final String SQL_QUERY_ADD_PEOPLE = "INSERT INTO people (first_name, last_name, role_id, other) VALUES (?,?,?,?)";
	private static final String SQL_QUERY_DELETE_PEOPLE = "DELETE FROM people WHERE id = ?";
	private static final String SQL_QUERY_DELETE_USER = "DELETE FROM user WHERE people_id = ?";
	private static final String SQL_QUERY_DELETE_REGISTER = "DELETE FROM register WHERE id = ?";
	private static final String SQL_QUERY_JOIN_GET_REGISTER = "SELECT people.id, people.first_name, people.last_name, test.id, test.name, test.subject_area, register.mark, test.max_mark, register.id FROM register LEFT JOIN people ON register.people_id = people.id LEFT JOIN test ON register.test_id = test.id";
	private static final String SQL_QUERY_JOIN_GET_REGISTER_BY_PEOPLE_ID = "SELECT first_name, people.last_name, test.id, test.name, test.subject_area, register.mark, test.max_mark, register.id FROM register LEFT JOIN people ON register.people_id = people.id LEFT JOIN test ON register.test_id = test.id WHERE people.id = ?";
	private static final String SQL_QUERY_JOIN_GET_USER = "SELECT people.id, people.first_name, people.last_name FROM people LEFT JOIN user ON people.id = user.people_id WHERE people.role_id=?";

	@Override
	public boolean addUser(String firstName, String lastName, String login,
			String password, Role role, String other) {
		Connection connection = null;
		PreparedStatement statementPeople = null;
		PreparedStatement statementPeopleDelete = null;
		PreparedStatement statementUser = null;
		Statement statementPeopleId = null;
		int peopleId = -1;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementPeople = connection.prepareStatement(SQL_QUERY_ADD_PEOPLE);
			statementPeople.setString(1, firstName);
			statementPeople.setString(2, lastName);
			statementPeople.setInt(3, role.ordinal() + 1);
			if (isNullOtherAtTheTutor(role, other)) {
				statementPeople.setInt(4, 0);
			} else {
				statementPeople.setString(4, other);
			}
			statementPeople.executeUpdate();

			statementPeopleId = connection.createStatement();
			ResultSet rsPeopleId = statementPeopleId
					.executeQuery(SQL_QUERY_GET_LAST_INSERT_ID);
			rsPeopleId.next();
			peopleId = rsPeopleId.getInt(1);

			statementUser = connection.prepareStatement(SQL_QUERY_ADD_USER);
			statementUser.setString(1, login);
			statementUser.setString(2, password);
			statementUser.setInt(3, peopleId);
			statementUser.executeUpdate();

		} catch (SQLException e) {
			try {
				statementPeopleDelete = connection
						.prepareStatement(SQL_QUERY_DELETE_PEOPLE);
				statementPeopleDelete.setInt(1, peopleId);
				statementPeopleDelete.executeUpdate();
			} catch (SQLException e1) {
			}
			logger.error("TechnicalException", e);
			return false;
		} finally {
			StatementCloseUtil.close(statementPeople);
			StatementCloseUtil.close(statementPeopleDelete);
			StatementCloseUtil.close(statementUser);
			StatementCloseUtil.close(statementPeopleId);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

	private boolean isNullOtherAtTheTutor(Role role, String other) {
		return other == null && role == Role.TUTOR;
	}

	@Override
	public User getUser(String login, String password)
			throws TechnicalException, LogicException {
		Connection connection = null;
		PreparedStatement statementPeople = null;
		User user = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementPeople = connection.prepareStatement(SQL_QUERY_GET_USER);
			statementPeople.setString(1, login);
			statementPeople.setString(2, password);
			statementPeople.executeQuery();
			ResultSet rsPeopleId = statementPeople.executeQuery();
			rsPeopleId.next();
			user = initUser(user, rsPeopleId);

		} catch (SQLException e) {
			throw new TechnicalException(e);
		} catch (LogicException e) {
			throw new LogicException(e);
		} finally {
			StatementCloseUtil.close(statementPeople);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return user;
	}

	private User initUser(User user, ResultSet rsPeopleId)
			throws LogicException, SQLException {
		int peopleId = rsPeopleId.getInt(1);
		String firstName = rsPeopleId.getString(2);
		String lastName = rsPeopleId.getString(3);
		int role = rsPeopleId.getInt(4);
		String other = rsPeopleId.getString(5);
		switch (role) {
		case 1:
			user = new Admin(firstName, lastName, peopleId);
			((Admin) user).setPhone(other);
			break;
		case 2:
			user = new Tutor(firstName, lastName, peopleId);
			if (other.equals("")) {
				other = "0";
			}
			((Tutor) user).setExperience(Integer.parseInt(other));
			break;
		case 3:
			user = new Student(firstName, lastName, peopleId);
			((Student) user).setUniversity(other);
			break;
		default:
			throw new LogicException("Unknown User Index");
		}
		return user;
	}

	@Override
	public boolean setRegister(int testId, int peopleId, int mark) {
		Connection connection = null;
		PreparedStatement statementTest = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementTest = connection.prepareStatement(SQL_QUERY_ADD_REGISTER);
			statementTest.setInt(1, mark);
			statementTest.setInt(2, peopleId);
			statementTest.setInt(3, testId);
			statementTest.executeUpdate();

		} catch (SQLException e) {
			logger.error("TechnicalException", e);
			return false;
		} finally {
			StatementCloseUtil.close(statementTest);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

	@Override
	public List<Register> getRegister() throws TechnicalException {
		Connection connection = null;
		Statement statement = null;
		List<Register> register = new ArrayList<>();
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statement = connection.createStatement();
			ResultSet rsRegister = statement
					.executeQuery(SQL_QUERY_JOIN_GET_REGISTER);
			while (rsRegister.next()) {
				Student student = new Student(rsRegister.getString(2),
						rsRegister.getString(3), rsRegister.getInt(1));
				Test test = new Test(null, rsRegister.getString(5),
						rsRegister.getString(6), rsRegister.getInt(8));
				test.setId(rsRegister.getInt(4));
				Register reg = new Register();
				reg.setStudent(student);
				reg.setTest(test);
				reg.setMark(rsRegister.getInt(7));
				reg.setId(rsRegister.getInt(9));
				register.add(reg);
			}
			return register;
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			StatementCloseUtil.close(statement);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
	}

	@Override
	public List<Register> getRegister(int peopleId) throws TechnicalException {
		Connection connection = null;
		PreparedStatement statement = null;
		List<Register> register = new ArrayList<>();
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statement = connection
					.prepareStatement(SQL_QUERY_JOIN_GET_REGISTER_BY_PEOPLE_ID);
			statement.setInt(1, peopleId);
			statement.executeQuery();

			ResultSet rsRegister = statement.executeQuery();
			while (rsRegister.next()) {
				Test test = new Test(null, rsRegister.getString(4),
						rsRegister.getString(5), rsRegister.getInt(7));
				test.setId(rsRegister.getInt(3));
				Register reg = new Register();
				reg.setTest(test);
				reg.setMark(rsRegister.getInt(6));
				reg.setId(rsRegister.getInt(8));
				register.add(reg);
			}
			return register;
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			StatementCloseUtil.close(statement);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}

	}

	@Override
	public List<User> getUsers(Role role) throws TechnicalException {
		Connection connection = null;
		PreparedStatement statement = null;
		List<User> users = new ArrayList<>();
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statement = connection.prepareStatement(SQL_QUERY_JOIN_GET_USER);
			statement.setInt(1, role.ordinal() + 1);
			statement.executeQuery();
			ResultSet rsRegister = statement.executeQuery();
			User user = null;
			while (rsRegister.next()) {
				switch (role) {
				case STUDENT:
					user = new Student(rsRegister.getString(2),
							rsRegister.getString(3), rsRegister.getInt(1));
					break;
				case TUTOR:
					user = new Tutor(rsRegister.getString(2),
							rsRegister.getString(3), rsRegister.getInt(1));
					break;
				case ADMIN:
					user = new Admin(rsRegister.getString(2),
							rsRegister.getString(3), rsRegister.getInt(1));
					break;
				}
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			StatementCloseUtil.close(statement);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
	}

	@Override
	public User getUser(int userId) throws TechnicalException, LogicException {
		Connection connection = null;
		PreparedStatement statementPeople = null;
		User user = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementPeople = connection
					.prepareStatement(SQL_QUERY_GET_USER_BY_ID);
			statementPeople.setInt(1, userId);
			statementPeople.executeQuery();

			ResultSet rsPeopleId = statementPeople.executeQuery();
			rsPeopleId.next();
			user = initUser(user, rsPeopleId);

		} catch (SQLException e) {
			throw new TechnicalException(e);
		} catch (LogicException e) {
			throw new LogicException(e);
		} finally {
			StatementCloseUtil.close(statementPeople);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean modifyUser(String firstName, String lastName, String other,
			int userId) {
		Connection connection = null;
		PreparedStatement statementUpdatePeople = null;
		try {
			Role userRole = getUser(userId).getRole();
			if (userRole == Role.TUTOR) {
				Integer.parseInt(other);
			}
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementUpdatePeople = connection
					.prepareStatement(SQL_QUERY_UPDATE_PEOPLE);
			statementUpdatePeople.setString(1, firstName);
			statementUpdatePeople.setString(2, lastName);
			statementUpdatePeople.setString(3, other);
			statementUpdatePeople.setInt(4, userId);
			statementUpdatePeople.executeUpdate();
		} catch (SQLException | NumberFormatException e) {
			logger.error("TechnicalException", e);
			return false;
		} catch (TechnicalException | LogicException e) {
			logger.error(e);
			return false;
		} finally {
			StatementCloseUtil.close(statementUpdatePeople);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

	@Override
	public boolean deleteUser(int userId) {
		Connection connection = null;
		PreparedStatement statementUserDelete = null;
		PreparedStatement statementPeopleDelete = null;
		try {
			if (getRegister(userId).size() == 0) {
				connection = PoolSingleton.INSTANCE.getInstance()
						.getConnection();
				statementUserDelete = connection
						.prepareStatement(SQL_QUERY_DELETE_USER);
				statementUserDelete.setInt(1, userId);
				statementUserDelete.executeUpdate();

				statementPeopleDelete = connection
						.prepareStatement(SQL_QUERY_DELETE_PEOPLE);
				statementPeopleDelete.setInt(1, userId);
				statementPeopleDelete.executeUpdate();
			} else {
				throw new TechnicalException(
						"Can't be deleted of the user from the active registers");
			}
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
			return false;
		} catch (TechnicalException e) {
			logger.error(e);
			return false;
		} finally {
			StatementCloseUtil.close(statementPeopleDelete);
			StatementCloseUtil.close(statementUserDelete);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

	@Override
	public boolean deleteRegister(int registerId) {
		Connection connection = null;
		PreparedStatement statementRegisterDelete = null;
		try {
			connection = PoolSingleton.INSTANCE.getInstance().getConnection();
			statementRegisterDelete = connection
					.prepareStatement(SQL_QUERY_DELETE_REGISTER);
			statementRegisterDelete.setInt(1, registerId);
			statementRegisterDelete.executeUpdate();
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
			return false;
		} finally {
			StatementCloseUtil.close(statementRegisterDelete);
			PoolSingleton.INSTANCE.getInstance().closeConnection(connection);
		}
		return true;
	}

}
