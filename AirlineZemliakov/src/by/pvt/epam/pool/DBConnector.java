package by.pvt.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
	private static final ResourceBundle configBundle = ResourceBundle
			.getBundle("database");

	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(
					configBundle.getString("url"),
					configBundle.getString("user"),
					configBundle.getString("pass"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}