package by.prokhorenko.rentservice.service;

import by.prokhorenko.rentservice.entity.Flat;
import by.prokhorenko.rentservice.exception.ServiceException;

/**
 * {@link Flat} service interface.
 */
public interface FlatService {
    /**
     * Makes {@link Flat} free status {@code false}, so that advertisement.
     * becomes invisible
     *
     * @param flatsId flats id
     * @return {@code true} if status was updated and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean setFlatIsInRent(int flatsId) throws ServiceException;

    /**
     * Makes {@link Flat} free status {@code true}, so that advertisement.
     * becomes visible
     *
     * @param flatsId flats id
     * @return {@code true} if status was updated and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean setFlatIsNotInRent(int flatsId) throws ServiceException;
}
