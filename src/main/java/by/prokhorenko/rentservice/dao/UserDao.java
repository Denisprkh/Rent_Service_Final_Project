package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.Optional;

public interface UserDao extends CommonDao<User> {
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    boolean banUser(int id) throws DaoException;
    boolean unBanUser(int id) throws DaoException;
    boolean activateUser(int id) throws DaoException;
    boolean updateRole(int usersId, int roleId) throws DaoException;
    Optional<User> findByPhone(String phone) throws DaoException;
}
