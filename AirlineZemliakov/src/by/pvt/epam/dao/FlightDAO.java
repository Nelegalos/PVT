package by.pvt.epam.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static by.pvt.epam.test.Test.logger;

import by.pvt.epam.entity.Flight;

public abstract class FlightDAO {
	public abstract List<Flight> getAllFlights();

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
	}

}