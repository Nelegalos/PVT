package by.pvt.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import by.pvt.epam.controller.Controller;

public class DBConnector {
	private static Logger logger = Logger.getLogger(Controller.class);
	private static final ResourceBundle configBundle = ResourceBundle
			.getBundle("resources.database");

	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Technical Exception", e);
		}

		try {
			connection = DriverManager.getConnection(
					configBundle.getString("url"),
					configBundle.getString("user"),
					configBundle.getString("pass"));
		} catch (SQLException e) {
			logger.error("Technical Exception", e);
		}
		return connection;
	}
}