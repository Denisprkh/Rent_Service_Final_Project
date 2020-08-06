package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;

/**
 * Request dao interface
 */
public interface RequestDao extends CommonDao<Request> {

    /**
     * Returns List of {@link Request} found by users id
     *
     * @param usersId
     * @return List of {@link Request}
     * @throws DaoException if an SQLException occurs
     */
    List<Request> findRequestsByUsersId(int usersId) throws DaoException;

    /**
     * Sets approve status of {@link Request} object {@code true}
     *
     * @param requestsId
     * @return {@code true} if status is updated successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean approveRequest(int requestsId) throws DaoException;

    /**
     * Sets approve status of {@link Request} object {@code false}
     *
     * @param requestsId
     * @return {@code true} if  status is updated successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean disApproveRequest(int requestsId) throws DaoException;

    /**
     * Returns List of {@link Request} sent for an {@link Advertisement} object
     *
     * @param authorId
     * @return List of {@link Request}
     * @throws DaoException if an SQLException occurs
     */
    List<Request> findRequestsByAdvertisementsAuthorId(int authorId) throws DaoException;
}
