package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.FlatPhotoDao;
import by.prokhorenko.rentservice.entity.flat.FlatPhoto;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class FlatPhotoDaoImpl extends AbstractCommonDao implements FlatPhotoDao {

    private static final FlatPhotoDao INSTANCE = new FlatPhotoDaoImpl();
    private static final int BUFFER_SIZE = 4096;
    private static final int BYTES_READ_START_VALUE = -1;
    private FlatPhotoDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    private static final Logger LOG = LogManager.getLogger();
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
            addBase64DataToPhotos(flatPhotos);
            return flatPhotos;
        } catch (SQLException e) {
            throw new DaoException("Finding all flats photo by flats id error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    private List<FlatPhoto> addBase64DataToPhotos(List<FlatPhoto> flatPhotos){
        for(FlatPhoto flatPhoto : flatPhotos){
            InputStream inputStream = flatPhoto.getFlatPhotoData();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = BYTES_READ_START_VALUE;
            while (true) {
                try {
                    if (!((bytesRead = inputStream.read(buffer)) != -1)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            flatPhoto.setBase64PhotoData(base64Image);
        }
        return flatPhotos;
    }

    @Override
    public boolean addAllPhotos(List<FlatPhoto> flatPhotos) throws DaoException {
        boolean photosAreAdded = false;
        for(FlatPhoto flatPhoto : flatPhotos){
            photosAreAdded = add(flatPhoto).isPresent();
        }
        return photosAreAdded;
    }

    @Override
    public Optional<FlatPhoto> add(FlatPhoto photo) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_FLATS_PHOTO,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,photo.getFlatsId());
            statement.setBlob(2,photo.getFlatPhotoData());
            int id = executeUpdateAndGetGeneratedId(statement);
            photo.setId(id);
            LOG.debug("photo was added");
            return Optional.of(photo);
        } catch (SQLException e) {
            throw new DaoException("Adding flats photo error",e);
        }
    }



    @Override
    public List<FlatPhoto> findAll(int start, int total) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLAT_PHOTO);
            ResultSet resultSet = statement.executeQuery()){
            List<FlatPhoto> allFlatPhotos = new ArrayList<>();
            while (resultSet.next()){
                allFlatPhotos.add(buildFlatPhotoFromResultSet(resultSet));
            }
            addBase64DataToPhotos(allFlatPhotos);
            return allFlatPhotos;
        } catch (SQLException e) {
            throw new DaoException("Finding all flats photos error",e);
        }
    }

    @Override
    public Optional<FlatPhoto> findById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<FlatPhoto> update(FlatPhoto flatPhoto) throws DaoException {
        throw new UnsupportedOperationException("Updating flats photo operation is not available");
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
