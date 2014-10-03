package test.by.pvt.epam;

import java.sql.Connection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import by.pvt.epam.exception.TechnicalException;
import by.pvt.epam.pool.ConnectionPool;

@RunWith(ConPoolRunner.class)
public class ConPoolTest {

	@Test
	public void getConnectionTest() throws TechnicalException {
		ConnectionPool conPoolInstance = ConnectionPool.getInstance();
		Connection connection = conPoolInstance.getConnection();
		Assert.assertNotNull(connection);
	}

}