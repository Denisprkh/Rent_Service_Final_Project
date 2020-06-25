package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.dao.CommonDao;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserChoiceHandler;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;

public interface AdvertisementDao extends CommonDao<Advertisement> {
    List<Advertisement> findAllAdvertisementsByUsersId(int id) throws DaoException;
    List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceHandler userChoiceHandler) throws DaoException;
}
