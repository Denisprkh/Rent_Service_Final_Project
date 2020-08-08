package by.prokhorenko.rentservice.service.impl;

import by.prokhorenko.rentservice.dao.FlatDao;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.FlatService;

import java.io.IOException;

/**
 * Implementation of {@link FlatService}.
 */
public class FlatServiceImpl implements FlatService {

    private static final FlatService INSTANCE = new FlatServiceImpl();
    private FlatServiceImpl(){}
    public static FlatService getInstance(){
        return INSTANCE;
    }

    @Override
    public boolean setFlatIsInRent(int flatsId) throws ServiceException{
        try(FlatDao flatDao = DaoFactory.getInstance().getFlatDao()){
            return flatDao.updateFlatFreeStatusFalse(flatsId);
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean setFlatIsNotInRent(int flatsId) throws ServiceException {
        try(FlatDao flatDao = DaoFactory.getInstance().getFlatDao()){
            return flatDao.updateFlatFreeStatusTrue(flatsId);
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
