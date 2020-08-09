package by.prokhorenko.rentservice.pool;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class ConnectionPoolTest {

    @Test
    public void connectionPoolTest() {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Assert.assertNotNull(connection);
        ConnectionPool.INSTANCE.releaseConnection(connection);
    }


    @AfterClass
    public static void deInit() {
        ConnectionPool.INSTANCE.destroyPool();
    }

}
