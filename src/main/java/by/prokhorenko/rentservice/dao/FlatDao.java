package by.prokhorenko.rentservice.dao;


import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.exception.DaoException;


public interface FlatDao extends CommonDao<Flat> {
    boolean updateFlatFreeStatusFalse(int flatsId) throws DaoException;
    boolean updateFlatFreeStatusTrue(int flatsId) throws DaoException;
}
