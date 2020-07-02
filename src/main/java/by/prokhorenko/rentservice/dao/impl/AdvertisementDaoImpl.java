package by.prokhorenko.rentservice.dao.impl;


import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.AdvertisementDao;
import by.prokhorenko.rentservice.dao.FlatDao;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserChoiceHandler;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AdvertisementDaoImpl extends AbstractCommonDao implements AdvertisementDao {

    private static final String USER_CHOICE_DEFAULT_REGEX = ".*";
    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }
    private static final AdvertisementDao INSTANCE = new AdvertisementDaoImpl();
    public static AdvertisementDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<Advertisement> add(Advertisement advertisement) throws DaoException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        FlatDao flatDao = daoFactory.getFlatDao();
        EntityTransaction entityTransaction = new EntityTransaction();
        PreparedStatement statement = null;
        try {
            entityTransaction.beginTransaction(connection,(AbstractCommonDao)flatDao);
            Optional<Flat> flat = flatDao.add(advertisement.getFlat());
            int flatsId = flat.orElseThrow(DaoException::new).getId();
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
            entityTransaction.commit(connection);
            return Optional.of(advertisement);
        } catch (SQLException e) {
            entityTransaction.rollback(connection);
            throw new DaoException("Adding advertisement error",e);
        } catch (Exception e) {
            entityTransaction.rollback(connection);
            throw new DaoException("Adding advertisement error",e);
        }finally {
            entityTransaction.endTransaction(connection);
            closeStatement(statement);
        }
    }

    @Override
    public List<Advertisement> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ADVERTISEMENTS);
        ResultSet resultSet = statement.executeQuery()) {
            List<Advertisement> allAdvertisements = new ArrayList<>();
            while(resultSet.next()){
                allAdvertisements.add(buildAdvertisementFromResultSet(resultSet));
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
                return Optional.of(buildAdvertisementFromResultSet(resultSet));
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
                usersAdvertisements.add(buildAdvertisementFromResultSet(resultSet));
            }
            return usersAdvertisements;
        } catch (SQLException e) {
            throw new DaoException("Finding all users advertisements error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceHandler userChoiceHandler) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ADVERTISEMENT_BY_USERS_CHOICE)) {
        List<String> regexForStatement = extractRegexForFilterFromUsersChoice(userChoiceHandler);
        int index = 0;
        for(String regex : regexForStatement){
            statement.setString(++index,regex);
        }
            System.out.println(statement);
        resultSet = statement.executeQuery();
        List<Advertisement> advertisements = new ArrayList<>();
        while(resultSet.next()){
            advertisements.add(buildAdvertisementFromResultSet(resultSet));
        }
        return advertisements;
        } catch (SQLException e) {
            throw new DaoException("Finding advertisements by user choice error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    private List<String> extractRegexForFilterFromUsersChoice(UserChoiceHandler userChoiceHandler){
        String cityRegex = userChoiceHandler.getCity() != null ? userChoiceHandler.getCity() :
                USER_CHOICE_DEFAULT_REGEX;
        String districtRegex = userChoiceHandler.getDistrict() != null ? userChoiceHandler.getDistrict() :
                USER_CHOICE_DEFAULT_REGEX;
        String streetRegex = userChoiceHandler.getStreet() != null ? userChoiceHandler.getStreet() :
                USER_CHOICE_DEFAULT_REGEX;
        String roomsRegex = userChoiceHandler.getRooms() == 0 ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(userChoiceHandler.getRooms());
        String livingAreaRegex = userChoiceHandler.getLivingArea() == 0.0 ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(userChoiceHandler.getLivingArea());
        String  repairTypeRegex = userChoiceHandler.getRepairType() != null ?
                userChoiceHandler.getRepairType().getRepairType() : USER_CHOICE_DEFAULT_REGEX;
        String hasFurnitureRegex = !userChoiceHandler.isHasFurniture() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String hasHomeAppliancesRegex = !userChoiceHandler.isHasHomeAppliances() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String possibleWithPetsRegex = !userChoiceHandler.isPossibleWithPets() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String possibleWithChildRegex = !userChoiceHandler.isPossibleWithChild() ? USER_CHOICE_DEFAULT_REGEX :
                String.valueOf(1);
        String priceRegex = userChoiceHandler.getPrice() != null ?  userChoiceHandler.getPrice().toString():
                USER_CHOICE_DEFAULT_REGEX  ;
        List<String> regexForStatement = List.of(cityRegex,districtRegex,streetRegex,roomsRegex,priceRegex,
                livingAreaRegex,repairTypeRegex,hasFurnitureRegex,hasHomeAppliancesRegex,possibleWithChildRegex,
                possibleWithPetsRegex);
        return regexForStatement;
    }

}
