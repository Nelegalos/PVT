package by.pvt.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class DBConnector {
	private static Logger logger = Logger.getLogger(DBConnector.class);
	private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle
			.getBundle("resources.database");

	public Connection getConnection(Properties properties) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					CONFIG_BUNDLE.getString("url"), properties);
		} catch (SQLException e) {
			logger.fatal("Fatal Error", e);
			throw new RuntimeException(e);
		}
		return connection;
	}
}