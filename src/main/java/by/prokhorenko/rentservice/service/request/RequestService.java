package by.prokhorenko.rentservice.service.request;

import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.ServiceException;

public interface RequestService {
    boolean addRequest(Request request) throws ServiceException;
}
