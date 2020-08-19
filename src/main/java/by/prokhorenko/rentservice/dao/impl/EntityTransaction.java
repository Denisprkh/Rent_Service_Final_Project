package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;

/**
 * Class for working with dao with transaction support.
 */
public class EntityTransaction {

    private static final Logger LOG = LogManager.getLogger();

    EntityTransaction(){

    }

     void beginTransaction(ProxyConnection connection, AbstractCommonDao ... daos) throws DaoException {
        try {
            connection.setAutoCommit(false);
            for(AbstractCommonDao dao : daos){
                dao.setConnection(connection);
            }
        } catch (SQLException e) {
           throw new DaoException(e);
        }
    }

     void endTransaction(ProxyConnection connection) throws DaoException {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

     void commit(ProxyConnection connection) throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    void rollback(ProxyConnection connection) throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
