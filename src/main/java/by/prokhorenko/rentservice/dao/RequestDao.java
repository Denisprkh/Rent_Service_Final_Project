package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;

public interface RequestDao extends CommonDao<Request> {
    List<Request> findRequestsByUsersId(int usersId) throws DaoException;
    boolean approveRequest(int requestsId) throws DaoException;
    List<Request> findRequestsByAdvertisementsId(int advertisementsId) throws DaoException;
}
