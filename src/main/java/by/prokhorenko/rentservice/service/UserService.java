package by.prokhorenko.rentservice.service;

import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link User} service interface.
 */
public interface UserService {

    /**
     * Stores user in database.
     * @param user {@link User}
     * @return signedUp {@link User} with generatedId
     * @throws ServiceException if user wasn't added, {@link by.prokhorenko.rentservice.exception.DaoException} or
     * IOException occurs
     */
    User signUp(User user) throws ServiceException;

    /**
     * Returns {@link User} found by email and password.
     * @param email users email
     * @param password users password
     * @return user
     * @throws ServiceException if data doesn't matches regex, email or password is incorrect for signIn, {@link User}
     * is banned or not activated, {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    User signIn(String email, String password) throws ServiceException;

    /**
     * Finds all users.
     * @param start start position of row in the table from which data is getting, needed for pagination
     * @param total amount of {@link User} to get
     * @return List of {@link User}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<User> findAllUsers(int start,int total) throws ServiceException;

    /**
     * Checks data for building {@link User}.
     * @param email users email
     * @param firstName users first name
     * @param lastName users last name
     * @param password users password
     * @param phoneNumber users phone number
     * @return Map of key {@code String} name of validation parameter and of value {@code true} if parameter is correct
     * and {@code false} if not
     * @throws ServiceException if {@link ServiceException} occurs
     */
    Map<String,Boolean> defineUsersIncorrectData(String email, String firstName, String lastName, String password,
                                                 String phoneNumber) throws ServiceException;

    /**
     * Makes users banned status {@code true}.
     * @param id users id
     * @return {@code true} if status was changed and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean banUser(int id) throws  ServiceException;

    /**
     * Makes users banned status {@code false}.
     * @param id users id
     * @return {@code true} if status was changed and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean unBanUser(int id) throws ServiceException;

    /**
     * Finds {@link User} by id.
     * @param id users od
     * @return found {@link User}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    Optional<User> findUserById(int id) throws ServiceException;

    /**
     * Finds {@link User} by email.
     * @param email users email
     * @return found {@link User}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Finds {@link User} by phone.
     * @param phone users phone number
     * @return found {@link User}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    Optional<User> findUserByPhone(String phone) throws  ServiceException;

    /**
     * Makes users {@code isActivated} status {@code true}.
     * @param id
     * @return {@code true} if status was changed and {@code false} if not
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean activateUser(int id) throws ServiceException;

    /**
     * Updates {@link User}.
     * @param user with new data
     * @return updated {@link User}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    User updateUserInfo(User user) throws ServiceException;

    /**
     * Checks data for updating {@link User}.
     * @param email updated email
     * @param firstName updated first name
     * @param lastName updated last name
     * @param phoneNumber updated phone number
     * @param usersId users id
     * @return Map of key {@code String} name of validation parameter and of value {@code true} if parameter is correct
     * and {@code false} if not
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    Map<String,Boolean> defineUsersIncorrectDataForUpdate(String email, String firstName, String lastName,
                                                          String phoneNumber, int usersId) throws ServiceException;

    /**
     * Updates {@code userRoleId} to admin by  users id.
     * @param usersId whose role will be updated
     * @return {@code true} if role was updated and {@code false} if not
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean giveAdminRightsById(int usersId) throws ServiceException;

    /**
     * Updates {@code userRoleId} to user by  users id.
     * @param usersId whose role will be updated
     * @return {@code true} if role was updated and {@code false} if not
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean pickUpAdminRightsById(int usersId) throws ServiceException;

    /**
     * Finds all {@link User} quantity.
     * @return quantity
     * @throws ServiceException
     */
    int findUsersQuantity() throws ServiceException;

    /**
     * Checks is full name data correct to parse for update first name and last name.
     * @param fullName users first name and last name separated by space
     * @throws ServiceException if data is not correct
     */
    void fullNameIsCorrect(String fullName) throws ServiceException;
}
