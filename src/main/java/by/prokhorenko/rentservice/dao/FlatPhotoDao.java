package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.dao.CommonDao;
import by.prokhorenko.rentservice.entity.flat.FlatPhoto;
import by.prokhorenko.rentservice.exception.DaoException;

import java.io.File;
import java.util.List;

public interface FlatPhotoDao extends CommonDao<FlatPhoto>{
    List<FlatPhoto> findAllPhotosByFlatsId(int flatsId) throws DaoException;
    boolean addAllPhotos(List<FlatPhoto> flatPhotos) throws  DaoException;
}
