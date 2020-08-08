package by.prokhorenko.rentservice.dao;


import by.prokhorenko.rentservice.entity.Flat;
import by.prokhorenko.rentservice.exception.DaoException;

/**
 * Flat dao interface.
 */
public interface FlatDao extends CommonDao<Flat> {
    /**
     * Sets {@link Flat} isFree status {@code false} by id.
     *
     * @param flatsId flats id
     * @return boolean true if status was updated and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean updateFlatFreeStatusFalse(int flatsId) throws DaoException;

    /**
     * Sets {@link Flat} isFree status {@code true} by id.
     *
     * @param flatsId flats id
     * @return boolean true if status was updated and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean updateFlatFreeStatusTrue(int flatsId) throws DaoException;
}
