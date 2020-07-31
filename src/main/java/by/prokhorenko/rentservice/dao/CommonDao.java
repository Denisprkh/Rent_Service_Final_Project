package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.exception.DaoException;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;

public interface CommonDao<T> extends AutoCloseable, Closeable{
    Optional<T> add(T t) throws DaoException;
    List<T> findAll(int start, int total) throws DaoException;
    Optional<T> findById(int id) throws DaoException;
    Optional<T> update(T t) throws DaoException;
    int findQuantity() throws DaoException;
}
