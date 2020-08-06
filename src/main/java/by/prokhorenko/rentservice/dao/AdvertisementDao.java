package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.UserChoiceDataHandler;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;

/**
 * Advertisement dao interface
 */
public interface AdvertisementDao extends CommonDao<Advertisement> {

    /**
     * Returns List of {@link Advertisement} by authors id
     *
     * @param id
     * @return List of {@link Advertisement}
     * @throws DaoException if an SQLException occurs
     */
    List<Advertisement> findAllAdvertisementsByUsersId(int id) throws DaoException;

    /**
     * Returns List of {@link Advertisement} filtered by users choice,
     *
     * @param advertisementDataHandler has {@code String} for building regex for search
     * @param start                    start position of row in the table from which data is getting,
     *                                needed for pagination
     * @param total                    amount of {@link Advertisement} to get
     * @return List of {@link Advertisement}
     * @throws DaoException if an SQLException occurs
     */
    List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceDataHandler advertisementDataHandler,
                                                        int start, int total) throws DaoException;

    /**
     * Sets advertisement status invisible to delete it from wep pages
     *
     * @param advertisementsId
     * @return boolean true if successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean setAdvertisementStatusInvisible(int advertisementsId) throws DaoException;

    /**
     * Returns {@link Advertisement} quantity, which match to users choice
     *
     * @param dataHandler
     * @return quantity of {@link Advertisement}
     * @throws DaoException if an SQLException occurs
     */
    int findFilteredAdvertisementsQuantity(UserChoiceDataHandler dataHandler) throws DaoException;

    /**
     * Returns List of {@link Advertisement}, which are not in rent
     *
     * @param start start position of row in the table from which data is getting, needed for pagination
     * @param total amount of {@link Advertisement} to get
     * @return List of {@link Advertisement}
     * @throws DaoException if an SQLException occurs
     */
    List<Advertisement> findAllNotRentedAdvertisements(int start, int total) throws DaoException;

    /**
     * Returns quantity of not rented {@link Advertisement}
     *
     * @return quantity
     * @throws DaoException if an SQLException occurs
     */
    int findNotInRentAdvertisementsQuantity() throws DaoException;
}

