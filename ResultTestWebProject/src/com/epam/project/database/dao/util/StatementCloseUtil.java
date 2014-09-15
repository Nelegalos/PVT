package com.epam.project.database.dao.util;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.project.controller.TestController;

public final class StatementCloseUtil {
	private static Logger logger = Logger.getLogger(TestController.class);

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error("TechnicalException.", e);
		}
	}
}
