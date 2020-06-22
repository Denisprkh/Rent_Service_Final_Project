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
            throw new DaoException("Adding flatAddress error",e);
        }
    }

    @Override
    public List<FlatAddress> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLAT_ADDRESSES);
            ResultSet resultSet = statement.executeQuery()) {
            List<FlatAddress> flatAddresses = new ArrayList<>();
            while (resultSet.next()){
                flatAddresses.add(buildEntityFromResultSet(resultSet));
            }
            return flatAddresses;
        } catch (SQLException e) {
            throw new DaoException("Finding all flatsAddresses error",e);
        }
    }

    @Override
    public Optional<FlatAddress> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_ADDRESS_BY_ID)) {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Can't find flatAddress by id",e);
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
           throw new DaoException("Updating flatAddress error",e);
        }
    }

    @Override
    public FlatAddress buildEntityFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatAddressBuilder().buildId(resultSet.getInt(SqlColumnName.FLAT_ADDRESS_ID_COLUMN_NAME))
                    .buildCity(resultSet.getString(SqlColumnName.FLAT_ADDRESS_CITY_COLUMN_NAME))
                    .buildDistrict(resultSet.getString(SqlColumnName.FLAT_ADDRESS_DISTRICT_COLUMN_NAME))
                    .buildStreet(resultSet.getString(SqlColumnName.FLAT_ADDRESS_STREET_COLUMN_NAME))
                    .buildHouse(resultSet.getString(SqlColumnName.FLAT_ADDRESS_HOUSE_COLUMN_NAME))
                    .buildFlatAddress();
        } catch (SQLException e) {
            throw new DaoException("Building flats address from resultSet error",e);
        }
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

    @Override
    public List<FlatAddress> findByFullData(String city, String district, String street, String house)
            throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_ADDRESS_BY_FULL_DATA)) {
            statement.setString(1,city);
            statement.setString(2,district);
            statement.setString(3,street);
            statement.setString(4,house);
            resultSet = statement.executeQuery();
            List<FlatAddress> flatAddresses = new ArrayList<>();
            while(resultSet.next()){
                flatAddresses.add(buildEntityFromResultSet(resultSet));
            }
            return flatAddresses;
        } catch (SQLException e) {
            throw new DaoException("Finding flatAddress by full data error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<FlatAddress> findByCity(String city) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_ADDRESS_BY_FULL_DATA)) {
            statement.setString(1,city);
            resultSet = statement.executeQuery();
            List<FlatAddress> flatAddresses = new ArrayList<>();
            while(resultSet.next()){
                flatAddresses.add(buildEntityFromResultSet(resultSet));
            }
            return flatAddresses;
        } catch (SQLException e) {
            throw new DaoException("Finding flatAddress by city error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<FlatAddress> findByCityAndDistrict(String city, String district) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_ADDRESS_BY_FULL_DATA)) {
            statement.setString(1,city);
            statement.setString(2,district);
            resultSet = statement.executeQuery();
            List<FlatAddress> flatAddresses = new ArrayList<>();
            while(resultSet.next()){
                flatAddresses.add(buildEntityFromResultSet(resultSet));
            }
            return flatAddresses;
        } catch (SQLException e) {
            throw new DaoException("Finding flatAddress by city and district error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }
}
