package by.prokhorenko.pool;
import by.prokhorenko.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {
    INSTANCE;

    private static final String DB_PROPERTIES = "database.properties";
    private static final String DB_URL = "url";
    private static final String DB_DRIVER = "driver";
    private static final int POOL_SIZE = 32;
    private static final int DEFAULT_CONNECTION_AWAIT = 30;
    private BlockingQueue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> busyConnections;
    private AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final Logger LOG = LogManager.getLogger();

    ConnectionPool(){
        availableConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        busyConnections = new ArrayDeque<>();
    }

    public void init() throws ConnectionPoolException {
        if(!isInitialized.get()){
            ClassLoader classLoader = this.getClass().getClassLoader();
            Properties properties = new Properties();

            try {
                properties.load(classLoader.getResourceAsStream(DB_PROPERTIES));
                String url = properties.getProperty(DB_URL);
                String driver = properties.getProperty(DB_DRIVER);
                Class.forName(driver);
                for(int i = 0; i < POOL_SIZE; i++){
                    availableConnections.add(new ProxyConnection(DriverManager.getConnection(url,properties)));
                }
                isInitialized.set(true);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                throw new ConnectionPoolException("Connection pool wasn't initialized",e);
            }
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        if(isInitialized.get()) {
            ProxyConnection connection = null;
            if (!availableConnections.isEmpty()) {
                try {
                    connection = availableConnections.poll(DEFAULT_CONNECTION_AWAIT, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    LOG.error(e);
                    Thread.currentThread().interrupt();
                }
                busyConnections.add(connection);
            }
            return connection;
        }
        throw new ConnectionPoolException("ConnectionPool is not initialized");
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if(connection.getClass() != ProxyConnection.class){
            throw new ConnectionPoolException("Invalid connection");
        }
        busyConnections.remove(connection);
        availableConnections.offer((ProxyConnection)connection);
    }

    public void destroyPool(){
        for(int i = 0; i < POOL_SIZE; i++){
            try {
                availableConnections.take().close();
            } catch (SQLException | InterruptedException e) {
                LOG.error(e);
            }
        }
        deregisterDrivers();
        isInitialized.set(false);
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
