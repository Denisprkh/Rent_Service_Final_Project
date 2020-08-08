package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.FlatPhoto;

import java.io.InputStream;

/**
 * Class for building {@link FlatPhoto} entity.
 */
public class FlatPhotoBuilder {

    /**
     * Id.
     */
    private int id;

    /**
     * Flats id.
     */
    private int flatsId;

    /**
     * Data which photo consists of.
     */
    private InputStream flatPhotoData;

    /**
     * Initializes a newly created {@code FlatPhotoBuilder} object so that it represents
     * an empty FlatPhotoBuilder entity.
     */
    public FlatPhotoBuilder() {
    }

    /**
     * Returns id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id and returns {@code FlatPhotoBuilder} object with built field.
     *
     * @param id photos id
     * @return {@see FlatPhotoBuilder}
     */
    public FlatPhotoBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns flats id.
     *
     * @return flatsId
     */
    public int getFlatsId() {
        return flatsId;
    }

    /**
     * Sets flats id and returns {@code FlatPhotoBuilder} object with built field.
     *
     * @param flatsId flats id
     * @return {@see FlatPhotoBuilder}
     */
    public FlatPhotoBuilder buildFlatsId(int flatsId) {
        this.flatsId = flatsId;
        return this;
    }

    /**
     * Returns data which photo consists of.
     *
     * @return flatPhotoData
     */
    public InputStream getFlatPhotoData() {
        return flatPhotoData;
    }

    /**
     * Sets photo data and returns {@code FlatPhotoBuilder} object with built field.
     *
     * @param flatPhotoData inputStream
     * @return {@see FlatPhotoBuilder}
     */
    public FlatPhotoBuilder buildFlatPhotoData(InputStream flatPhotoData) {
        this.flatPhotoData = flatPhotoData;
        return this;
    }

    /**
     * Returns newly created {@link FlatPhoto} with fields, built in builder.
     *
     * @return {@see FlatPhoto}
     */
    public FlatPhoto buildFlatPhoto() {
        return new FlatPhoto(this);
    }
}
