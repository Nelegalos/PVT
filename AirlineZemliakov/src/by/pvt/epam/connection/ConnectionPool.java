package by.pvt.epam.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import static by.pvt.epam.test.Test.logger;

public class ConnectionPool {
	private static final String DATASOURCE_NAME = "jdbc/testphones";
	private static DataSource dataSource;

	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private ConnectionPool() {
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}

	public void backConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
	}

}
