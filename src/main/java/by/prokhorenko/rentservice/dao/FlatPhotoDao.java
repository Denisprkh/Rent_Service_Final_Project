package by.prokhorenko.rentservice.dao;

import by.prokhorenko.rentservice.entity.FlatPhoto;
import by.prokhorenko.rentservice.exception.DaoException;

import java.util.List;

/**
 * FlatPhoto dao interface.
 */
public interface FlatPhotoDao extends CommonDao<FlatPhoto> {

    /**
     * Returns List of {@link FlatPhoto} found by flatsId.
     *
     * @param flatsId flats id which photos belong to
     * @return List of {@link FlatPhoto}
     * @throws DaoException if an SQLException occurs
     */
    List<FlatPhoto> findAllPhotosByFlatsId(int flatsId) throws DaoException;

    /**
     * Stores all {@link FlatPhoto} in database.
     *
     * @param flatPhotos List of {@link FlatPhoto}
     * @return true if successfully and vice versa
     * @throws DaoException if an SQLException occurs
     */
    boolean addAllPhotos(List<FlatPhoto> flatPhotos) throws DaoException;

    /**
     * Updates all {@link FlatPhoto} from List.
     *
     * @param flatPhotos List of {@link FlatPhoto}
     * @return List of {@link FlatPhoto} after updating
     * @throws DaoException if an SQLException occurs
     */
    List<FlatPhoto> UpdateAllFlatsPhotos(List<FlatPhoto> flatPhotos) throws DaoException;
}
