package by.pvt.epam.dao;

import static by.pvt.epam.test.Test.logger;
import java.sql.SQLException;
import java.sql.Statement;
import by.pvt.epam.entity.Employee;

public abstract class CrewDAO {

	public abstract boolean addEmployee(String name, String surname,
			int position);

	public abstract Employee getEmployeeById(int id);

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
