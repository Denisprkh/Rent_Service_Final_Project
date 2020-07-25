package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.RequestDao;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestDaoImpl extends AbstractCommonDao implements RequestDao {

    private static final RequestDao INSTANCE = new RequestDaoImpl();
    private RequestDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }
    public static RequestDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<Request> add(Request request) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,request.getUser().getId());
            statement.setLong(2,dateToLongConverter(request.getStartDate()));
            statement.setLong(3,dateToLongConverter(request.getEndDate()));
            statement.setLong(4,dateToLongConverter(request.getApplicationDate()));
            statement.setInt(5,request.getAdvertisement().getId());
            int id = executeUpdateAndGetGeneratedId(statement);
            request.setId(id);
            return Optional.of(request);
        } catch (SQLException e) {
            throw new DaoException("Adding request error",e);
        }
    }

    private long dateToLongConverter(LocalDateTime date){
        return date.atZone(ZoneId.systemDefault()).toInstant().
                toEpochMilli();
    }

    @Override
    public List<Request> findAll(int start, int total) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_REQUESTS);
        ResultSet resultSet = statement.executeQuery()) {
            List<Request> allRequests = new ArrayList<>();
            while(resultSet.next()){
                allRequests.add(buildRequestFromResultSet(resultSet));
            }
            return allRequests;
        } catch (SQLException e) {
           throw new DaoException("Finding all requests error",e);
        }
    }

    @Override
    public Optional<Request> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_REQUEST_BY_ID)) {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildRequestFromResultSet(resultSet));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new DaoException("Finding request by id error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<Request> update(Request request) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_REQUEST_BY_ID)) {
            statement.setLong(1,dateToLongConverter(request.getStartDate()));
            statement.setLong(2,dateToLongConverter(request.getEndDate()));
            updateEntity(statement);
            return Optional.of(request);
        } catch (SQLException e) {
            throw new DaoException("Updating request error",e);
        }
    }

    @Override
    public int findQuantity() throws DaoException {
        return 0;
    }


    @Override
    public void close(){
        closeConnection(this.connection);
    }

    @Override
    public List<Request> findRequestsByUsersId(int usersId) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_REQUESTS_BY_USERS_ID)){
            statement.setInt(1,usersId);
            resultSet = statement.executeQuery();
            List<Request> usersRequests= new ArrayList<>();
            while (resultSet.next()){
                buildRequestFromResultSet(resultSet);
            }
            return usersRequests;
        } catch (SQLException e) {
            throw new DaoException("Finding all users requests error",e);
        }
    }

    @Override
    public boolean approveRequest(int requestsId) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_REQUEST_APPROVED_STATUS)){
            statement.setBoolean(1,true);
            statement.setInt(2,requestsId);
            updateEntity(statement);
            return true;
        } catch (SQLException e) {
            throw new DaoException("Approving request error",e);
        }
    }

    @Override
    public List<Request> findRequestsByAdvertisementsId(int advertisementsId) throws DaoException {
        return null;
    }


}
