package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface CommonDao<T> extends AutoCloseable{
    Optional<T> add(T t) throws DaoException;
    List<T> findAll() throws DaoException;
    Optional<T> findById(int id) throws DaoException;
    Optional<T> update(T t) throws DaoException;
    T buildEntityFromResultSet(ResultSet resultSet) throws DaoException;
}
