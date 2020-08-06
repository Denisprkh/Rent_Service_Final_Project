package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.builder.*;
import by.prokhorenko.rentservice.entity.*;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.entity.UserRole;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Abstract dao class, base for all dao.
 */
public abstract class AbstractCommonDao {

    /**
     * Proxy connection object.
     */
    protected ProxyConnection connection;

    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Expected number of rows which were affected after updating.
     */
    private static final int ONE_ROW_COUNT = 1;

    /**
     * Number of the row where the generated id is stored.
     */
    private static final int GENERATED_ID_ROW_NUMBER = 1;

    /**
     * Sets connection.
     *
     * @param connection
     */
    public void setConnection(ProxyConnection connection) {
        if (this.connection != null) {
            closeConnection(this.connection);
        }
        this.connection = connection;
    }

    /**
     * Closes connection.
     *
     * @param connection
     */
    protected void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("Closing connection error", e);
        }
    }


    /**
     * Closes {@code ResultSet}
     * @param resultSet
     * @throws DaoException if an SQLException occurs
     */
    protected void closeResultSet(ResultSet resultSet) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Executes update and returns generated id
     *
     * @param statement
     * @return id generated id
     * @throws DaoException if an SQLException occurs
     */
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

    /**
     * Updates entity and return the boolean result successfully or not
     *
     * @param statement
     * @return boolean true if successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    protected boolean updateEntity(PreparedStatement statement) throws DaoException {
        try {
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 1) {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Updating entity error", e);
        }
        return true;
    }

    /**
     * Updates entity by id and return the boolean result successfully or not
     *
     * @param id
     * @param sqlQuery
     * @return boolean true if successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    protected boolean updateEntityById(int id, String sqlQuery) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 1) {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Updating entity by id error", e);
        }
        return true;
    }

    /**
     * Builds {@link User} from {@code ResultSet}
     *
     * @param resultSet
     * @return {@link User}
     * @throws DaoException if an SQLException occurs
     */
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
                    .buildIsActivated(resultSet.getBoolean(SqlColumnName.USERS_IS_ACTIVATED_COLUMN_NAME))
                    .buildIsBanned(resultSet.getBoolean(SqlColumnName.USERS_IS_BANNED_COLUMN_NAME))
                    .buildUser();
        } catch (SQLException e) {
            throw new DaoException("Building user from resultSet error", e);
        }
    }

    /**
     * Builds {@link FlatPhoto} from {@code ResultSet}
     *
     * @param resultSet
     * @return {@link FlatPhoto}
     * @throws DaoException if an SQLException occurs
     */
    protected FlatPhoto buildFlatPhotoFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatPhotoBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.FLAT_PHOTO_FLATS_PHOTO_ID_COLUMN_NAME))
                    .buildFlatsId(resultSet.getInt(SqlColumnName.FLAT_PHOTO_FLATS_ID_COLUMN_NAME))
                    .buildFlatPhotoData(resultSet.getBinaryStream(SqlColumnName.FLAT_PHOTO_PHOTO_COLUMN_NAME))
                    .buildFlatPhoto();
        } catch (SQLException e) {
            throw new DaoException("Building flatPhoto from resultSet error", e);
        }
    }

    /**
     * Builds {@link FlatDescription} from {@code ResultSet}
     *
     * @param resultSet
     * @return {@link FlatDescription}
     * @throws DaoException if an SQLException occurs
     */
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
                    .buildPossibleWithPets
                            (resultSet.getBoolean(SqlColumnName.FLAT_DESCRIPTION_POSSIBLE_WITH_PETS_COLUMN_NAME))
                    .buildUsersDescription(resultSet.getString(SqlColumnName.FLAT_DESCRIPTION_USERS_DESCRIPTION_COLUMN_NAME))
                    .buildFlatDescription();
        } catch (SQLException e) {
            throw new DaoException("Building flats description from resultSet error", e);
        }
    }

    /**
     * Builds {@link FlatAddress} from {@code ResultSet}
     *
     * @param resultSet
     * @return {@link FlatAddress}
     * @throws DaoException if an SQLException occurs
     */
    protected FlatAddress buildFlatAddressFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatAddressBuilder().buildId(resultSet.getInt(SqlColumnName.FLAT_ADDRESS_ID_COLUMN_NAME))
                    .buildCity(resultSet.getString(SqlColumnName.FLAT_ADDRESS_CITY_COLUMN_NAME))
                    .buildDistrict(resultSet.getString(SqlColumnName.FLAT_ADDRESS_DISTRICT_COLUMN_NAME))
                    .buildStreet(resultSet.getString(SqlColumnName.FLAT_ADDRESS_STREET_COLUMN_NAME))
                    .buildHouse(resultSet.getString(SqlColumnName.FLAT_ADDRESS_HOUSE_COLUMN_NAME))
                    .buildFlatAddress();
        } catch (SQLException e) {
            throw new DaoException("Building flats address from resultSet error", e);
        }
    }

    /**
     * Builds {@link Flat} from {@code ResultSet}
     *
     * @param resultSet
     * @return {@link Flat}
     * @throws DaoException if an SQLException occurs
     */
    protected Flat buildFlatFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.FLAT_ID_COLUMN_NAME))
                    .buildIsFree(resultSet.getBoolean(SqlColumnName.FLAT_IS_FREE_COLUMN_NAME))
                    .buildFlatDescription(buildFlatDescriptionFromResultSet(resultSet))
                    .buildFlatAddress(buildFlatAddressFromResultSet(resultSet))
                    .buildFlat();
        } catch (SQLException e) {
            throw new DaoException("Building flat from resultSet error", e);
        }
    }

    /**
     * Builds {@link Advertisement} from {@code ResultSet}
     *
     * @param resultSet
     * @return {@link Advertisement}
     * @throws DaoException if an SQLException occurs
     */
    public Advertisement buildAdvertisementFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new AdvertisementBuilder()
                    .buildId(resultSet.getInt(SqlColumnName.ADVERTISEMENT_ADVERTISEMENTS_ID_COLUMN_NAME))
                    .buildAuthor(buildUserFromResultSet(resultSet))
                    .buildFlat(buildFlatFromResultSet(resultSet))
                    .buildTitle(resultSet.getString(SqlColumnName.ADVERTISEMENT_TITLE_COLUMN_NAME))
                    .buildPrice(resultSet.getBigDecimal(SqlColumnName.ADVERTISEMENT_PRICE_COLUMN_NAME))
                    .buildDateOfCreation(convertLongToDate(resultSet.getLong(SqlColumnName.
                            ADVERTISEMENT_DATE_OF_CREATION_COLUMN_NAME)))
                    .buildVisible(Boolean.parseBoolean(resultSet.getString
                            (SqlColumnName.ADVERTISEMENT_IS_VISIBLE_COLUMN_NAME)))
                    .buildAdvertisement();
        } catch (SQLException e) {
            throw new DaoException("Building advertisement from resultSet error", e);
        }
    }

    /**
     * Builds {@link Request} from {@code ResultSet}
     *
     * @param resultSet
     * @return {@code ResultSet}
     * @throws DaoException if an SQLException or IOException occurs
     */
    protected Request buildRequestFromResultSet(ResultSet resultSet) throws DaoException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (AdvertisementDao advertisementDao = daoFactory.getAdvertisementDao()) {
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
        } catch (SQLException | IOException e) {
            throw new DaoException("Building request from resultSet error", e);
        }
    }

    /**
     * Converts {@code long} to {@code LocalDateTime}
     *
     * @param millis
     * @return LocalDateTime form millis
     */
    private LocalDateTime convertLongToDate(long millis) {
        LocalDateTime dateFromMillis = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dateFromMillis;
    }
}
