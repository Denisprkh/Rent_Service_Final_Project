package by.prokhorenko.rentservice.service.request;

import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;

public interface RequestService {
    boolean addRequest(Request request) throws ServiceException;
    List<Request> findAllUsersRequests(int usersId) throws  ServiceException;
    boolean ApproveRequestById(int requestsId) throws ServiceException;
}
