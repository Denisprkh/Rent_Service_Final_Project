package by.prokhorenko.dao;

import by.prokhorenko.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractDao <T>{
    protected static final ConnectionPool POOL = ConnectionPool.INSTANCE;
    private static final Logger LOG = LogManager.getLogger();
}
