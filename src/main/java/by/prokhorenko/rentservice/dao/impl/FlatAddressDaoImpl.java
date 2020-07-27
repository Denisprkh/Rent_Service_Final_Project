package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.builder.FlatAddressBuilder;
import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlColumnName;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.FlatAddressDao;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlatAddressDaoImpl extends AbstractCommonDao implements FlatAddressDao {

    private static final FlatAddressDao INSTANCE = new FlatAddressDaoImpl();
    private FlatAddressDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }
    public static FlatAddressDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<FlatAddress> add(FlatAddress flatAddress) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_FLAT_ADDRESS,
                Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,flatAddress.getCity());
            statement.setString(2,flatAddress.getDistrict());
            statement.setString(3,flatAddress.getStreet());
            statement.setString(4,flatAddress.getHouse());
            int id = executeUpdateAndGetGeneratedId(statement);
            flatAddress.setId(id);
            return Optional.of(flatAddress);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FlatAddress> findAll(int start, int total) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLAT_ADDRESSES);
            ResultSet resultSet = statement.executeQuery()) {
            List<FlatAddress> flatAddresses = new ArrayList<>();
            while (resultSet.next()){
                flatAddresses.add(buildFlatAddressFromResultSet(resultSet));
            }
            return flatAddresses;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<FlatAddress> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_ADDRESS_BY_ID)) {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildFlatAddressFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<FlatAddress> update(FlatAddress flatAddress) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_FLAT_ADDRESS_BY_ID)) {
            statement.setString(1,flatAddress.getCity());
            statement.setString(2,flatAddress.getDistrict());
            statement.setString(3,flatAddress.getStreet());
            statement.setString(4,flatAddress.getHouse());
            statement.setInt(5,flatAddress.getId());
            updateEntity(statement);
            return findById(flatAddress.getId());
        } catch (SQLException e) {
           throw new DaoException(e);
        }
    }

    @Override
    public int findQuantity() throws DaoException {
        return 0;
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

}
