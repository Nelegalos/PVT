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
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.pool.ConnectionPool;

public class FlightDAOImpl extends FlightDAO {
	private static Logger logger = Logger.getLogger(Controller.class);
	private static final String SQL_QUERY_FIND_ALL_FLIGHTS = "SELECT * FROM flight";
	private static final String SQL_QUERY_FIND_PLANE_BY_ID = "SELECT * FROM plane_staff WHERE plane=? ";
	private static final String SQL_QUERY_FIND_FLIGHT_BY_ID = "SELECT * FROM flight WHERE id=? ";
	private static final String SQL_QUERY_CHANGE_STATUS_TO_ON_AIR = "UPDATE flight SET status = 1 WHERE id = ?";
	private static final String SQL_QUERY_CHANGE_STATUS_TO_COMPLETED = "UPDATE flight SET status = 2 WHERE id = ?";
	private static final String SQL_QUERY_FIND_UNFORMED_FLIGHTS = "SELECT * FROM flight WHERE status=0";
	private static final String SQL_QUERY_ADD_FLIGHT = "INSERT INTO flight VALUES (?,?,?,?,?,0)";
	private static final String SQL_QUERY_FIND_ALL_PLANES = "SELECT * FROM plane_staff";

	@Override
	public List<Flight> findAllFlights() throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Flight> flights = new ArrayList<Flight>();
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_QUERY_FIND_ALL_FLIGHTS);
			while (resultSet.next()) {
				Flight flight = new Flight();
				flight.setId(resultSet.getInt(1));
				flight.setDate(resultSet.getDate(2));
				flight.setFrom(resultSet.getString(3));
				flight.setTo(resultSet.getString(4));
				flight.setStatus(resultSet.getInt(6));
				int id = resultSet.getInt(5);
				Plane plane = findPlaneById(id);
				flight.setPlane(plane);
				flights.add(flight);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			FlightDAO.close(statement);
			pool.backConnection(connection);
		}
		return flights;
	}

	@Override
	public Flight findFlightById(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Flight flight = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_FIND_FLIGHT_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			flight = new Flight();
			while (resultSet.next()) {
				flight.setId(resultSet.getInt(1));
				flight.setDate(resultSet.getDate(2));
				flight.setFrom(resultSet.getString(3));
				flight.setTo(resultSet.getString(4));
				int idPlane = resultSet.getInt(5);
				Plane plane = findPlaneById(idPlane);
				flight.setPlane(plane);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return flight;
	}

	@Override
	public Plane findPlaneById(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Plane plane = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_FIND_PLANE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			plane = new Plane();
			while (resultSet.next()) {
				plane.setId(resultSet.getInt(1));
				plane.setPilot(resultSet.getInt(2));
				plane.setNavigator(resultSet.getInt(3));
				plane.setRadioman(resultSet.getInt(4));
				plane.setSteward(resultSet.getInt(5));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return plane;
	}

	@Override
	public boolean setFlightOnAir(int id) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_CHANGE_STATUS_TO_ON_AIR);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return flag;
	}

	@Override
	public List<Flight> findUnformedFlights() throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Flight> flights = new ArrayList<Flight>();
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_QUERY_FIND_UNFORMED_FLIGHTS);
			while (resultSet.next()) {
				Flight flight = new Flight();
				flight.setId(resultSet.getInt(1));
				flight.setDate(resultSet.getDate(2));
				flight.setFrom(resultSet.getString(3));
				flight.setTo(resultSet.getString(4));
				flight.setStatus(resultSet.getInt(6));
				int id = resultSet.getInt(5);
				Plane plane = findPlaneById(id);
				flight.setPlane(plane);
				flights.add(flight);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			FlightDAO.close(statement);
			pool.backConnection(connection);
		}
		return flights;
	}

	@Override
	public boolean setFlightCompleted(int id) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_CHANGE_STATUS_TO_COMPLETED);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return flag;
	}

	@Override
	public boolean addFlight(int flightId, String to, String from, String date,
			int plane) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_ADD_FLIGHT);
			preparedStatement.setInt(1, flightId);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, from);
			preparedStatement.setString(4, to);
			preparedStatement.setInt(5, plane);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return flag;
	}

	@Override
	public List<Plane> findAllPlanes() throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Plane> planes = new ArrayList<Plane>();
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_QUERY_FIND_ALL_PLANES);
			while (resultSet.next()) {
				Plane plane = new Plane();
				plane.setId(resultSet.getInt(1));
				plane.setPilot(resultSet.getInt(2));
				plane.setNavigator(resultSet.getInt(3));
				plane.setRadioman(resultSet.getInt(4));
				plane.setSteward(resultSet.getInt(5));
				planes.add(plane);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			FlightDAO.close(statement);
			pool.backConnection(connection);
		}
		return planes;
	}
}
