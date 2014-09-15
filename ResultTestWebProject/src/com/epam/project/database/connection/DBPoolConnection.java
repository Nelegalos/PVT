package com.epam.project.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.epam.project.configuration.ConfigurationManager;
import com.epam.project.controller.TestController;

public class DBPoolConnection {
	private static Logger logger = Logger.getLogger(TestController.class);
	private static final String SQL_SETTINGS_PATH = "sqlSettings";
	
	private static BlockingQueue<Connection> pool;
	private final static int SIZE = 10;

	DBPoolConnection() {
		ResourceBundle rs = ResourceBundle.getBundle(ConfigurationManager
				.getProperty(SQL_SETTINGS_PATH));
		pool = new ArrayBlockingQueue<Connection>(SIZE, true);
		for (int i = 0; i < SIZE; i++) {
			try {
				Class.forName(rs.getString("SQL_DRIVER"));
				Properties properties = new Properties();
				properties.setProperty("user", rs.getString("SQL_ROOT_LOGIN"));
				properties.setProperty("password",
						rs.getString("SQL_ROOT_PASSWORD"));
				properties.setProperty("useUnicode", "true");
				properties.setProperty("characterEncoding", "UTF-8");
				Connection connection = DriverManager.getConnection(
						rs.getString("SQL_URL_DATABASE"), properties);
				pool.add(connection);
			} catch (SQLException | ClassNotFoundException e) {
				logger.error("TechnicalException", e);
			}
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = pool.take();
		} catch (InterruptedException e) {
			logger.error("TechnicalException", e);
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				pool.put(connection);
			} catch (InterruptedException e) {
				logger.error("TechnicalException", e);
			}
		}
	}

	public static void removePool() {
		for (int i = 0; i < pool.size(); i++) {
			try {
				Connection connection = pool.take();
				connection.close();
			} catch (SQLException | InterruptedException e) {
				logger.error("TechnicalException", e);
			}
		}
	}

}
