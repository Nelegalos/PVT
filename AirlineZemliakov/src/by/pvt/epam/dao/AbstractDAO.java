package by.pvt.epam.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import by.pvt.epam.controller.Controller;

public abstract class AbstractDAO {

	private static Logger logger = Logger.getLogger(Controller.class);

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error("DAOException", e);
		}
	}

}
