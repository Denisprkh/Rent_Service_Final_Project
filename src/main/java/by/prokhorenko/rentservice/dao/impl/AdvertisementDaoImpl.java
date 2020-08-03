package by.prokhorenko.rentservice.dao.impl;


import by.prokhorenko.rentservice.dao.*;
import by.prokhorenko.rentservice.dao.SqlQuery;
import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.UserChoiceDataHandler;
import by.prokhorenko.rentservice.entity.Flat;
import by.prokhorenko.rentservice.entity.FlatAddress;
import by.prokhorenko.rentservice.entity.FlatDescription;
import by.prokhorenko.rentservice.entity.FlatPhoto;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AdvertisementDaoImpl extends AbstractCommonDao implements AdvertisementDao {

    private static final String USER_CHOICE_DEFAULT_REGEX = ".*";
    private static final String REGEX_ON_CLICKED_CHECKBOX = "1";
    private static final Logger LOG = LogManager.getLogger();

    private AdvertisementDaoImpl() {
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    private static final AdvertisementDao INSTANCE = new AdvertisementDaoImpl();

    public static AdvertisementDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Advertisement> add(Advertisement advertisement) throws DaoException {
        EntityTransaction entityTransaction = new EntityTransaction();
        LOG.debug(advertisement);
        try (FlatDao flatDao = DaoFactory.getInstance().getFlatDao();
             FlatAddressDao flatAddressDao = DaoFactory.getInstance().getFlatAddressDao();
             FlatDescriptionDao flatDescriptionDao = DaoFactory.getInstance().getFlatDescriptionDao();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_ADVERTISEMENT,
                     Statement.RETURN_GENERATED_KEYS);
             FlatPhotoDao flatPhotoDao = DaoFactory.getInstance().getFlatPhotoDao()) {
            entityTransaction.beginTransaction(connection, (AbstractCommonDao) flatDao,
                    (AbstractCommonDao) flatAddressDao, (AbstractCommonDao) flatDescriptionDao, (AbstractCommonDao)
                            flatPhotoDao);
            Flat flat = advertisement.getFlat();
            FlatAddress flatAddress = flatAddressDao.add(flat.getFlatAddress()).orElseThrow(DaoException::new);
            FlatDescription flatDescription = flatDescriptionDao.add(flat.getFlatDescription()).
                    orElseThrow(DaoException::new);
            flat.setFlatAddress(flatAddress);
            flat.setFlatDescription(flatDescription);
            flat = flatDao.add(flat).orElseThrow(DaoException::new);
            int flatsId = flat.getId();
            List<FlatPhoto> flatPhotos = setFlatsIdToPhotos(flat.getFlatPhotos(), flatsId);
            if (!flatPhotoDao.addAllPhotos(flatPhotos)) {
                throw new DaoException("Photos were not added");
            }
            long dateOfCreation = advertisement.getDateOfCreation().atZone(ZoneId.systemDefault()).toInstant().
                    toEpochMilli();
            statement.setInt(1, advertisement.getAuthor().getId());
            statement.setInt(2, flatsId);
            statement.setString(3, advertisement.getTitle());
            statement.setBigDecimal(4, advertisement.getPrice());
            statement.setLong(5, dateOfCreation);
            int advertisementsId = executeUpdateAndGetGeneratedId(statement);
            advertisement.setId(advertisementsId);
            entityTransaction.commit(connection);
            return Optional.of(advertisement);
        } catch (IOException | SQLException e) {
            entityTransaction.rollback(connection);
            throw new DaoException(e);
        } finally {
            entityTransaction.endTransaction(connection);
        }
    }


    private List<FlatPhoto> setFlatsIdToPhotos(List<FlatPhoto> flatPhotos, int flatsId) {
        List<FlatPhoto> photos = new ArrayList<>();
        for (FlatPhoto photo : flatPhotos) {
            photo.setFlatsId(flatsId);
            photos.add(photo);
        }
        return photos;
    }

    @Override
    public List<Advertisement> findAll(int start, int total) throws DaoException {
        List<Advertisement> allAdvertisements = findAllAdvertisementsByDifferentSqlRequest
                (SqlQuery.FIND_ALL_ADVERTISEMENTS,start,total);
        return allAdvertisements;
    }



    @Override
    public Optional<Advertisement> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENT_BY_ID);
             FlatPhotoDao flatPhotoDao = DaoFactory.getInstance().getFlatPhotoDao()) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Advertisement advertisement = null;
            if (resultSet.next()) {
                advertisement = buildAdvertisementFromResultSet(resultSet);
            }
            if (advertisement != null) {
                List<FlatPhoto> flatPhotos = flatPhotoDao.findAllPhotosByFlatsId(advertisement.getFlat().getId());
                advertisement.getFlat().setFlatPhotos(flatPhotos);
                return Optional.of(advertisement);
            }
            return Optional.empty();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<Advertisement> update(Advertisement advertisement) throws DaoException {
        EntityTransaction entityTransaction = new EntityTransaction();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ADVERTISEMENT_BY_ID);
        FlatAddressDao flatAddressDao = DaoFactory.getInstance().getFlatAddressDao();
        FlatDescriptionDao flatDescriptionDao = DaoFactory.getInstance().getFlatDescriptionDao();
        FlatPhotoDao flatPhotoDao = DaoFactory.getInstance().getFlatPhotoDao()) {
            entityTransaction.beginTransaction(connection,(AbstractCommonDao) flatAddressDao,
                    (AbstractCommonDao) flatDescriptionDao,(AbstractCommonDao) flatPhotoDao);
            FlatDescription flatDescription = advertisement.getFlat().getFlatDescription();
            FlatAddress flatAddress = advertisement.getFlat().getFlatAddress();
            flatDescriptionDao.update(flatDescription);
            flatAddressDao.update(flatAddress);
            flatPhotoDao.UpdateAllFlatsPhotos(advertisement.getFlat().getFlatPhotos());
            statement.setString(1, advertisement.getTitle());
            statement.setBigDecimal(2, advertisement.getPrice());
            statement.setInt(3, advertisement.getId());
            if (updateEntity(statement)) {
                entityTransaction.commit(connection);
                return (findById(advertisement.getId()));
            }
            entityTransaction.rollback(connection);
            throw new DaoException("Advertisement has not been updated");
        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage() + e.getCause());
            entityTransaction.rollback(connection);
            throw new DaoException(e);
        }finally {
            entityTransaction.endTransaction(connection);
        }
    }

    @Override
    public int findQuantity() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENT_QUANTITY);
             ResultSet resultSet = statement.executeQuery()) {
            int advertisementsQuantity = 0;
            if (resultSet.next()) {
                advertisementsQuantity = resultSet.getInt(1);
            }
            return advertisementsQuantity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

    @Override
    public List<Advertisement> findAllAdvertisementsByUsersId(int id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENTS_BY_USERS_ID);
             FlatPhotoDao flatPhotoDao = DaoFactory.getInstance().getFlatPhotoDao()) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<Advertisement> usersAdvertisements = new ArrayList<>();
            while (resultSet.next()) {
                Advertisement advertisement = buildAdvertisementFromResultSet(resultSet);
                if (advertisement != null) {
                    List<FlatPhoto> flatPhotos = flatPhotoDao.findAllPhotosByFlatsId(advertisement.getFlat().getId());
                    advertisement.getFlat().setFlatPhotos(flatPhotos);
                    usersAdvertisements.add(advertisement);
                }
            }
            return usersAdvertisements;
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    private List<Advertisement> findAllAdvertisementsByDifferentSqlRequest(String sql,int start, int total)
            throws DaoException{
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);
             FlatPhotoDao flatPhotoDao = DaoFactory.getInstance().getFlatPhotoDao()) {
            statement.setInt(1, start);
            statement.setInt(2, total);
            resultSet = statement.executeQuery();
            List<Advertisement> allAdvertisements = new ArrayList<>();
            while (resultSet.next()) {
                Advertisement advertisement = buildAdvertisementFromResultSet(resultSet);
                Flat flat = advertisement.getFlat();
                int flatsId = flat.getId();
                List<FlatPhoto> flatPhotos = flatPhotoDao.findAllPhotosByFlatsId(flatsId);
                flat.setFlatPhotos(flatPhotos);
                allAdvertisements.add(advertisement);
            }
            return allAdvertisements;
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }


    @Override
    public List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceDataHandler advertisementDataHandler,
                                                               int start, int total) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENT_BY_USERS_CHOICE);
             FlatPhotoDao flatPhotoDao = DaoFactory.getInstance().getFlatPhotoDao()) {
            List<String> regexForStatement = extractRegexForFilterFromUsersChoice(advertisementDataHandler);
            int index = 0;
            for (String regex : regexForStatement) {
                statement.setString(++index, regex);
            }
            statement.setInt(11, start);
            statement.setInt(12, total);
            resultSet = statement.executeQuery();
            List<Advertisement> advertisements = new ArrayList<>();
            while (resultSet.next()) {
                Advertisement advertisement = buildAdvertisementFromResultSet(resultSet);
                Flat flat = advertisement.getFlat();
                int flatsId = flat.getId();
                List<FlatPhoto> flatPhotos = flatPhotoDao.findAllPhotosByFlatsId(flatsId);
                flat.setFlatPhotos(flatPhotos);
                advertisements.add(advertisement);
            }
            return advertisements;
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean setAdvertisementStatusInvisible(int advertisementsId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.SET_ADVERTISEMENT_INVISIBLE_BY_ID)) {
            statement.setInt(1, advertisementsId);
            boolean isInvisible = false;
            if (statement.executeUpdate() == 1) {
                isInvisible = true;
            }
            return isInvisible;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findFilteredAdvertisementsQuantity(UserChoiceDataHandler dataHandler) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENT_BY_USERS_CHOICE_QUANTITY)) {
            List<String> regexForStatement = extractRegexForFilterFromUsersChoice(dataHandler);
            int index = 0;
            for (String regex : regexForStatement) {
                statement.setString(++index, regex);
            }
            resultSet = statement.executeQuery();
            int quantity = 0;
            if (resultSet.next()) {
                quantity = resultSet.getInt(1);
            }
            return quantity;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<Advertisement> findAllNotRentedAdvertisements(int start, int total) throws DaoException {
        List<Advertisement> allNotInRentAdvertisements = findAllAdvertisementsByDifferentSqlRequest
                (SqlQuery.FIND_ALL_ADVERTISEMENTS_NOT_IN_RENT,start,total);
        return allNotInRentAdvertisements;
    }

    @Override
    public int findNotInRentAdvertisementsQuantity() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_NOT_IN_RENT_ADVERTISEMENTS_QUANTITY);
        ResultSet resultSet = statement.executeQuery()){
            int notRentedAdsQuantity = 0;
            if(resultSet.next()){
                notRentedAdsQuantity = resultSet.getInt(1);
            }
            return notRentedAdsQuantity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<String> extractRegexForFilterFromUsersChoice(UserChoiceDataHandler userChoiceDataHandler) {
        String cityRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getCity()) ? userChoiceDataHandler.getCity() :
                USER_CHOICE_DEFAULT_REGEX;
        String districtRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getDistrict()) ?
                userChoiceDataHandler.getDistrict() : USER_CHOICE_DEFAULT_REGEX;
        String streetRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getStreet()) ? userChoiceDataHandler.getStreet() :
                USER_CHOICE_DEFAULT_REGEX;
        String roomsRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getRooms()) ? userChoiceDataHandler.getRooms()
                : USER_CHOICE_DEFAULT_REGEX;
        String livingAreaRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getLivingArea()) ?
                userChoiceDataHandler.getLivingArea() : USER_CHOICE_DEFAULT_REGEX;
        String hasFurnitureRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getHasFurniture()) ?
                REGEX_ON_CLICKED_CHECKBOX : USER_CHOICE_DEFAULT_REGEX;
        String hasHomeAppliancesRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getHasHomeAppliances()) ?
                REGEX_ON_CLICKED_CHECKBOX : USER_CHOICE_DEFAULT_REGEX;
        String possibleWithPetsRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getPossibleWithPets()) ?
                REGEX_ON_CLICKED_CHECKBOX : USER_CHOICE_DEFAULT_REGEX;
        String possibleWithChildRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getPossibleWithChild()) ?
                REGEX_ON_CLICKED_CHECKBOX : USER_CHOICE_DEFAULT_REGEX;
        String priceRegex = regexIsNotBlankOrNull(userChoiceDataHandler.getPrice()) ?
                userChoiceDataHandler.getPrice() : USER_CHOICE_DEFAULT_REGEX;
        List<String> regexForStatement = List.of(cityRegex, districtRegex, streetRegex, roomsRegex, livingAreaRegex,
                hasFurnitureRegex, hasHomeAppliancesRegex, possibleWithPetsRegex, possibleWithChildRegex, priceRegex);
        return regexForStatement;
    }

    private boolean regexIsNotBlankOrNull(String regex) {
        boolean regexIsNotNullOrBlank = !(regex == null || regex.isBlank());
        return regexIsNotNullOrBlank;
    }

}
