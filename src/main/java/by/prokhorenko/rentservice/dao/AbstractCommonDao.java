package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;


public abstract class AbstractCommonDao implements AutoCloseable{
    protected ProxyConnection connection;
    private static final Logger LOG = LogManager.getLogger();
    private static final int ONE_ROW_COUNT = 1;
    private static final int GENERATED_ID_ROW_NUMBER = 1;

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            } catch(SQLException e){
            LOG.error("Closing statement error",e);
            }
        }

    protected void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            LOG.error("Closing connection error",e);
        }
    }

    protected void closeResultSet(ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
        }catch(SQLException e){
            LOG.error("Closing resultSet error",e);
        }
    }

    protected void rollbackTransaction(Connection connection){
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error("Transaction rollback error",e);
            }
        }
    }

    protected int executeUpdateAndGetGeneratedId(PreparedStatement statement) throws DaoException {
        ResultSet generatedId = null;
        try {
            int updatedRowCount = statement.executeUpdate();
            if (updatedRowCount == ONE_ROW_COUNT) {
                generatedId = statement.getGeneratedKeys();
                if (generatedId.next()) {
                    return generatedId.getInt(GENERATED_ID_ROW_NUMBER);
                }
            }
            throw new DaoException("Updating row error: 0 rows were updated");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(generatedId);
        }
    }

    protected boolean updateEntity(PreparedStatement statement) throws DaoException {
        try{
            int updatedRows = statement.executeUpdate();
            if(updatedRows != 1){
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Updating entity error",e);
        }
        return true;
    }

    protected boolean updateEntityById(int id, String sqlQuery) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1,id);
            int updatedRows = statement.executeUpdate();
            if(updatedRows != 1){
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Updating entity by id error",e);
        }
        return true;
    }


}
