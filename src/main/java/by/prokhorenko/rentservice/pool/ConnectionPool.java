package by.prokhorenko.rentservice.pool;

import by.prokhorenko.rentservice.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private static final String DB_PROPERTIES = "database.properties";
    private static final String DB_URL = "url";
    private static final String DB_DRIVER = "driver";
    private static final int DEFAULT_POOL_SIZE = 32;
    private BlockingQueue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> busyConnections;
    private static final Logger LOG = LogManager.getLogger();

    ConnectionPool(){
        availableConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        busyConnections = new ArrayDeque<>();
        init();
    }

    private void init(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(DB_PROPERTIES));
            String url = properties.getProperty(DB_URL);
            String driver = properties.getProperty(DB_DRIVER);
            Class.forName(driver);
            for(int i = 0; i < DEFAULT_POOL_SIZE; i++){
                availableConnections.add(new ProxyConnection(DriverManager.getConnection(url,properties)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Connection pool wasn't initialized",e);
        }

    }

    public ProxyConnection getConnection(){
        ProxyConnection connection = null;
        if (!availableConnections.isEmpty()) {
            try {
                connection = availableConnections.take();
            } catch (InterruptedException e) {
                LOG.error("Can't get connection",e);
                Thread.currentThread().interrupt();
            }
            busyConnections.add(connection);
            LOG.debug(availableConnections.size());
        }
        LOG.debug("Connection was taken");
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if(connection.getClass() != ProxyConnection.class){
            throw new ConnectionPoolException("Invalid connection");
        }
        LOG.debug("Connection was released");
        busyConnections.remove(connection);
        availableConnections.offer((ProxyConnection)
                connection);
        LOG.info(availableConnections.size());
    }

    public void destroyPool(){
        for(int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try {
                availableConnections.take().closeToDestroy();
            } catch (SQLException | InterruptedException e) {
                LOG.error("Pool hasn't been destroyed",e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers(){
        Enumeration<Driver> drivers= DriverManager.getDrivers();
        while(drivers.hasMoreElements()){
            Driver driver = (Driver) drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOG.error("Deregister driver error",e);
            }
        }
    }

}