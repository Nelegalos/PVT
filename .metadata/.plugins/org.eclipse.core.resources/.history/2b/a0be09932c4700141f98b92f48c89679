package by.pvt.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.pvt.epam.exception.DAOException;

public class ConnectionPool {
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
	public static final int DEFAULT_POOL_SIZE = 20;
	private static ConnectionPool instance;
	private ArrayBlockingQueue<Connection> pool;
	private ArrayBlockingQueue<Connection> inUse;
	private static final ResourceBundle CONFIGBUNDLE = ResourceBundle
			.getBundle("resources.database");
	private static final Lock LOCK = new ReentrantLock();

	private ConnectionPool() {
		init();
	}

	private void init() {
		pool = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		inUse = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			logger.fatal("Fatal Error", e);
			throw new RuntimeException(e);
		}
		DBConnector dbConnector = new DBConnector();
		Properties properties = new Properties();
		properties.setProperty("user", CONFIGBUNDLE.getString("user"));
		properties.setProperty("password", CONFIGBUNDLE.getString("pass"));
		properties.setProperty("useUnicode", CONFIGBUNDLE.getString("unicode"));
		properties.setProperty("characterEncoding",
				CONFIGBUNDLE.getString("encoding"));
		for (int i = 0; i <= DEFAULT_POOL_SIZE; i++) {
			Connection connection = dbConnector.getConnection(properties);
			pool.offer(connection);
		}
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			LOCK.lock();
			if (instance == null) {
				instance = new ConnectionPool();
			}
			LOCK.unlock();
		}
		return instance;
	}

	public Connection getConnection() throws DAOException {
		Connection conn = null;
		try {
			conn = pool.take();
			inUse.add(conn);
		} catch (InterruptedException e) {
			throw new DAOException(e);
		}
		return conn;
	}

	public void backConnection(Connection conn) {
		inUse.remove(conn);
		pool.offer(conn);
	}

	public void cleanUp() {
		Iterator<Connection> iterator = pool.iterator();
		while (iterator.hasNext()) {
			Connection c = (Connection) iterator.next();
			try {
				c.close();
			} catch (SQLException e) {
				logger.error("TechnicalException", e);
			}
			iterator.remove();
		}
		iterator = inUse.iterator();
		while (iterator.hasNext()) {
			Connection c = (Connection) iterator.next();
			try {
				c.close();
			} catch (SQLException e) {
				logger.error("TechnicalException", e);
			}
			iterator.remove();
		}
	}
}
