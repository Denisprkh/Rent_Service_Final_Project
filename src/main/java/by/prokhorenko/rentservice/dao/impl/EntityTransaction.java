package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;

/**
 * Class for working with dao with transaction support
 */
public class EntityTransaction {

    private static final Logger LOG = LogManager.getLogger();

    EntityTransaction(){

    }

     void beginTransaction(ProxyConnection connection, AbstractCommonDao ... daos){
        try {
            connection.setAutoCommit(false);
            for(AbstractCommonDao dao : daos){
                dao.setConnection(connection);
            }
        } catch (SQLException e) {
            LOG.error("Setting connection autoCommit false error",e);
        }
    }

     void endTransaction(ProxyConnection connection){
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Setting connection autoCommit true error",e);
        }
    }

     void commit(ProxyConnection connection){
        try {
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Connection commit error",e);
        }
    }

    void rollback(ProxyConnection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOG.error("Connection rollback error",e);
        }
    }
}
