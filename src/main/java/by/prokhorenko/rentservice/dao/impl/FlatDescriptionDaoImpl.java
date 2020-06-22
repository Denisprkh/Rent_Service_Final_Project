package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.builder.FlatDescriptionBuilder;
import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlColumnName;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.FlatDescriptionDao;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.entity.flat.FlatRepairType;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlatDescriptionDaoImpl extends AbstractCommonDao implements FlatDescriptionDao {

    private static final FlatDescriptionDao INSTANCE = new FlatDescriptionDaoImpl();

    private FlatDescriptionDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    public static FlatDescriptionDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<FlatDescription> add(FlatDescription flatDescription) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_FLAT_DESCRIPTION,
                Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,flatDescription.getRooms());
            statement.setFloat(2,flatDescription.getLivingArea());
            statement.setBoolean(3,flatDescription.isHasFurniture());
            statement.setBoolean(4,flatDescription.isHasHomeAppliances());
            statement.setBoolean(5,flatDescription.isHasTheInternet());
            statement.setBoolean(6,flatDescription.isPossibleWithChild());
            statement.setBoolean(7,flatDescription.isPossibleWithPets());
            statement.setString(8,flatDescription.getRepairType().getRepairType());
            statement.setString(9,flatDescription.getUsersDescription());
            int id = executeUpdateAndGetGeneratedId(statement);
            flatDescription.setId(id);
            return Optional.of(flatDescription);
        } catch (SQLException e) {
            throw new DaoException("Adding flats description error",e);
        }
    }

    @Override
    public List<FlatDescription> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLAT_DESCRIPTIONS);
            ResultSet resultSet = statement.executeQuery()) {
            List<FlatDescription> flatDescriptions = new ArrayList<>();
            while (resultSet.next()){
                flatDescriptions.add(buildEntityFromResultSet(resultSet));
            }
            return flatDescriptions;
        } catch (SQLException e) {
            throw new DaoException("Finding all flats descriptions error",e);
        }
    }

    @Override
    public Optional<FlatDescription> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_DESCRIPTION_BY_ID)){
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by id error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<FlatDescription> update(FlatDescription flatDescription) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_FLAT_DESCRIPTION_BY_ID)) {
            statement.setInt(1,flatDescription.getRooms());
            statement.setFloat(2,flatDescription.getLivingArea());
            statement.setBoolean(3,flatDescription.isHasFurniture());
            statement.setBoolean(4,flatDescription.isHasHomeAppliances());
            statement.setBoolean(5,flatDescription.isHasTheInternet());
            statement.setBoolean(6,flatDescription.isPossibleWithChild());
            statement.setBoolean(7,flatDescription.isPossibleWithPets());
            statement.setString(8,flatDescription.getRepairType().getRepairType());
            statement.setString(9,flatDescription.getUsersDescription());
            statement.setInt(10,flatDescription.getId());
            updateEntity(statement);
            return findById(flatDescription.getId());
        } catch (SQLException e) {
            throw new DaoException("Updating flatAddress error",e);
        }
    }

    @Override
    public FlatDescription buildEntityFromResultSet(ResultSet resultSet) throws DaoException {
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
          throw new DaoException("Building flats description from entity error",e);
        }
    }

    @Override
    public void close() {
        closeConnection(this.connection);
    }

    @Override
    public List<FlatDescription> findByRoomsAmount(int roomsAmount) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_DESCRIPTION_BY_ROOMS_AMOUNT)){
            statement.setInt(1,roomsAmount);
            resultSet = statement.executeQuery();
            List<FlatDescription> flatDescriptions = new ArrayList<>();
            while (resultSet.next()){
                flatDescriptions.add(buildEntityFromResultSet(resultSet));
            }
            return flatDescriptions;
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by rooms amount error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<FlatDescription> findByLivingArea(float livingArea) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_DESCRIPTION_BY_LIVING_AREA)){
            statement.setFloat(1,livingArea);
            resultSet = statement.executeQuery();
            List<FlatDescription> flatDescriptions = new ArrayList<>();
            while(resultSet.next()){
                flatDescriptions.add(buildEntityFromResultSet(resultSet));
            }
            return flatDescriptions;
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by living area error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<FlatDescription> findByRepairType(FlatRepairType repairType) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_DESCRIPTION_BY_REPAIR_TYPE)){
            statement.setString(1,repairType.getRepairType());
            resultSet = statement.executeQuery();
            List<FlatDescription> flatDescriptions = new ArrayList<>();
            while (resultSet.next()){
                flatDescriptions.add(buildEntityFromResultSet(resultSet));
            }
            return flatDescriptions;
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by living area error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }
}
