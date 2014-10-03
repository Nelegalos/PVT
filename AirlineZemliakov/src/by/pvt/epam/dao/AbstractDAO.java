package by.pvt.epam.dao;

import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public abstract class AbstractDAO {

	private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			LOGGER.error("DAOException", e);
		}
	}

}
