package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.FlatBuilder;
import java.io.Serializable;
import java.util.List;

/**
 * Flat entity. Has next properties:
 * <b>id</b>,<b>isFree</b>,<b>flatDescription</b>,<b>flatAddress</b>,<b>flatPhotos</b>
 */

public class Flat implements Serializable {

    /**
     * Property - flats id
     */
    private int id;

    /**
     * Property - flats free status
     */
    private boolean isFree;

    /**
     * Property - flats description {@link FlatDescription}
     */
    private FlatDescription flatDescription;

    /**
     * Property - flats address {@link FlatAddress}
     */
    private FlatAddress flatAddress;

    /**
     * Property - list of {@link FlatPhoto} flatPhotos
     */
    List<FlatPhoto> flatPhotos;

    /**
     * Initializes a newly created {@code Flat} object with
     * fields which have been built with {@link FlatBuilder}
     *
     * @param flatBuilder
     */
    public Flat(FlatBuilder flatBuilder) {
        this.id = flatBuilder.getId();
        this.isFree = flatBuilder.isFree();
        this.flatDescription = flatBuilder.getFlatDescription();
        this.flatAddress = flatBuilder.getFlatAddress();
        this.flatPhotos = flatBuilder.getFlatPhotos();
    }

    /**
     * Initializes a newly created {@code Flat} object so that it represents
     * an empty Flat entity.
     */
    public Flat() {

    }

    /**
     * Returns flat id of an {@code Flat} object
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets flat id to an {@code Flat} object
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns whether flat is free of an {@code Flat} object
     *
     * @return true if the flat is free and vice versa
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     * Sets flat isFree status to an {@code Flat} object
     *
     * @param free
     */
    public void setFree(boolean free) {
        isFree = free;
    }


    /**
     * Returns {@link FlatDescription} flatDescription of an {@code Flat} object
     *
     * @return flatDescription
     */
    public FlatDescription getFlatDescription() {
        return flatDescription;
    }

    /**
     * Sets {@link FlatDescription} flatDescription to an {@code Flat} object
     *
     * @param flatDescription
     */
    public void setFlatDescription(FlatDescription flatDescription) {
        this.flatDescription = flatDescription;
    }

    /**
     * Returns {@link FlatAddress} flatAddress of an {@code Flat} object
     *
     * @return flatAddress
     */
    public FlatAddress getFlatAddress() {
        return flatAddress;
    }

    /**
     * Sets {@link FlatAddress} flatAddress to an {@code Flat} object
     *
     * @param flatAddress
     */
    public void setFlatAddress(FlatAddress flatAddress) {
        this.flatAddress = flatAddress;
    }


    /**
     * Returns list of {@link FlatPhoto} flats photos of an {@code Flat} object
     *
     * @return
     */
    public List<FlatPhoto> getFlatPhotos() {
        return flatPhotos;
    }

    /**
     * Sets list of {@link FlatPhoto} flats photos to an {@code Flat} object
     *
     * @param flatPhotos
     */
    public void setFlatPhotos(List<FlatPhoto> flatPhotos) {
        this.flatPhotos = flatPhotos;
    }

    /**
     * Compares this flat to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * Flat} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code Flat} against
     * @return {@code true} if the given object represents a {@code Flat}
     * equivalent to this flat, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;

        if (id != flat.id) return false;
        if (isFree != flat.isFree) return false;
        if (flatDescription != null ? !flatDescription.equals(flat.flatDescription) : flat.flatDescription != null)
            return false;
        if (flatAddress != null ? !flatAddress.equals(flat.flatAddress) : flat.flatAddress != null) return false;
        return flatPhotos != null ? flatPhotos.equals(flat.flatPhotos) : flat.flatPhotos == null;
    }

    /**
     * Returns a hash code for this {@code Flat} object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isFree ? 1 : 0);
        result = 31 * result + (flatDescription != null ? flatDescription.hashCode() : 0);
        result = 31 * result + (flatAddress != null ? flatAddress.hashCode() : 0);
        result = 31 * result + (flatPhotos != null ? flatPhotos.hashCode() : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code Flat} object
     *
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flat{");
        sb.append("id=").append(id);
        sb.append(", isFree=").append(isFree);
        sb.append(", flatDescription=").append(flatDescription);
        sb.append(", flatAddress=").append(flatAddress);
        sb.append(", flatPhotos=").append(flatPhotos);
        sb.append('}');
        return sb.toString();
    }
}
