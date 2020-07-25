package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;

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
            LOG.debug("beginning transaction");
        } catch (SQLException e) {
            LOG.error("Setting connection autoCommit false error"+e.getMessage() + e.getCause(),e);
        }
    }

     void endTransaction(ProxyConnection connection){
        try {
            connection.setAutoCommit(true);
            connection.close();
            LOG.debug("ending transaction");
        } catch (SQLException e) {
            LOG.error("Setting connection autoCommit true error"+e.getMessage() + e.getCause(),e);
        }
    }

     void commit(ProxyConnection connection){
        try {
            connection.commit();
            LOG.debug("Committing transaction");
        } catch (SQLException e) {
            LOG.error("Connection commit error"+e.getMessage() + e.getCause(),e);
        }
    }

    void rollback(ProxyConnection connection){
        try {
            connection.rollback();
            LOG.debug("Rollback transaction");
        } catch (SQLException e) {
            LOG.error("Connection rollback error"+e.getMessage() + e.getCause(),e);
        }
    }
}
