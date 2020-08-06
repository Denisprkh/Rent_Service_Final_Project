package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.exception.DaoException;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;

/**
 * Common dao interface
 *
 * @param <T> some entity
 */
public interface CommonDao<T> extends Closeable, AutoCloseable {

    /**
     * Adds object and returns {@code Optional<T>} if successfully
     *
     * @param t
     * @return {@code Optional<T>}
     * @throws DaoException if an error occurs
     */
    Optional<T> add(T t) throws DaoException;

    /**
     * Returns List of {@code T} object
     *
     * @param start start position of row in the table from which data is getting, needed for pagination
     * @param total amount of {@code T} to get
     * @return List of {@code T}
     * @throws DaoException if an error occurs
     */
    List<T> findAll(int start, int total) throws DaoException;

    /**
     * Returns {@code Optional<T>} if object was found by id and {@code Optional.empty} if not
     *
     * @param id
     * @return {@code Optional<T>}
     * @throws DaoException if an error occurs
     */
    Optional<T> findById(int id) throws DaoException;

    /**
     * Returns {@code Optional<T>} after updating object
     *
     * @param t
     * @return {@code Optional<T>}
     * @throws DaoException if an error occurs
     */
    Optional<T> update(T t) throws DaoException;

    /**
     * Returns objects quantity
     *
     * @return
     * @throws DaoException
     */
    int findQuantity() throws DaoException;
}
