package by.prokhorenko.rentservice.dao.flat.impl;

import by.prokhorenko.rentservice.builder.FlatAddressBuilder;
import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.SQLColumnName;
import by.prokhorenko.rentservice.dao.SQLQuery;
import by.prokhorenko.rentservice.dao.flat.FlatAddressDao;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlatAddressDaoImpl extends AbstractCommonDao implements FlatAddressDao {

    public FlatAddressDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    @Override
    public boolean add(FlatAddress flatAddress) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.ADD_FLAT_ADDRESS)){
            statement.setString(1,flatAddress.getCity());
            statement.setString(2,flatAddress.getDistrict());
            statement.setString(3,flatAddress.getStreet());
            statement.setString(4,flatAddress.getHouse());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Adding flatAddress error",e);
        }
        return true;
    }

    @Override
    public List<FlatAddress> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_ALL_FLAT_ADDRESSES);
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
    public FlatAddress findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_FLAT_ADDRESS_BY_ID)) {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            FlatAddress flatAddress = null;
            while(resultSet.next()){
                flatAddress = buildEntityFromResultSet(resultSet);
            }
            return flatAddress;
        } catch (SQLException e) {
            throw new DaoException("Can't find flatAddress by id",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public FlatAddress update(FlatAddress flatAddress) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_FLAT_ADDRESS_BY_ID)) {
            statement.setString(1,flatAddress.getCity());
            statement.setString(2,flatAddress.getDistrict());
            statement.setString(3,flatAddress.getStreet());
            statement.setString(4,flatAddress.getHouse());
            statement.setInt(5,flatAddress.getId());
            statement.executeUpdate();
            return findById(flatAddress.getId());
        } catch (SQLException e) {
           throw new DaoException("Updating flatAddress error",e);
        }
    }

    @Override
    public FlatAddress buildEntityFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatAddressBuilder().buildId(resultSet.getInt(SQLColumnName.FLAT_ADDRESS_ID_COLUMN_NAME))
                    .buildCity(resultSet.getString(SQLColumnName.FLAT_ADDRESS_CITY_COLUMN_NAME))
                    .buildDistrict(resultSet.getString(SQLColumnName.FLAT_ADDRESS_DISTRICT_COLUMN_NAME))
                    .buildStreet(resultSet.getString(SQLColumnName.FLAT_ADDRESS_STREET_COLUMN_NAME))
                    .buildHouse(resultSet.getString(SQLColumnName.FLAT_ADDRESS_HOUSE_COLUMN_NAME))
                    .buildFlatAddress();
        } catch (SQLException e) {
            throw new DaoException("Building flats address from resultSet error",e);
        }
    }

    @Override
    public void closeConnection() {
        closeConnection(this.connection);
    }

    @Override
    public FlatAddress getFlatAddressByFullData(String city, String district, String street, String house) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_FLAT_ADDRESS_BY_FULL_DATA)) {
            resultSet = statement.executeQuery();
            FlatAddress flatAddress = null;
            while (resultSet.next()){
                flatAddress = buildEntityFromResultSet(resultSet);
            }
            return flatAddress;
        } catch (SQLException e) {
            throw new DaoException("Getting flatAddress by full data error",e);
        }
    }
}
