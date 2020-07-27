package by.prokhorenko.rentservice.service.request.impl;

import by.prokhorenko.rentservice.dao.RequestDao;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.request.RequestService;

import java.io.IOException;
import java.util.List;

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

    @Override
    public List<Request> findAllUsersRequests(int usersId) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()){
            List<Request> usersRequests = requestDao.findRequestsByUsersId(usersId);
            return usersRequests;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean ApproveRequestById(int requestsId) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()) {
            boolean isApproved = requestDao.approveRequest(requestsId);
            return isApproved;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
