package by.prokhorenko.rentservice.pool;

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

/**
 * Connection pool, which contains connections of ProxyConnection type.
 * Implemented as singleton.
 *
 * @see ProxyConnection
 */
public enum ConnectionPool {
    /**
     * Singleton. Instance.
     */
    INSTANCE;

    /**
     * File name with properties for database.
     */
    private static final String DB_PROPERTIES = "database.properties";

    /**
     * Url.
     */
    private static final String DB_URL = "url";

    /**
     * Driver.
     */
    private static final String DB_DRIVER = "driver";

    /**
     * Pool size.Amount of connections.
     */
    private static final int DEFAULT_POOL_SIZE = 32;

    /**
     * Contains connections, which are available for use.
     */
    private BlockingQueue<ProxyConnection> availableConnections;

    /**
     * Contains connections, which are in use and aren't available to take.
     */
    private Queue<ProxyConnection> busyConnections;
    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger();

    ConnectionPool() {
        availableConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        busyConnections = new ArrayDeque<>();
        init();
    }

    /**
     * Initializes pool by registering drivers and filling container by connections
     *
     * @throws RuntimeException if pool wasn't initialized.
     */
    private void init() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(DB_PROPERTIES));
            String url = properties.getProperty(DB_URL);
            String driver = properties.getProperty(DB_DRIVER);
            Class.forName(driver);
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                availableConnections.add(new ProxyConnection(DriverManager.getConnection(url, properties)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Connection pool wasn't initialized", e);
        }

    }

    /**
     * Returns connection from available connections container.
     *
     * @return connection
     */
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        if (!availableConnections.isEmpty()) {
            try {
                connection = availableConnections.take();
            } catch (InterruptedException e) {
                LOG.error("Can't get connection", e);
                Thread.currentThread().interrupt();
            }
            busyConnections.add(connection);
            LOG.debug(availableConnections.size());
        }
        return connection;
    }

    /**
     * Releases a connection, checks it and puts into available
     * connections container.
     *
     * @param connection received connection
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() != ProxyConnection.class) {
            LOG.warn("Trying to put an invalid connection into a container");
        }
        busyConnections.remove(connection);
        availableConnections.offer((ProxyConnection) connection);
    }

    /**
     * Closes connections.
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.take().closeToDestroy();
                LOG.debug("closing");
            } catch (SQLException | InterruptedException e) {
                LOG.error("Pool hasn't been destroyed", e);
            }
        }
        deregisterDrivers();
    }

    /**
     * Deregister drivers.
     */
    private void deregisterDrivers() {
        LOG.debug("dereg");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOG.error("Deregister driver error", e);
            }
        }
    }

}