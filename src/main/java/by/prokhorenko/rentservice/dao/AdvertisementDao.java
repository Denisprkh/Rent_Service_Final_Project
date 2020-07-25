package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserChoiceDataHandler;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;

public interface AdvertisementDao extends CommonDao<Advertisement> {
    List<Advertisement> findAllAdvertisementsByUsersId(int id) throws DaoException;
    List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceDataHandler advertisementDataHandler) throws DaoException;
    boolean setAdvertisementStatusInvisible(int advertisementsId) throws DaoException;
}
