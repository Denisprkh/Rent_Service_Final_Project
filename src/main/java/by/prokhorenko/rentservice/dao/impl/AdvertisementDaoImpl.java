package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.builder.AdvertisementBuilder;
import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlColumnName;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.AdvertisementDao;
import by.prokhorenko.rentservice.dao.FlatDao;
import by.prokhorenko.rentservice.dao.UserDao;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvertisementDaoImpl extends AbstractCommonDao implements AdvertisementDao {

    private static final Logger LOG = LogManager.getLogger();
    public AdvertisementDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    @Override
    public Optional<Advertisement> add(Advertisement advertisement) throws DaoException {
        FlatDao flatDao = FlatDaoImpl.getInstance();
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            Optional<Flat> flat = flatDao.add(advertisement.getFlat());
            if(!flat.isPresent()){
               throw new DaoException("Adding flat in advertisement error");
            }
            int flatsId = flat.get().getId();
            int authorsId = advertisement.getAuthor().getId();
            long timeInMillis = advertisement.getDateOfCreation().atZone(ZoneId.systemDefault()).toInstant().
                    toEpochMilli();
            statement = connection.prepareStatement(SqlQuery.ADD_ADVERTISEMENT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,authorsId);
            statement.setInt(2,flatsId);
            statement.setString(3,advertisement.getTitle());
            statement.setBigDecimal(4,advertisement.getPrice());
            statement.setLong(5,timeInMillis);
            int advertisementsId = executeUpdateAndGetGeneratedId(statement);
            advertisement.setId(advertisementsId);
            connection.commit();
            return Optional.of(advertisement);
        } catch (SQLException e) {
            rollbackTransaction(connection);
            throw new DaoException("Adding advertisement error",e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error("Setting connection autocommit true error",e);
            }
            closeStatement(statement);
        }
    }

    @Override
    public List<Advertisement> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ADVERTISEMENTS);
        ResultSet resultSet = statement.executeQuery()) {
            List<Advertisement> allAdvertisements = new ArrayList<>();
            while(resultSet.next()){
                allAdvertisements.add(buildEntityFromResultSet(resultSet));
            }
            return allAdvertisements;
        } catch (SQLException e) {
            throw new DaoException("Finding all advertisements error",e);
        }
    }

    @Override
    public Optional<Advertisement> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENT_BY_ID)) {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding all advertisements error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<Advertisement> update(Advertisement advertisement) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ADVERTISEMENT_BY_ID)) {
            statement.setString(1,advertisement.getTitle());
            statement.setBigDecimal(2,advertisement.getPrice());
            statement.setInt(3,advertisement.getId());
            if(updateEntity(statement)){
                return (findById(advertisement.getId()));
            }
            throw new DaoException("Advertisement has not been updated");
        } catch (SQLException e) {
            throw new DaoException("Advertisement updating error",e);
        }
    }

    @Override
    public Advertisement buildEntityFromResultSet(ResultSet resultSet) throws DaoException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao();
            FlatDao flatDao = daoFactory.getFlatDao()) {
            return new AdvertisementBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.ADVERTISEMENT_ADVERTISEMENTS_ID_COLUMN_NAME))
                    .buildAuthor(userDao.buildEntityFromResultSet(resultSet))
                    .buildFlat(flatDao.buildEntityFromResultSet(resultSet))
                    .buildTitle(resultSet.getString(SqlColumnName.ADVERTISEMENT_TITLE_COLUMN_NAME))
                    .buildPrice(resultSet.getBigDecimal(SqlColumnName.ADVERTISEMENT_PRICE_COLUMN_NAME))
                    .buildDateOfCreation(Instant.ofEpochMilli(resultSet.getLong(SqlColumnName.
                            ADVERTISEMENT_DATE_OF_CREATION_COLUMN_NAME)).atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .buildAdvertisement();
        } catch (SQLException e) {
            throw new DaoException("Building advertisement from resultSet error",e);
        } catch (Exception e) {
            throw new DaoException("Building advertisement from resultSet error: can't" +
                    "close flat and user dao",e);
        }
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

    @Override
    public List<Advertisement> findAllAdvertisementsByUsersId(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENTS_BY_USERS_ID)) {
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            List<Advertisement> usersAdvertisements = new ArrayList<>();
            while(resultSet.next()){
                usersAdvertisements.add(buildEntityFromResultSet(resultSet));
            }
            return usersAdvertisements;
        } catch (SQLException e) {
            throw new DaoException("Finding all users advertisements error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }
}
