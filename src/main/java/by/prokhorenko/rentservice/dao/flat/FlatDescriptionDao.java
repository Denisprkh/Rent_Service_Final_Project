package by.prokhorenko.rentservice.dao.flat;

import by.prokhorenko.rentservice.dao.CommonDao;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.entity.flat.FlatRepairType;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.Optional;

public interface FlatDescriptionDao extends CommonDao<FlatDescription> {
    Optional<FlatDescription> findByRoomsAmount(int roomsAmount) throws DaoException;
    Optional<FlatDescription> findByLivingArea(float livingArea) throws DaoException;
    Optional<FlatDescription> findByRepairType(FlatRepairType repairType) throws DaoException;
}
