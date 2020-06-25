package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.FlatPhotoDao;
import by.prokhorenko.rentservice.entity.flat.FlatPhoto;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlatPhotoDaoImpl extends AbstractCommonDao implements FlatPhotoDao {

    private static final FlatPhotoDao INSTANCE = new FlatPhotoDaoImpl();

    private FlatPhotoDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    public static FlatPhotoDao getInstance(){
        return INSTANCE;
    }

    @Override
    public List<FlatPhoto> findAllPhotosByFlatsId(int flatsId) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_PHOTO_BY_FLATS_ID)){
            statement.setInt(1,flatsId);
            resultSet = statement.executeQuery();
            List<FlatPhoto> flatPhotos = new ArrayList<>();
            while(resultSet.next()){
                flatPhotos.add(buildFlatPhotoFromResultSet(resultSet));
            }
            return flatPhotos;
        } catch (SQLException e) {
            throw new DaoException("Finding all flats photo by flats id error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<FlatPhoto> add(FlatPhoto photo) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_FLATS_PHOTO,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,photo.getFlatsId());
            statement.setBlob(2,photo.getFlatPhotoData());
            int id = executeUpdateAndGetGeneratedId(statement);
            photo.setId(id);
            return Optional.of(photo);
        } catch (SQLException e) {
            throw new DaoException("Adding flats photo error",e);
        }
    }

    @Override
    public List<FlatPhoto> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLAT_PHOTO);
            ResultSet resultSet = statement.executeQuery()){
            List<FlatPhoto> allFlatPhotos = new ArrayList<>();
            while (resultSet.next()){
                allFlatPhotos.add(buildFlatPhotoFromResultSet(resultSet));
            }
            return allFlatPhotos;
        } catch (SQLException e) {
            throw new DaoException("Finding all flats photos error",e);
        }
    }

    @Override
    public Optional<FlatPhoto> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_PHOTO_BY_ID)){
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildFlatPhotoFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding flats photo by id error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<FlatPhoto> update(FlatPhoto flatPhoto) throws DaoException {
        throw new UnsupportedOperationException("Updating flats photo operation is not available");
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }
}
