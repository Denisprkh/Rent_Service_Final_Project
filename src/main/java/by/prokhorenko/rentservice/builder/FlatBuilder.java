package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.Flat;
import by.prokhorenko.rentservice.entity.FlatAddress;
import by.prokhorenko.rentservice.entity.FlatDescription;
import by.prokhorenko.rentservice.entity.FlatPhoto;

import java.util.List;

/**
 * Class for building {@link Flat}
 */
public class FlatBuilder {

    /**
     * Id
     */
    private int id;

    /**
     * Flat status
     */
    private boolean isFree;

    /**
     * Flat description
     */
    private FlatDescription flatDescription;

    /**
     * Flat address
     */
    private FlatAddress flatAddress;

    /**
     * Flat photos
     */
    private List<FlatPhoto> flatPhotos;

    /**
     * Initializes a newly created {@code FlatBuilder} object so that it represents
     * an empty FlatBuilder entity.
     */
    public FlatBuilder() {

    }

    /**
     * Returns id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id and returns {@code FlatBuilder} object with built field
     *
     * @param id
     * @return {@see FlatBuilder}
     */
    public FlatBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns flat free status
     *
     * @return isFree
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     * Sets flats status and returns {@code FlatBuilder} object with built field
     *
     * @param free
     * @return {@see FlatBuilder}
     */
    public FlatBuilder buildIsFree(boolean free) {
        isFree = free;
        return this;
    }

    /**
     * Returns all flatPhotos
     *
     * @return flatPhotos
     */
    public List<FlatPhoto> getFlatPhotos() {
        return flatPhotos;
    }

    /**
     * Sets flats photos and returns {@code FlatBuilder} object with built field
     *
     * @param flatPhotos
     * @return {@see FlatBuilder}
     */
    public FlatBuilder buildFlatPhotos(List<FlatPhoto> flatPhotos) {
        this.flatPhotos = flatPhotos;
        return this;
    }

    /**
     * Returns flats description
     *
     * @return flatDescription
     */
    public FlatDescription getFlatDescription() {
        return flatDescription;
    }

    /**
     * Sets flats description and returns {@code FlatBuilder} object with built field
     *
     * @param flatDescription
     * @return {@see FlatBuilder}
     */
    public FlatBuilder buildFlatDescription(FlatDescription flatDescription) {
        this.flatDescription = flatDescription;
        return this;
    }

    /**
     * Returns flats address
     *
     * @return flatAddress
     */
    public FlatAddress getFlatAddress() {
        return flatAddress;
    }

    /**
     * Sets flats address and returns {@code FlatBuilder} object with built field
     *
     * @param flatAddress
     * @return {@see FlatBuilder}
     */
    public FlatBuilder buildFlatAddress(FlatAddress flatAddress) {
        this.flatAddress = flatAddress;
        return this;
    }

    /**
     * Returns newly created {@link Flat} with fields, built in builder
     *
     * @return {@see Flat}
     */
    public Flat buildFlat() {
        return new Flat(this);
    }
}
