package by.prokhorenko.rentservice.dao.user;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.Optional;

public interface UserDao{

    boolean add(User user) throws DaoException;
    User findByEmailAndPassword(String email, String password) throws DaoException;
    User findByEmail(String email) throws DaoException;
    boolean ban(int id) throws DaoException;
    boolean unBan(int id) throws DaoException;
    boolean updateRole(int usersId, int roleId) throws DaoException;
    void closeConnection();
}
