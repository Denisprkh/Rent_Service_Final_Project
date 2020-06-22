package by.prokhorenko.rentservice.dao.impl;

import by.prokhorenko.rentservice.builder.UserBuilder;
import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.constant.SqlColumnName;
import by.prokhorenko.rentservice.dao.constant.SqlQuery;
import by.prokhorenko.rentservice.dao.UserDao;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.entity.user.UserRole;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl extends AbstractCommonDao implements UserDao {

    private static final Logger LOG = LogManager.getLogger();
    private static final UserDao INSTANCE = new UserDaoImpl();
    private UserDaoImpl(){
        this.connection = ConnectionPool.INSTANCE.getConnection();
    }
    public static UserDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<User> add(User user) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_USER)){
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            statement.setString(5,user.getPhone());
            int id = executeUpdateAndGetGeneratedId(statement);
            user.setId(id);
            return Optional.of(user);
        }catch (SQLException e) {
            throw new DaoException("User has not been added",e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> allUsers;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery()){
                    allUsers = new ArrayList<>();
                    while (resultSet.next()) {
                        allUsers.add(buildEntityFromResultSet(resultSet));
                }
        } catch (SQLException e) {
            throw new DaoException("Finding all users error",e);
        }
        return allUsers;
    }

    @Override
    public Optional<User> findById(int id) throws DaoException{
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException  e) {
            throw new DaoException("Finding user by id error", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<User> update(User user) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_BY_ID)) {
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            statement.setString(5,user.getPhone());
            statement.setInt(6,user.getId());
            statement.executeUpdate();
            return findById(user.getId());
        } catch (SQLException e) {
            throw new DaoException("Updating user error",e);
        }
    }


    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_USER_BY_EMAIL_AND_PASSWORD)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding user by password and email error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding user by email error", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean ban(int id) throws DaoException {
      return updateEntityById(id, SqlQuery.UPDATE_USERS_BAN_STATUS_TRUE);
    }

    @Override
    public boolean unBan(int id) throws DaoException {
        return updateEntityById(id, SqlQuery.UPDATE_USERS_BAN_STATUS_FALSE);
    }

    @Override
    public boolean updateRole(int usersId, int roleId) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USERS_ROLE)){
            statement.setInt(1, roleId);
            statement.setInt(2, usersId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Updating users role error",e);
        }
    }

    @Override
    public void close() {
        closeConnection(this.connection);
        LOG.info("UserDao was closed");
    }

    @Override
    public Optional<User> findByPhone(String phone) throws DaoException {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_PHONE)) {
            statement.setString(1,phone);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Finding user by phone error",e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public User buildEntityFromResultSet(ResultSet resultSet) throws DaoException {

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
}
