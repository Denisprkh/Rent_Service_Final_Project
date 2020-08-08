package by.prokhorenko.rentservice.service;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;

/**
 * {@link Request} service interface.
 */
public interface RequestService {

    /**
     * Stores {@link Request} in database.
     *
     * @param request {@link Request}
     * @return {@code true} if request was added
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean addRequest(Request request) throws ServiceException;

    /**
     * Returns List of {@link Request} found by {@code usersId}.
     *
     * @param usersId id of user who sent a request
     * @return List of {@link Request}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Request> findAllUsersRequests(int usersId) throws ServiceException;

    /**
     * Sets {@link Request} approve status {@code true}.
     *
     * @param requestsId requests id
     * @return {@code true} if status is updated and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean approveRequestById(int requestsId) throws ServiceException;

    /**
     * Sets {@link Request} approve status {@code false}.
     *
     * @param requestsId requests id
     * @return {@code true} if status is updated and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean disApproveRequestById(int requestsId) throws ServiceException;

    /**
     * Returns List of {@link Request} found by {@link Advertisement} authors id.
     *
     * @param usersId id of the author of the advertisement for which the application is being submitted
     * @return List of {@link Request}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Request> findRequestsOnUsersAdvertisement(int usersId) throws ServiceException;

    /**
     * Returns List of all {@link Request}.
     *
     * @param start start position of row in the table from which data is getting, needed for pagination
     * @param total amount of {@link Request} to get
     * @return List of {@link Request}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Request> findAllRequests(int start, int total) throws ServiceException;

    /**
     * Returns {@link Request} quantity.
     *
     * @return {@code int} quantity
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    int findAllRequestsQuantity() throws ServiceException;

    /**
     *Finds {@link Request} by id.
     * @param requestsId requests id
     * @return found {@link Request}
     * @throws ServiceException if request wasn't found, {@link by.prokhorenko.rentservice.exception.DaoException} or
     * IOException occurs
     */
    Request findRequestById(int requestsId) throws ServiceException;

    /**
     * Checks data for building rent date is correct.
     * @param data contains rent date in {@code String} format
     * @throws ServiceException if data is not correct
     */
    void checkDataForRentDateIsCorrect(String data) throws ServiceException;
}
