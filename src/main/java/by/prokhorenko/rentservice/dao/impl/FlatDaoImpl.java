package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.*;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.entity.flat.FlatPhoto;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
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
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_FLAT,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,flat.getFlatDescription().getId());
            statement.setInt(2,flat.getFlatAddress().getId());
            int flatsId = executeUpdateAndGetGeneratedId(statement);
            flat.setId(flatsId);
            return Optional.of(flat);
        } catch (SQLException e) {
            throw new DaoException("Adding flat error",e);
        }
    }



    @Override
    public List<Flat> findAll(int start, int total) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLATS);
            ResultSet resultSet = statement.executeQuery()) {
            List<Flat> allFlats = new ArrayList<>();
            while(resultSet.next()){
                allFlats.add(buildFlatFromResultSet(resultSet));
            }
            return allFlats;
        } catch (SQLException e) {
            throw new DaoException("Finding all flats error",e);
        }
    }

    @Override
    public Optional<Flat> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_BY_ID);
         FlatPhotoDao flatPhotoDao = DaoFactory.getInstance().getFlatPhotoDao()){
            List<FlatPhoto> flatPhotos = flatPhotoDao.findAllPhotosByFlatsId(id);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                Flat flat = buildFlatFromResultSet(resultSet);
                flat.setFlatPhotos(flatPhotos);
                return Optional.of(flat);
            }
            return Optional.empty();
        } catch (SQLException | IOException e) {
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
    public int findQuantity() throws DaoException {
        return 0;
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

}
