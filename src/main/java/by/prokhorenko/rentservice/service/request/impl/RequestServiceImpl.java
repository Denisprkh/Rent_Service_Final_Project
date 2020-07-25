package by.prokhorenko.rentservice.service.request.impl;

import by.prokhorenko.rentservice.dao.RequestDao;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.request.RequestService;

import java.io.IOException;

public class RequestServiceImpl implements RequestService {

    private static final RequestService INSTANCE = new RequestServiceImpl();
    private RequestServiceImpl(){}
    public static RequestService getInstance(){
        return INSTANCE;
    }

    @Override
    public boolean addRequest(Request request) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()){
            return requestDao.add(request).isPresent();
        } catch (IOException | DaoException e) {
            throw new ServiceException("Adding request error",e);
        }
    }
}
