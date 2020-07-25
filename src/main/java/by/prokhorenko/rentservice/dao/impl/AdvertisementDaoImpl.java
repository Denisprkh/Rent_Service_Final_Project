package by.prokhorenko.rentservice.dao.impl;


import by.prokhorenko.rentservice.dao.*;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserChoiceDataHandler;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AdvertisementDaoImpl extends AbstractCommonDao implements AdvertisementDao {

    private static final String USER_CHOICE_DEFAULT_REGEX = ".*";
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
                    (AbstractCommonDao) flatAddressDao, (AbstractCommonDao) flatDescriptionDao, (AbstractCommonDao) flatPhotoDao);
            Flat flat = advertisement.getFlat();
            FlatAddress flatAddress = flatAddressDao.add(flat.getFlatAddress()).orElseThrow(DaoException::new);
            FlatDescription flatDescription = flatDescriptionDao.add(flat.getFlatDescription()).
                    orElseThrow(DaoException::new);
            flat.setFlatAddress(flatAddress);
            flat.setFlatDescription(flatDescription);
            flat = flatDao.add(flat).orElseThrow(DaoException::new);
            int flatsId = flat.getId();
            List<FlatPhoto> flatPhotos = setFlatsIdToPhotos(flat.getFlatPhotos(),flatsId);
            if(!flatPhotoDao.addAllPhotos(flatPhotos)){
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
            LOG.debug(e.getMessage()+" "+e.getCause());
            throw new DaoException("Adding advertisement error", e);
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
        return flatPhotos;
    }

    @Override
    public List<Advertisement> findAll(int start, int total) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ADVERTISEMENTS);
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
            throw new DaoException("Finding all advertisements error", e);
        } finally {
            closeResultSet(resultSet);
        }
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
            List<FlatPhoto> flatPhotos = flatPhotoDao.findAllPhotosByFlatsId(advertisement.getFlat().getId());
            advertisement.getFlat().setFlatPhotos(flatPhotos);
            return Optional.of(advertisement);
        } catch (SQLException | IOException e) {
            throw new DaoException("Finding advertisement by id error", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<Advertisement> update(Advertisement advertisement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ADVERTISEMENT_BY_ID)) {
            statement.setString(1, advertisement.getTitle());
            statement.setBigDecimal(2, advertisement.getPrice());
            statement.setInt(3, advertisement.getId());
            if (updateEntity(statement)) {
                return (findById(advertisement.getId()));
            }
            throw new DaoException("Advertisement has not been updated");
        } catch (SQLException e) {
            throw new DaoException("Advertisement updating error", e);
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
            throw new DaoException("Finding advertisements quantity error", e);
        }
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

    @Override
    public List<Advertisement> findAllAdvertisementsByUsersId(int id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENTS_BY_USERS_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<Advertisement> usersAdvertisements = new ArrayList<>();
            while (resultSet.next()) {
                usersAdvertisements.add(buildAdvertisementFromResultSet(resultSet));
            }
            return usersAdvertisements;
        } catch (SQLException e) {
            throw new DaoException("Finding all users advertisements error", e);
        } finally {
            closeResultSet(resultSet);
        }
    }


    @Override
    public List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceDataHandler advertisementDataHandler) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENT_BY_USERS_CHOICE)) {
            List<String> regexForStatement = extractRegexForFilterFromUsersChoice(advertisementDataHandler);
            int index = 0;
            for (String regex : regexForStatement) {
                statement.setString(++index, regex);
            }
            resultSet = statement.executeQuery();
            List<Advertisement> advertisements = new ArrayList<>();
            while (resultSet.next()) {
                advertisements.add(buildAdvertisementFromResultSet(resultSet));
            }
            return advertisements;
        } catch (SQLException e) {
            throw new DaoException("Finding advertisements by user choice error", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean setAdvertisementStatusInvisible(int advertisementsId) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.SET_ADVERTISEMENT_INVISIBLE_BY_ID)){
            statement.setInt(1,advertisementsId);
            boolean isInvisible = false;
            if(statement.executeUpdate() == 1){
                isInvisible = true;
            }
            return isInvisible;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<String> extractRegexForFilterFromUsersChoice(UserChoiceDataHandler userChoiceDataHandler) {
        String cityRegex = userChoiceDataHandler.getCity() != null ? userChoiceDataHandler.getCity() :
                USER_CHOICE_DEFAULT_REGEX;
        String districtRegex = userChoiceDataHandler.getDistrict() != null ? userChoiceDataHandler.getDistrict() :
                USER_CHOICE_DEFAULT_REGEX;
        String streetRegex = userChoiceDataHandler.getStreet() != null ? userChoiceDataHandler.getStreet() :
                USER_CHOICE_DEFAULT_REGEX;
        String roomsRegex = userChoiceDataHandler.getRooms() == 0 ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(userChoiceDataHandler.getRooms());
        String livingAreaRegex = userChoiceDataHandler.getLivingArea() == 0.0 ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(userChoiceDataHandler.getLivingArea());
        String hasFurnitureRegex = !userChoiceDataHandler.isHasFurniture() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String hasHomeAppliancesRegex = !userChoiceDataHandler.isHasHomeAppliances() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String possibleWithPetsRegex = !userChoiceDataHandler.isPossibleWithPets() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String possibleWithChildRegex = !userChoiceDataHandler.isPossibleWithChild() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String priceRegex = userChoiceDataHandler.getPrice() != null ?  userChoiceDataHandler.getPrice().toString():
                USER_CHOICE_DEFAULT_REGEX  ;
        List<String> regexForStatement = List.of(cityRegex,districtRegex,streetRegex,roomsRegex,priceRegex,
                livingAreaRegex,hasFurnitureRegex,hasHomeAppliancesRegex,possibleWithChildRegex,
                possibleWithPetsRegex);
        return regexForStatement;
    }

}
