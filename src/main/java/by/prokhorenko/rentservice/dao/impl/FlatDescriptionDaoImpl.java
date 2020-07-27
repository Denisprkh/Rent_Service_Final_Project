package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.FlatDescriptionDao;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
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

    private FlatDescriptionDaoImpl() {
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }

    public static FlatDescriptionDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<FlatDescription> add(FlatDescription flatDescription) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_FLAT_DESCRIPTION,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, flatDescription.getRooms());
            statement.setFloat(2, flatDescription.getLivingArea());
            statement.setBoolean(3, flatDescription.isHasFurniture());
            statement.setBoolean(4, flatDescription.isHasHomeAppliances());
            statement.setBoolean(5, flatDescription.isHasTheInternet());
            statement.setBoolean(6, flatDescription.isPossibleWithChild());
            statement.setBoolean(7, flatDescription.isPossibleWithPets());
            statement.setString(8, flatDescription.getUsersDescription());
            int id = executeUpdateAndGetGeneratedId(statement);
            flatDescription.setId(id);
            return Optional.of(flatDescription);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FlatDescription> findAll(int start, int total) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_FLAT_DESCRIPTIONS);
             ResultSet resultSet = statement.executeQuery()) {
            List<FlatDescription> flatDescriptions = new ArrayList<>();
            while (resultSet.next()) {
                flatDescriptions.add(buildFlatDescriptionFromResultSet(resultSet));
            }
            return flatDescriptions;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<FlatDescription> findById(int id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FLAT_DESCRIPTION_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildFlatDescriptionFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<FlatDescription> update(FlatDescription flatDescription) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_FLAT_DESCRIPTION_BY_ID)) {
            statement.setInt(1, flatDescription.getRooms());
            statement.setFloat(2, flatDescription.getLivingArea());
            statement.setBoolean(3, flatDescription.isHasFurniture());
            statement.setBoolean(4, flatDescription.isHasHomeAppliances());
            statement.setBoolean(5, flatDescription.isHasTheInternet());
            statement.setBoolean(6, flatDescription.isPossibleWithChild());
            statement.setBoolean(7, flatDescription.isPossibleWithPets());
            statement.setString(8, flatDescription.getUsersDescription());
            statement.setInt(9, flatDescription.getId());
            updateEntity(statement);
            return findById(flatDescription.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
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
