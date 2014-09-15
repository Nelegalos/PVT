package by.pvt.epam.dao;

import static by.pvt.epam.test.Test.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Position;
import by.pvt.epam.pool.ConnectionPool;

public class CrewDAOimpl extends CrewDAO {

	private static final String SQL_QUERY_GET_EMPLOYEE_BY_ID = "SELECT employee.id, employee.name, employee.surname, position.position FROM employee LEFT JOIN position on employee.position_id = position.id  WHERE employee.id=?";
	private static final String SQL_QUERY_ADD_EMPLOYEE = "INSERT INTO employee (name, surname, position_id) VALUES (?,?,?)";

	@Override
	public boolean addEmployee(String name, String surname, int position) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_ADD_EMPLOYEE);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setInt(3, position);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
			return false;
		} catch (ClassNotFoundException e1) {
			logger.error("TechnicalException", e1);
			return false;
		} finally {
			pool.backConnection(connection);
			CrewDAO.close(preparedStatement);
		}
		return true;
	}

	@Override
	public Employee getEmployeeById(int id) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_GET_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			employee = new Employee();
			while (resultSet.next()) {
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setPosition(Position.valueOf((resultSet.getString(4))
						.toUpperCase()));
			}
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
		} catch (ClassNotFoundException e1) {
			logger.error("TechnicalException", e1);
		} finally {
			pool.backConnection(connection);
			FlightDAO.close(preparedStatement);
		}
		return employee;
	}

}
