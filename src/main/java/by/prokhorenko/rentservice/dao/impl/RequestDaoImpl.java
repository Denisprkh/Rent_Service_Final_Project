package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.RequestDao;
import by.prokhorenko.rentservice.dao.SqlQuery;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link RequestDao}
 */
public class RequestDaoImpl extends AbstractCommonDao implements RequestDao {

    private static final RequestDao INSTANCE = new RequestDaoImpl();

    private RequestDaoImpl() {
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    public static RequestDao getInstance() {
        return INSTANCE;
    }

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public Optional<Request> add(Request request) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, request.getUser().getId());
            statement.setLong(2, dateToLongConverter(request.getStartDate()));
            statement.setLong(3, dateToLongConverter(request.getEndDate()));
            statement.setLong(4, dateToLongConverter(request.getApplicationDate()));
            statement.setInt(5, request.getAdvertisement().getId());
            int id = executeUpdateAndGetGeneratedId(statement);
            request.setId(id);
            return Optional.of(request);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private long dateToLongConverter(LocalDateTime date) {
        return date.atZone(ZoneId.systemDefault()).toInstant().
                toEpochMilli();
    }

    @Override
    public List<Request> findAll(int start, int total) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_REQUESTS)) {
            statement.setInt(1, start);
            statement.setInt(2, total);
            resultSet = statement.executeQuery();
            List<Request> allRequests = new ArrayList<>();
            while (resultSet.next()) {
                allRequests.add(buildRequestFromResultSet(resultSet));
            }
            return allRequests;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<Request> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildRequestFromResultSet(resultSet));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<Request> update(Request request) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_REQUEST_BY_ID)) {
            statement.setLong(1, dateToLongConverter(request.getStartDate()));
            statement.setLong(2, dateToLongConverter(request.getEndDate()));
            updateEntity(statement);
            return Optional.of(request);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findQuantity() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_REQUESTS_QUANTITY);
             ResultSet resultSet = statement.executeQuery()) {
            int requestsQuantity = 0;
            if (resultSet.next()) {
                requestsQuantity = resultSet.getInt(1);
            }
            return requestsQuantity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public void close() {
        closeConnection(this.connection);
    }

    @Override
    public List<Request> findRequestsByUsersId(int usersId) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_REQUESTS_BY_USERS_ID)) {
            statement.setInt(1, usersId);
            resultSet = statement.executeQuery();
            List<Request> usersRequests = new ArrayList<>();
            while (resultSet.next()) {
                usersRequests.add(buildRequestFromResultSet(resultSet));
            }
            LOG.debug(usersRequests);
            return usersRequests;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean approveRequest(int requestsId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_REQUEST_APPROVED_STATUS)) {
            statement.setBoolean(1, Boolean.TRUE);
            statement.setInt(2, requestsId);
            return updateEntity(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean disApproveRequest(int requestsId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_REQUEST_APPROVED_STATUS)) {
            statement.setBoolean(1, Boolean.FALSE);
            statement.setInt(2, requestsId);
            return updateEntity(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Request> findRequestsByAdvertisementsAuthorId(int authorId) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_REQUESTS_BY_ADVERTISEMENT_AUTHOR_ID)) {
            statement.setInt(1, authorId);
            resultSet = statement.executeQuery();
            List<Request> requestsOnAdvertisement = new ArrayList<>();
            while (resultSet.next()) {
                requestsOnAdvertisement.add(buildRequestFromResultSet(resultSet));
            }
            return requestsOnAdvertisement;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

}
