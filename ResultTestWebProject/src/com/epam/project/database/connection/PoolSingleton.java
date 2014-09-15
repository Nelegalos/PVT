package com.epam.project.database.connection;


public enum PoolSingleton {
	INSTANCE(new DBPoolConnection());

	private final DBPoolConnection pool;

	private PoolSingleton(DBPoolConnection pool) {
		this.pool = pool;
	}

	public DBPoolConnection getInstance() {
		return pool;
	}

}
