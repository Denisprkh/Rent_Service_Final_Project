package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.dao.CommonDao;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.entity.flat.FlatRepairType;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface FlatDescriptionDao extends CommonDao<FlatDescription> {
    List<FlatDescription> findByRoomsAmount(int roomsAmount) throws DaoException;
    List<FlatDescription> findByLivingArea(float livingArea) throws DaoException;
    List<FlatDescription> findByRepairType(String repai) throws DaoException;
}
