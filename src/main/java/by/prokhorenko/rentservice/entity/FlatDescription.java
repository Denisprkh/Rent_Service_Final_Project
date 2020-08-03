package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.FlatAddressBuilder;
import by.prokhorenko.rentservice.builder.FlatDescriptionBuilder;

import java.io.Serializable;

/**
 * FlatDescription entity. Has next properties:
 * <b>id</b>,<b>rooms</b>,<b>livingArea</b>,<b>hsFurniture</b>,<b>hasHomeAppliances</b>,
 * <b>hasTheInternet</b>,<b>possibleWithChild</b>,<b>possibleWithPets</b>,<b>usersDescription</b>
 */
public class FlatDescription implements Serializable {

    /**
     * Property - flat descriptions id
     */
    private int id;

    /**
     * Property - rooms represents rooms amount of {@code FlatDescription} object
     */
    private int rooms;

    /**
     * Property - livingArea represents livingArea of {@code FlatDescription} object
     */
    private float livingArea;

    /**
     * Property - hasFurniture represents whether {@code FlatDescription} object has furniture
     */
    private boolean hasFurniture;

    /**
     * Property - hasHomeAppliances represents whether {@code FlatDescription} object has home appliances
     */
    private boolean hasHomeAppliances;

    /**
     * Property - hasTheInternet represents whether {@code FlatDescription} object has the Internet
     */
    private boolean hasTheInternet;

    /**
     * Property - possibleWithChild represents whether is it possible to live with child
     */
    private boolean possibleWithChild;


    /**
     * Property - possibleWithPets represents whether is it possible to live with pets
     */
    private boolean possibleWithPets;

    /**
     * Property - usersDescription, is a description of flat that user gives himself
     */
    private String usersDescription;

    /**
     * Initializes a newly created {@code FlatDescription} object with
     * fields which have been built with {@link FlatDescriptionBuilder}
     *
     * @param flatDescriptionBuilder
     */
    public FlatDescription(FlatDescriptionBuilder flatDescriptionBuilder) {
        this.id = flatDescriptionBuilder.getId();
        this.rooms = flatDescriptionBuilder.getRooms();
        this.livingArea = flatDescriptionBuilder.getLivingArea();
        this.hasFurniture = flatDescriptionBuilder.isHasFurniture();
        this.hasHomeAppliances = flatDescriptionBuilder.isHasHomeAppliances();
        this.hasTheInternet = flatDescriptionBuilder.isHasTheInternet();
        this.possibleWithChild = flatDescriptionBuilder.isPossibleWithChild();
        this.possibleWithPets = flatDescriptionBuilder.isPossibleWithPets();
        this.usersDescription = flatDescriptionBuilder.getUsersDescription();
    }

    /**
     * Initializes a newly created {@code User} object so that it represents
     * an empty User entity.
     */
    public FlatDescription() {

    }


    /**
     * Returns flat descriptions id of an {@code FlatDescription} object
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets flat descriptions id to an {@code FlatDescription} object
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns flat descriptions rooms of an {@code FlatDescription} object
     *
     * @return rooms
     */
    public int getRooms() {
        return rooms;
    }

    /**
     * Sets flat descriptions rooms to an {@code FlatDescription} object
     *
     * @param rooms
     */
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    /**
     * Returns flat descriptions livingArea of an {@code FlatDescription} object
     *
     * @return livingArea
     */
    public float getLivingArea() {
        return livingArea;
    }

    /**
     * Sets flat descriptions livingArea to an {@code FlatDescription} object
     *
     * @param livingArea
     */
    public void setLivingArea(float livingArea) {
        this.livingArea = livingArea;
    }

    /**
     * Returns whether flat description hasFurniture to an {@code FlatDescription} object
     *
     * @return hasFurniture
     */
    public boolean isHasFurniture() {
        return hasFurniture;
    }

    /**
     * Sets whether flat description has furniture to an {@code FlatDescription} object
     *
     * @param hasFurniture
     */
    public void setHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
    }

    /**
     * Returns whether flat description has home appliacnes to an {@code FlatDescription} object
     *
     * @return hasHomeAppliances
     */
    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    /**
     * Sets whether flat description has home appliances to an {@code FlatDescription} object
     *
     * @param hasHomeAppliances
     */
    public void setHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
    }

    /**
     * Returns whether flat description hasHomeAppliances of the {@code FlatDescription} object
     *
     * @return hasHomeAppliances
     */
    public boolean isHasTheInternet() {
        return hasTheInternet;
    }

    /**
     * Sets whether flat description hasTheInternet to an {@code FlatDescription} object
     *
     * @param hasTheInternet
     */
    public void setHasTheInternet(boolean hasTheInternet) {
        this.hasTheInternet = hasTheInternet;
    }

    /**
     * Returns  flat description possibleWithChild of the {@code FlatDescription} object
     *
     * @return possibleWithChild
     */
    public boolean isPossibleWithChild() {
        return possibleWithChild;
    }

    /**
     * Sets flat description possibleWithChild to an {@code FlatDescription} object
     *
     * @param possibleWithChild
     */
    public void setPossibleWithChild(boolean possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
    }

    /**
     * Returns  flat description possibleWithPets of the {@code FlatDescription} object
     *
     * @return possibleWithPets
     */
    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    /**
     * Sets flat description possibleWithPets to an {@code FlatDescription} object
     *
     * @param possibleWithPets
     */
    public void setPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
    }

    /**
     * Returns  flat description usersDescription of the {@code FlatDescription} object
     *
     * @return usersDescription
     */
    public String getUsersDescription() {
        return usersDescription;
    }

    /**
     * Sets flat description usersDescription to an {@code FlatDescription} object
     *
     * @param usersDescription
     */
    public void setUsersDescription(String usersDescription) {
        this.usersDescription = usersDescription;
    }

    /**
     * Compares this flatDescription to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * FlatDescription} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code Flat} against
     * @return {@code true} if the given object represents a {@code FlatDescription}
     * equivalent to this flatDescription, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatDescription that = (FlatDescription) o;

        if (id != that.id) return false;
        if (rooms != that.rooms) return false;
        if (Float.compare(that.livingArea, livingArea) != 0) return false;
        if (hasFurniture != that.hasFurniture) return false;
        if (hasHomeAppliances != that.hasHomeAppliances) return false;
        if (hasTheInternet != that.hasTheInternet) return false;
        if (possibleWithChild != that.possibleWithChild) return false;
        if (possibleWithPets != that.possibleWithPets) return false;
        return usersDescription != null ? usersDescription.equals(that.usersDescription) : that.usersDescription == null;
    }

    /**
     * Returns a hash code for this {@code FlatDescription} object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rooms;
        result = 31 * result + (livingArea != +0.0f ? Float.floatToIntBits(livingArea) : 0);
        result = 31 * result + (hasFurniture ? 1 : 0);
        result = 31 * result + (hasHomeAppliances ? 1 : 0);
        result = 31 * result + (hasTheInternet ? 1 : 0);
        result = 31 * result + (possibleWithChild ? 1 : 0);
        result = 31 * result + (possibleWithPets ? 1 : 0);
        result = 31 * result + (usersDescription != null ? usersDescription.hashCode() : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code FlatDescription} object
     *
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlatDescription{");
        sb.append("id=").append(id);
        sb.append(", rooms=").append(rooms);
        sb.append(", livingArea=").append(livingArea);
        sb.append(", hasFurniture=").append(hasFurniture);
        sb.append(", hasHomeAppliances=").append(hasHomeAppliances);
        sb.append(", hasTheInternet=").append(hasTheInternet);
        sb.append(", possibleWithChild=").append(possibleWithChild);
        sb.append(", possibleWithPets=").append(possibleWithPets);
        sb.append(", usersDescription='").append(usersDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
