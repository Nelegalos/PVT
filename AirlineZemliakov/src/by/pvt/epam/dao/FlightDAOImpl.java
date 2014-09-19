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
import by.pvt.epam.pool.ConnectionPool;

public class FlightDAOImpl extends FlightDAO {
	private static Logger logger = Logger.getLogger(Controller.class);
	private static final String SQL_QUERY_FIND_ALL_FLIGHTS = "SELECT * FROM flight";
	private static final String SQL_QUERY_FIND_PLANE_BY_ID = "SELECT * FROM plane_staff WHERE plane=? ";
	private static final String SQL_QUERY_FIND_FLIGHT_BY_ID = "SELECT * FROM flight WHERE id=? ";

	@Override
	public List<Flight> findAllFlights() {
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
				int id = resultSet.getInt(5);
				Plane plane = findPlaneById(id);
				flight.setPlane(plane);
				flights.add(flight);
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(statement);
			pool.backConnection(connection);
		}
		return flights;
	}

	@Override
	public Flight findFlightById(int id) {
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
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return flight;
	}

	@Override
	public Plane findPlaneById(int id) {
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
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("TechnicalException", e);
		} finally {
			FlightDAO.close(preparedStatement);
			pool.backConnection(connection);
		}
		return plane;
	}
}
