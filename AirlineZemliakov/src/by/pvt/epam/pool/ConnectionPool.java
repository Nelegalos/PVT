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
import by.pvt.epam.exception.TechnicalException;

public class ConnectionPool {

	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
	private static final int DEFAULT_POOL_SIZE = 10000;
	private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle
			.getBundle("resources.database");
	private static final Lock LOCK = new ReentrantLock();
	private static boolean giveConnection = true;
	private static ConnectionPool instance;
	private ArrayBlockingQueue<Connection> pool;
	private ArrayBlockingQueue<Connection> inUse;

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() {
		init();
	}

	/**
	 * Initiates.
	 */
	private void init() {
		pool = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		inUse = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			LOGGER.fatal("Fatal Error", e);
			throw new RuntimeException(e);
		}
		DBConnector dbConnector = new DBConnector();
		Properties properties = new Properties();
		properties.setProperty("user", CONFIG_BUNDLE.getString("user"));
		properties.setProperty("password", CONFIG_BUNDLE.getString("pass"));
		properties
				.setProperty("useUnicode", CONFIG_BUNDLE.getString("unicode"));
		properties.setProperty("characterEncoding",
				CONFIG_BUNDLE.getString("encoding"));
		for (int i = 0; i <= DEFAULT_POOL_SIZE; i++) {
			Connection connection = dbConnector.getConnection(properties);
			pool.offer(connection);
		}
	}

	/**
	 * Gets the single instance of ConnectionPool.
	 * 
	 * @return single instance of ConnectionPool
	 */
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

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 * @throws TechnicalException
	 *             the technical exception
	 */
	public Connection getConnection() throws TechnicalException {
		Connection conn = null;
		if (giveConnection) {
			try {
				conn = pool.take();
				inUse.add(conn);
			} catch (InterruptedException e) {
				throw new TechnicalException(e);
			}
		}
		return conn;
	}

	/**
	 * Closes connection.
	 * 
	 * @param conn
	 *            the connection
	 */
	public void close(Connection conn) {
		inUse.remove(conn);
		pool.offer(conn);
	}

	/**
	 * Clean up.
	 */
	public void cleanUp() {
		giveConnection = false;
		Iterator<Connection> iterator = pool.iterator();
		while (iterator.hasNext()) {
			Connection c = (Connection) iterator.next();
			try {
				c.close();
			} catch (SQLException e) {
				LOGGER.error("TechnicalException", e);
			}
			iterator.remove();
		}
		iterator = inUse.iterator();
		while (iterator.hasNext()) {
			Connection c = (Connection) iterator.next();
			try {
				c.close();
			} catch (SQLException e) {
				LOGGER.error("TechnicalException", e);
			}
			iterator.remove();
		}
	}
}
