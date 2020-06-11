package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface CommonDao<T> {
    boolean add(T object) throws DaoException;
    List<T> findAll() throws DaoException;
    T findById(int id) throws DaoException;
    T update(T t) throws DaoException;
    T buildEntityFromResultSet(ResultSet resultSet) throws DaoException;
    void closeConnection();
}
