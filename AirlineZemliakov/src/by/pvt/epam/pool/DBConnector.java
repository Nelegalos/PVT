package by.pvt.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBConnector {

	private static final ResourceBundle configBundle = ResourceBundle
			.getBundle("resources.database");

	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Properties properties = new Properties();
		properties.setProperty("user", configBundle.getString("user"));
		properties.setProperty("password", configBundle.getString("pass"));
		properties.setProperty("useUnicode", configBundle.getString("unicode"));
		properties.setProperty("characterEncoding",
				configBundle.getString("encoding"));
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		connection = DriverManager.getConnection(configBundle.getString("url"),
				properties);
		return connection;
	}
}