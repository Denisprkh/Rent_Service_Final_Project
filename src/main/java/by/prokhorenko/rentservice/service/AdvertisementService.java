package by.prokhorenko.rentservice.service;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.AdvertisementDataHandler;
import by.prokhorenko.rentservice.entity.UserChoiceDataHandler;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;

/**
 * {@link Advertisement} service interface.
 */
public interface AdvertisementService {

    /**
     * Returns List of visible {@link Advertisement}.
     *
     * @param start start position of row in the table from which data is getting, needed for pagination
     * @param total amount of {@link Advertisement} to get
     * @return List of visible {@link Advertisement}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Advertisement> findAllAdvertisements(int start, int total) throws ServiceException;

    /**
     * Returns quantity of all visible {@link Advertisement}.
     *
     * @return {@code int} quantity
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    int findAdvertisementsQuantity() throws ServiceException;

    /**
     * Adds {@link Advertisement} to database.
     *
     * @param advertisement {@link Advertisement}
     * @return {@code true} if {@link Advertisement} was added and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean addAnAdvertisement(Advertisement advertisement) throws ServiceException;

    /**
     * Validates data for creating {@link Advertisement}.
     *
     * @param handler {@link AdvertisementDataHandler}
     * @return List of {@code Boolean}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Boolean> defineIncorrectData(AdvertisementDataHandler handler) throws ServiceException;

    /**
     * Finds {@link Advertisement} by id.
     *
     * @param id advertisements id
     * @return {@link Advertisement} if it found
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    Advertisement findAdvertisementById(int id) throws ServiceException;

    /**
     * Returns List of {@link Advertisement} found by authors id.
     *
     * @param usersId authors id
     * @return List of {@link Advertisement}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Advertisement> findAdvertisementsByUserId(int usersId) throws ServiceException;

    /**
     * Makes advertisement invisible.
     *
     * @param advertisementsId advertisements id
     * @return {@code true} if {@link Advertisement} was made invisible and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean deleteAdvertisement(int advertisementsId) throws ServiceException;

    /**
     * Returns {@link Advertisement} quantity filtered by users choice.
     *
     * @param userChoiceDataHandler {@link UserChoiceDataHandler} which contains {@code String} for building regex
     * @return {@code int} quantity
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    int findFilteredAdvertisementsQuantity(UserChoiceDataHandler userChoiceDataHandler) throws ServiceException;

    /**
     * Returns List of visible and not in rent {@link Advertisement} filtered by users choice.
     *
     * @param userChoiceDataHandler contains {@code String} for building regex
     * @param start                 start position of row in the table from which data is getting, needed for pagination
     * @param total                 amount of {@link Advertisement} to get
     * @return List of visible {@link Advertisement}
     * @throws ServiceException if @link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceDataHandler userChoiceDataHandler, int start, int total)
            throws ServiceException;

    /**
     * Updates {@link Advertisement}.
     *
     * @param advertisement {@link Advertisement}
     * @return {@code true} if {@link Advertisement} was updated and vice versa
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    boolean updateAdvertisement(Advertisement advertisement) throws ServiceException;

    /**
     * Returns {@link Advertisement} quantity which are not in rent.
     *
     * @return {@code int} quantity
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    int findNotRentedAdvertisementsQuantity() throws ServiceException;

    /**
     * Returns List of visible and not rented {@link Advertisement}.
     *
     * @param start start position of row in the table from which data is getting, needed for pagination
     * @param total amount of {@link Advertisement} to get
     * @return List of {@link Advertisement}
     * @throws ServiceException if {@link by.prokhorenko.rentservice.exception.DaoException} or IOException occurs
     */
    List<Advertisement> findAllNotRentedAdvertisements(int start, int total) throws ServiceException;
}
