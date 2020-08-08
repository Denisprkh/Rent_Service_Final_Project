package by.prokhorenko.rentservice.service.impl;

import by.prokhorenko.rentservice.controller.command.ResourceBundleMessageKey;
import by.prokhorenko.rentservice.dao.RequestDao;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.RequestService;
import by.prokhorenko.rentservice.validator.RequestValidator;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of {@link RequestService}.
 */
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
            throw new ServiceException(e);
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
    public boolean approveRequestById(int requestsId) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()) {
            boolean isApproved = requestDao.approveRequest(requestsId);
            return isApproved;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean disApproveRequestById(int requestsId) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()){
            boolean isDisApproved = requestDao.disApproveRequest(requestsId);
            return isDisApproved;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Request> findRequestsOnUsersAdvertisement(int usersId) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()){
            List<Request> requestsOnAdvertisement = requestDao.findRequestsByAdvertisementsAuthorId(usersId);
            return requestsOnAdvertisement;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Request> findAllRequests(int start, int total) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()){
            List<Request> allRequests = requestDao.findAll(start,total);
            return allRequests;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findAllRequestsQuantity() throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()){
            int allRequestsQuantity = requestDao.findQuantity();
            return allRequestsQuantity;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Request findRequestById(int requestsId) throws ServiceException {
        try(RequestDao requestDao = DaoFactory.getInstance().getRequestDao()){
            Request request = requestDao.findById(requestsId).orElseThrow(ServiceException::new);
            return request;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void checkDataForRentDateIsCorrect(String data) throws ServiceException {
        RequestValidator requestValidator = RequestValidator.getInstance();
        if(!requestValidator.dataForDateIsCorrect(data)){
            throw new ServiceException(ResourceBundleMessageKey.INVALID_DATE_FORMAT);
        }
    }
}
