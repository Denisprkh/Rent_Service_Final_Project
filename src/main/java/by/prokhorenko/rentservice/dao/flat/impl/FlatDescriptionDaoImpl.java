package by.prokhorenko.rentservice.dao.flat.impl;

import by.prokhorenko.rentservice.builder.FlatDescriptionBuilder;
import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.SQLColumnName;
import by.prokhorenko.rentservice.dao.SQLQuery;
import by.prokhorenko.rentservice.dao.flat.FlatDescriptionDao;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.entity.flat.FlatRepairType;
import by.prokhorenko.rentservice.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlatDescriptionDaoImpl extends AbstractCommonDao implements FlatDescriptionDao {

    @Override
    public boolean add(FlatDescription flatDescription) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.ADD_FLAT_DESCRIPTION)){
            statement.setInt(1,flatDescription.getRooms());
            statement.setFloat(2,flatDescription.getLivingArea());
            statement.setBoolean(3,flatDescription.isHasFurniture());
            statement.setBoolean(4,flatDescription.isHasHomeAppliances());
            statement.setBoolean(5,flatDescription.isPossibleWithChild());
            statement.setBoolean(6,flatDescription.isPossibleWithPets());
            statement.setString(7,flatDescription.getUsersDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Adding flats description error",e);
        }
        return true;
    }

    @Override
    public List<FlatDescription> findAll() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_ALL_FLAT_DESCRIPTIONS);
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
    public FlatDescription findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_FLAT_DESCRIPTION_BY_ID)){
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return buildEntityFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by id error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public FlatDescription update(FlatDescription flatDescription) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_FLAT_DESCRIPTION_BY_ID)) {
            statement.setInt(1,flatDescription.getRooms());
            statement.setFloat(2,flatDescription.getLivingArea());
            statement.setBoolean(3,flatDescription.isHasFurniture());
            statement.setBoolean(4,flatDescription.isHasHomeAppliances());
            statement.setBoolean(5,flatDescription.isPossibleWithChild());
            statement.setBoolean(6,flatDescription.isPossibleWithPets());
            statement.setString(7,flatDescription.getUsersDescription());
            statement.executeUpdate();
            return findById(flatDescription.getId());
        } catch (SQLException e) {
            throw new DaoException("Updating flatAddress error",e);
        }
    }

    @Override
    public FlatDescription buildEntityFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            return new FlatDescriptionBuilder()
                    .buildId(resultSet.getInt(SQLColumnName.FLAT_DESCRIPTION_ID_COLUMN_NAME))
                    .buildRooms(resultSet.getInt(SQLColumnName.FLAT_DESCRIPTION_ROOMS_COLUMN_NAME))
                    .buildLivingArea(resultSet.getFloat(SQLColumnName.FLAT_DESCRIPTION_LIVING_AREA_COLUMN_NAME))
                    .buildHasFurniture(resultSet.getBoolean(SQLColumnName.FLAT_DESCRIPTION_HAS_FURNITURE_COLUMN_NAME))
                    .buildHasHomeAppliances(resultSet.getBoolean
                            (SQLColumnName.FLAT_DESCRIPTION_HAS_HOME_APPLIANCES_COLUMN_NAME))
                    .buildPossibleWithChild(resultSet.getBoolean
                            (SQLColumnName.FLAT_DESCRIPTION_POSSIBLE_WITH_CHILD_COLUMN_NAME))
                    .buildPossibleWithPets
                            (resultSet.getBoolean(SQLColumnName.FLAT_DESCRIPTION_POSSIBLE_WITH_PETS_COLUMN_NAME))
                    .buildUsersDescription(resultSet.getString(SQLColumnName.FLAT_DESCRIPTION_USERS_DESCRIPTION_COLUMN_NAME))

                    .buildFlatDescription();
        } catch (SQLException e) {
          throw new DaoException("Building flats description from entity error",e);
        }
    }

    @Override
    public void closeConnection() {
        closeConnection(this.connection);
    }

    @Override
    public Optional<FlatDescription> findByRoomsAmount(int roomsAmount) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_FLAT_DESCRIPTION_BY_ID)){
            statement.setInt(1,roomsAmount);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by rooms amount error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<FlatDescription> findByLivingArea(float livingArea) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_FLAT_DESCRIPTION_BY_ID)){
            statement.setFloat(1,livingArea);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by living area error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<FlatDescription> findByRepairType(FlatRepairType repairType) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_FLAT_DESCRIPTION_BY_ID)){
            statement.setString(1,repairType.getRepairType());
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding flatDescription by living area error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }
}
