package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.builder.FlatBuilder;
import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlColumnName;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.FlatAddressDao;
import by.prokhorenko.rentservice.dao.FlatDao;
import by.prokhorenko.rentservice.dao.FlatDescriptionDao;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlatDaoImpl extends AbstractCommonDao implements FlatDao {

    private static final Logger LOG = LogManager.getLogger();
    private static final FlatDao INSTANCE = new FlatDaoImpl();
    private FlatDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }
    public static FlatDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<Flat> add(Flat flat) throws DaoException {
        FlatDescriptionDao descriptionDao = FlatDescriptionDaoImpl.getInstance();
        FlatAddressDao addressDao = FlatAddressDaoImpl.getInstance();
        PreparedStatement statement = null;
     try {
         connection.setAutoCommit(false);
         FlatDescription flatDescription = descriptionDao.add(flat.getFlatDescription()).get();//FIXME
         FlatAddress flatAddress = addressDao.add(flat.getFlatAddress()).get();//FIXME
         statement = connection.prepareStatement(SqlQuery.ADD_FLAT, Statement.RETURN_GENERATED_KEYS);
         statement.setInt(1,flatDescription.getId());
         statement.setInt(2,flatAddress.getId());
         int id = executeUpdateAndGetGeneratedId(statement);
         flat.setId(id);
         connection.commit();
         return Optional.of(flat);
     } catch (SQLException e) {
         rollbackTransaction(connection);
         throw new DaoException("Adding flat error",e);
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
    public List<Flat> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLATS);
            ResultSet resultSet = statement.executeQuery()) {
            List<Flat> allFlats = new ArrayList<>();
            while(resultSet.next()){
                allFlats.add(buildEntityFromResultSet(resultSet));
            }
            return allFlats;
        } catch (SQLException e) {
            throw new DaoException("Finding all flats error",e);
        }
    }

    @Override
    public Optional<Flat> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_BY_ID)){
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding flat by id error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<Flat> update(Flat flat) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_FLAT_BY_ID)) {
           statement.setBoolean(1,flat.isFree());
           updateEntity(statement);
           return findById(flat.getId());
        } catch (SQLException e) {
            throw new DaoException("Updating flatAddress error",e);
        }
    }

    @Override
    public Flat buildEntityFromResultSet(ResultSet resultSet) throws DaoException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(FlatDescriptionDao descriptionDao = daoFactory.getFlatDescriptionDao();
        FlatAddressDao addressDao = daoFactory.getFlatAddressDao()) {
            return new FlatBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.FLAT_ID_COLUMN_NAME))
                    .buildIsFree(resultSet.getBoolean(SqlColumnName.FLAT_IS_FREE_COLUMN_NAME))
                    .buildFlatDescription(descriptionDao.buildEntityFromResultSet(resultSet))
                    .buildFlatAddress(addressDao.buildEntityFromResultSet(resultSet))
                    .buildFlat();
        } catch (SQLException e) {
            throw new DaoException("Building flat from resultSet error",e);
        } catch (Exception e) {
            throw new DaoException("Building flat from resultSet error: can't close " +
                    "flatDescription and flatAddress dao ",e);
        }
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

}
