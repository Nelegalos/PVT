package by.pvt.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class DBConnector {
	private static final Logger LOGGER = Logger.getLogger(DBConnector.class);
	private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle
			.getBundle("resources.database");

	/**
	 * Gets the connection.
	 * 
	 * @param properties
	 *            the properties
	 * @return the connection
	 */
	public Connection getConnection(Properties properties) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					CONFIG_BUNDLE.getString("url"), properties);
		} catch (SQLException e) {
			LOGGER.fatal("Fatal Error", e);
			throw new RuntimeException(e);
		}
		return connection;
	}
}