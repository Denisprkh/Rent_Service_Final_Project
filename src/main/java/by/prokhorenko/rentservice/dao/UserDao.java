package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.dao.AbstractCommonDao;
import by.prokhorenko.rentservice.dao.CommonDao;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.Optional;

public interface UserDao extends CommonDao<User> {
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException; //make return optional
    Optional<User> findByEmail(String email) throws DaoException; //make return optional
    boolean ban(int id) throws DaoException;
    boolean unBan(int id) throws DaoException;
    boolean updateRole(int usersId, int roleId) throws DaoException;
    Optional<User> findByPhone(String phone) throws DaoException; //make return optional
}
