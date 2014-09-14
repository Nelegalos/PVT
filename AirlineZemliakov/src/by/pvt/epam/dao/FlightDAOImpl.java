package by.pvt.epam.dao;

import by.pvt.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.pvt.epam.entity.Flight;
import by.pvt.epam.entity.Plane;
import static by.pvt.epam.test.Test.logger;

public class FlightDAOImpl extends FlightDAO {

	private static final String SQL_QUERY_GET_ALL_FLIGHTS = "SELECT * FROM flight";
	private static final String SQL_QUERY_GET_PLANE_BY_ID = "SELECT * FROM plane_staff WHERE plane=? ";

	public List<Flight> getAllFlights() {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Flight> flights = new ArrayList<Flight>();
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_QUERY_GET_ALL_FLIGHTS);
			while (resultSet.next()) {
				Flight flight = new Flight();
				flight.setId(resultSet.getInt(1));
				flight.setDate(resultSet.getDate(2));
				flight.setFrom(resultSet.getString(3));
				flight.setTo(resultSet.getString(4));
				int id = resultSet.getInt(5);
				Plane plane = getPlaneById(id);
				flight.setPlane(plane);
				flights.add(flight);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		} catch (ClassNotFoundException e1) {
			logger.error("ClassNotFoundException", e1);
		} finally {
			pool.backConnection(connection);
			FlightDAO.close(statement);
		}
		return flights;
	}

	public Plane getPlaneById(int id) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Plane plane = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_QUERY_GET_PLANE_BY_ID);
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
			logger.error("SQLException", e);
		} catch (ClassNotFoundException e1) {
			logger.error("ClassNotFoundException", e1);
		} finally {
			pool.backConnection(connection);
			FlightDAO.close(preparedStatement);
		}
		return plane;
	}
}
