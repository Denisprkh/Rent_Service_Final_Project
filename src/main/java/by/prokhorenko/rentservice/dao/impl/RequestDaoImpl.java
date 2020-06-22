package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.RequestDao;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public class RequestDaoImpl extends AbstractCommonDao implements RequestDao {
    @Override
    public Optional<Request> add(Request request) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_REQUEST,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,request.getUser().getId());
            statement.setLong(2,dateToLongConverter(request.getStartDate()));
            statement.setLong(3,dateToLongConverter(request.getEndDate()));
            statement.setLong(4,dateToLongConverter(request.getApplicationDate()));
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
    public List<Request> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Request> findById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Request> update(Request request) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Request buildEntityFromResultSet(ResultSet resultSet) throws DaoException {
        return null;
    }

    @Override
    public void close(){
        closeConnection(this.connection);
    }
}
