package by.pvt.epam.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

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

	public static ConnectionPool getInstance() throws ClassNotFoundException,
			SQLException {
		if (instance == null) {
			Lock lock = new ReentrantLock();
			try {
				lock.lock();
				instance = new ConnectionPool();
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public Connection getConnection() {
		Connection conn = pool.poll();
		inUse.add(conn);
		return conn;
	}

	public void backConnection(Connection conn) {
		inUse.remove(conn);
		pool.offer(conn);
	}

	public void cleanUp() throws SQLException {
		Iterator<Connection> iterator = pool.iterator();
		while (iterator.hasNext()) {
			Connection c = (Connection) iterator.next();
			c.close();
			iterator.remove();
		}
		iterator = inUse.iterator();
		while (iterator.hasNext()) {
			Connection c = (Connection) iterator.next();
			c.close();
			iterator.remove();
		}
	}
}
