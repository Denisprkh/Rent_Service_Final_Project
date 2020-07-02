package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.builder.*;
import by.prokhorenko.rentservice.dao.constant.SqlColumnName;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.flat.*;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.entity.user.UserRole;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


public abstract class AbstractCommonDao implements AutoCloseable{
    protected ProxyConnection connection;
    private static final Logger LOG = LogManager.getLogger();
    private static final int ONE_ROW_COUNT = 1;
    private static final int GENERATED_ID_ROW_NUMBER = 1;

    public void setConnection(ProxyConnection connection){
        if(this.connection != null){
            closeConnection(this.connection);
        }
        this.connection = connection;
    }

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            } catch(SQLException e){
            LOG.error("Closing statement error",e);
            }
        }

    protected void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            LOG.error("Closing connection error",e);
        }
    }

    protected void closeResultSet(ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
        }catch(SQLException e){
            LOG.error("Closing resultSet error",e);
        }
    }

    protected void rollbackTransaction(Connection connection){
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error("Transaction rollback error",e);
            }
        }
    }

    protected int executeUpdateAndGetGeneratedId(PreparedStatement statement) throws DaoException {
        ResultSet generatedId = null;
        try {
            int updatedRowCount = statement.executeUpdate();
            if (updatedRowCount == ONE_ROW_COUNT) {
                generatedId = statement.getGeneratedKeys();
                if (generatedId.next()) {
                    return generatedId.getInt(GENERATED_ID_ROW_NUMBER);
                }
            }
            throw new DaoException("Updating row error: 0 rows were updated");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(generatedId);
        }
    }

    protected boolean updateEntity(PreparedStatement statement) throws DaoException {
        try{
            int updatedRows = statement.executeUpdate();
            if(updatedRows != 1){
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Updating entity error",e);
        }
        return true;
    }

    protected boolean updateEntityById(int id, String sqlQuery) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1,id);
            int updatedRows = statement.executeUpdate();
            if(updatedRows != 1){
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Updating entity by id error",e);
        }
        return true;
    }

    protected User buildUserFromResultSet(ResultSet resultSet) throws DaoException {

        try {
            return new UserBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.USERS_ID_COLUMN_NAME))
                    .buildFirstName(resultSet.getString(SqlColumnName.USERS_FIRST_NAME_COLUMN_NAME))
                    .buildLastName(resultSet.getString(SqlColumnName.USERS_LAST_NAME_COLUMN_NAME))
                    .buildEmail(resultSet.getString(SqlColumnName.USERS_EMAIL_COLUMN_NAME))
                    .buildPassword(resultSet.getString(SqlColumnName.USERS_PASSWORD_COLUMN_NAME))
                    .buildPhone(resultSet.getString(SqlColumnName.USERS_PHONE_COLUMN_NAME))
                    .buildUserRole(UserRole.getUserRoleById
                            (resultSet.getInt(SqlColumnName.USERS_ROLE_ID_COLUMN_NAME)).get())
                    .buildLogInToken(resultSet.getString(SqlColumnName.USERS_LOG_IN_TOKEN_COLUMN_NAME))
                    .buildIsBanned(resultSet.getBoolean(SqlColumnName.USERS_IS_BANNED_COLUMN_NAME))
                    .buildUser();
        } catch (SQLException e) {
            throw new DaoException("Building user from resultSet error",e);
        }
    }


    protected FlatPhoto buildFlatPhotoFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatPhotoBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.FLAT_PHOTO_FLATS_PHOTO_ID_COLUMN_NAME))
                    .buildFlatsId(resultSet.getInt(SqlColumnName.FLAT_PHOTO_FLATS_ID_COLUMN_NAME))
                    .buildFlatPhotoData(resultSet.getBinaryStream(SqlColumnName.FLAT_PHOTO_PHOTO_COLUMN_NAME))
                    .buildFlatPhoto();
        } catch (SQLException e) {
            throw new DaoException("Building flatPhoto from resultSet error",e);
        }
    }

    protected FlatDescription buildFlatDescriptionFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatDescriptionBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.FLAT_DESCRIPTION_ID_COLUMN_NAME))
                    .buildRooms(resultSet.getInt(SqlColumnName.FLAT_DESCRIPTION_ROOMS_COLUMN_NAME))
                    .buildLivingArea(resultSet.getFloat(SqlColumnName.FLAT_DESCRIPTION_LIVING_AREA_COLUMN_NAME))
                    .buildHasFurniture(resultSet.getBoolean(SqlColumnName.FLAT_DESCRIPTION_HAS_FURNITURE_COLUMN_NAME))
                    .buildHasHomeAppliances(resultSet.getBoolean
                            (SqlColumnName.FLAT_DESCRIPTION_HAS_HOME_APPLIANCES_COLUMN_NAME))
                    .buildHasTheInternet(resultSet.getBoolean(SqlColumnName.FLAT_DESCRIPTION_HAS_THE_INTERNET_COLUMN_NAME))
                    .buildPossibleWithChild(resultSet.getBoolean
                            (SqlColumnName.FLAT_DESCRIPTION_POSSIBLE_WITH_CHILD_COLUMN_NAME))
                    .buildRepairType(FlatRepairType.getRepairTypeByName(resultSet.getString
                            (SqlColumnName.FLAT_DESCRIPTION_REPAIR_COLUMN_NAME)).get())
                    .buildPossibleWithPets
                            (resultSet.getBoolean(SqlColumnName.FLAT_DESCRIPTION_POSSIBLE_WITH_PETS_COLUMN_NAME))
                    .buildUsersDescription(resultSet.getString(SqlColumnName.FLAT_DESCRIPTION_USERS_DESCRIPTION_COLUMN_NAME))

                    .buildFlatDescription();
        } catch (SQLException e) {
            throw new DaoException("Building flats description from resultSet error",e);
        }
    }

    protected FlatAddress buildFlatAddressFromResultSet(ResultSet resultSet) throws DaoException {
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

    protected Flat buildFlatFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.FLAT_ID_COLUMN_NAME))
                    .buildIsFree(resultSet.getBoolean(SqlColumnName.FLAT_IS_FREE_COLUMN_NAME))
                    .buildFlatDescription(buildFlatDescriptionFromResultSet(resultSet))
                    .buildFlatAddress(buildFlatAddressFromResultSet(resultSet))
                    .buildFlat();
        } catch (SQLException e) {
            throw new DaoException("Building flat from resultSet error",e);
        }
    }

    public Advertisement buildAdvertisementFromResultSet(ResultSet resultSet) throws DaoException {
        try{
            return new AdvertisementBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.ADVERTISEMENT_ADVERTISEMENTS_ID_COLUMN_NAME))
                    .buildAuthor(buildUserFromResultSet(resultSet))
                    .buildFlat(buildFlatFromResultSet(resultSet))
                    .buildTitle(resultSet.getString(SqlColumnName.ADVERTISEMENT_TITLE_COLUMN_NAME))
                    .buildPrice(resultSet.getBigDecimal(SqlColumnName.ADVERTISEMENT_PRICE_COLUMN_NAME))
                    .buildDateOfCreation(convertLongToDate(resultSet.getLong(SqlColumnName.
                            ADVERTISEMENT_DATE_OF_CREATION_COLUMN_NAME)))
                    .buildAdvertisement();
        } catch (SQLException e) {
            throw new DaoException("Building advertisement from resultSet error",e);
        }
    }

    protected Request buildRequestFromResultSet(ResultSet resultSet) throws DaoException{
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(AdvertisementDao advertisementDao = daoFactory.getAdvertisementDao()) {
            return new RequestBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.REQUEST_REQUESTS_ID_COLUMN_NAME))
                    .buildUser(buildUserFromResultSet(resultSet))
                    .buildStartDate(convertLongToDate(resultSet.getLong(SqlColumnName.REQUEST_START_DATE_COLUMN_NAME)))
                    .buildEndDate(convertLongToDate(resultSet.getLong(SqlColumnName.REQUEST_END_DATE_COLUMN_NAME)))
                    .buildApplicationDate(convertLongToDate(resultSet.getLong
                            (SqlColumnName.REQUEST_APPLICATION_DATE_COLUMN_NAME)))
                    .buildAdvertisement(advertisementDao.findById(resultSet.getInt(
                            SqlColumnName.REQUEST_ADVERTISEMENTS_ID_COLUMN_NAME)).get())
                    .buildApproved(resultSet.getBoolean(SqlColumnName.REQUEST_IS_APPROVED_COLUMN_NAME))
                    .buildRequest();
        } catch (SQLException e) {
            throw new DaoException("Building request from resultSet error",e);
        } catch (Exception e) {
            throw new DaoException("Building request from resultSet error",e);

        }
    }

    private LocalDateTime convertLongToDate(long millis){
        LocalDateTime dateFromMillis =  Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dateFromMillis;
    }
}
