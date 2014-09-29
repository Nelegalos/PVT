package test.by.pvt.epam;

import java.sql.Connection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import by.pvt.epam.exception.DAOException;
import by.pvt.epam.pool.ConnectionPool;

@RunWith(ConPoolRunner.class)
public class ConPoolTest {

	@Test
	public void getConnectionTest() throws DAOException {
		ConnectionPool conPoolInstance = ConnectionPool.getInstance();
		Connection connection = conPoolInstance.getConnection();
		Assert.assertNotNull(connection);
	}

}