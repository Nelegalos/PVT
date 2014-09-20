package by.pvt.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.pvt.epam.controller.Controller;
import by.pvt.epam.entity.Employee;
import by.pvt.epam.entity.Position;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.pool.ConnectionPool;

public class CrewDAOImpl extends CrewDAO {
	private static Logger logger = Logger.getLogger(Controller.class);
	private static final String SQL_QUERY_FIND_EMPLOYEE_BY_ID = "SELECT employee.id, employee.name, employee.surname, position.position FROM employee LEFT JOIN position on employee.position_id = position.id  WHERE employee.id=?";
	private static final String SQL_QUERY_FIND_AVAILABLE_EMPLOYEES = "SELECT employee.id, employee.name, employee.surname, position.position FROM employee LEFT JOIN position on employee.position_id = position.id WHERE employee.status=1";
	private static final String SQL_QUERY_ADD_EMPLOYEE = "INSERT INTO employee (name, surname, position_id) VALUES (?,?,?)";

	@Override
	public boolean addEmployee(String name, String surname, int position) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_ADD_EMPLOYEE);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setInt(3, position);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("TechnicalException addEmployee", e);
		} finally {
			CrewDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return flag;
	}

	@Override
	public Employee findEmployeeById(int id) throws TechnicalException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_FIND_EMPLOYEE_BY_ID);
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
		} catch (SQLException | ClassNotFoundException e) {
			throw new TechnicalException(e);
			//logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return employee;
	}

	@Override
	public List<Employee> findAvailableEmployees() throws TechnicalException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery(SQL_QUERY_FIND_AVAILABLE_EMPLOYEES);
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setPosition(Position.valueOf((resultSet.getString(4))
						.toUpperCase()));
				employees.add(employee);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new TechnicalException(e);
			//logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(statement);
			pool.backConnection(connection);
		}
		return employees;
	}

}
