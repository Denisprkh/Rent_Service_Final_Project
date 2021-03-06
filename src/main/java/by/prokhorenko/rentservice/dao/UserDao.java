package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.Optional;

/**
 * User dao interface.
 */
public interface UserDao extends CommonDao<User> {

    /**
     * Returns {@code Optional<User>} found by email and password.
     *
     * @param email users email
     * @param password users password
     * @return {@code Optional<User>}
     * @throws DaoException if an SQLException occurs
     */
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Returns {@code Optional<User>} found by email.
     *
     * @param email users email
     * @return {@code Optional<User>}
     * @throws DaoException if an SQLException occurs
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Updates {@link User} ban status {@code true}.
     *
     * @param id users id
     * @return {@code true} if status is updated successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean banUser(int id) throws DaoException;

    /**
     * Updates {@link User} ban status {@code false}.
     *
     * @param id users id
     * @return {@code true} if status is updated successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean unBanUser(int id) throws DaoException;

    /**
     * Updates {@link User} activation status {@code true}.
     *
     * @param id users id
     * @return {@code true} if status is updated successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean activateUser(int id) throws DaoException;

    /**
     * Update {@link User} userRole by id.
     *
     * @param usersId users id
     * @param roleId users role id
     * @return {@code true} if status is updated successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean updateRole(int usersId, int roleId) throws DaoException;

    /**
     * Returns {@code Optional<User>} found by phone.
     *
     * @param phone users phone number
     * @return {@code Optional<User>}
     * @throws DaoException if an SQLException occurs
     */
    Optional<User> findByPhone(String phone) throws DaoException;

}
