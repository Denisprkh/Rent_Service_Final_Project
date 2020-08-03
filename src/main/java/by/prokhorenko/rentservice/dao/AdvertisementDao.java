package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.UserChoiceDataHandler;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;

public interface AdvertisementDao extends CommonDao<Advertisement> {

    List<Advertisement> findAllAdvertisementsByUsersId(int id) throws DaoException;

    List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceDataHandler advertisementDataHandler,
                                                        int start,int total) throws DaoException;
    boolean setAdvertisementStatusInvisible(int advertisementsId) throws DaoException;
    int findFilteredAdvertisementsQuantity(UserChoiceDataHandler dataHandler) throws DaoException;
    List<Advertisement> findAllNotRentedAdvertisements(int start, int total) throws DaoException;
    int findNotInRentAdvertisementsQuantity() throws DaoException;
}

