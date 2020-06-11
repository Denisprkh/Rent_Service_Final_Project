package by.prokhorenko.rentservice.dao.flat;

import by.prokhorenko.rentservice.dao.CommonDao;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.exception.DaoException;

import javax.mail.Address;

public interface FlatAddressDao extends CommonDao<FlatAddress> {

    FlatAddress getFlatAddressByFullData(String city, String district, String street, String house) throws DaoException;
}
