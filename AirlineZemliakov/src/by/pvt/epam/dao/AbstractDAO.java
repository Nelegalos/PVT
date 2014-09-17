package by.pvt.epam.dao;

import static by.pvt.epam.test.Test.logger;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error("TechnicalException", e);
		}
	}

}
