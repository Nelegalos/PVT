package by.pvt.epam.connection;

import java.sql.SQLException;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;




import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

public class ConnectionPool {

	private static Logger LOGGER = Logger.getLogger(ConnectionPool.class);
	/**
     *
     */
	public static final int DEFAULT_POOL_SIZE = 20;
	private static ConnectionPool instance;
	private Queue<Connection> pool;
	private Queue<Connection> inUse;

	private ConnectionPool() throws ClassNotFoundException, SQLException {
		init();
	}

	private void init() throws ClassNotFoundException, SQLException {
		pool = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		inUse = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		for (int i = 0; i <= DEFAULT_POOL_SIZE; i++) {
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			pool.offer(connection);
		}

	}

	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ConnectionPool getInstance() throws ClassNotFoundException,
			SQLException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	/**
	 * 
	 * @return
	 */
	public Connection getConnection() {

		Connection conn = pool.poll();
		inUse.add(conn);
		return conn;
	}

	/**
	 * 
	 * @param conn
	 */
	public void backConnection(Connection conn) {
		inUse.remove(conn);
		pool.offer(conn);

	}

	/**
	 * 
	 * @throws SQLException
	 */
	public void cleanUp() throws SQLException {
		Iterator<Connection> iterator = pool.iterator();
		while (iterator.hasNext()) {
			try {
				Connection c = (Connection) iterator.next();
				c.close();
				iterator.remove();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				throw new SQLException(e.getMessage());
			}
		}

		iterator = inUse.iterator();
		while (iterator.hasNext()) {
			try {
				Connection c = (Connection) iterator.next();
				c.close();
				iterator.remove();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				throw new SQLException(e.getMessage());
			}
		}
	}
}
