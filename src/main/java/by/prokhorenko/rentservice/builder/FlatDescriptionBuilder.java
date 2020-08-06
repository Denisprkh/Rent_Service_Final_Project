package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.FlatDescription;
import by.prokhorenko.rentservice.entity.FlatPhoto;

/**
 * Class for building {@link FlatDescription} entity
 */
public class FlatDescriptionBuilder {

    /**
     * Id
     */
    private int id;

    /**
     * Rooms
     */
    private int rooms;

    /**
     * Living area
     */
    private float livingArea;

    /**
     * Whether has furniture
     */
    private boolean hasFurniture;

    /**
     * Whether has home appliances
     */
    private boolean hasHomeAppliances;

    /**
     * Whether has the Internet
     */
    private boolean hasTheInternet;

    /**
     * Whether possible with child
     */
    private boolean possibleWithChild;

    /**
     * Whether possible with pets
     */
    private boolean possibleWithPets;

    /**
     * Users own description
     */
    private String usersDescription;

    /**
     * Initializes a newly created {@code FlatDescriptionBuilder} object so that it represents
     * an empty FlatDescriptionBuilder entity.
     */
    public FlatDescriptionBuilder() {

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
     * Sets id and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param id
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns rooms
     *
     * @return rooms
     */
    public int getRooms() {
        return rooms;
    }

    /**
     * Sets rooms and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param rooms
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildRooms(int rooms) {
        this.rooms = rooms;
        return this;
    }

    /**
     * Returns living area
     *
     * @return livingArea
     */
    public float getLivingArea() {
        return livingArea;
    }

    /**
     * Sets living area and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param livingArea
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildLivingArea(float livingArea) {
        this.livingArea = livingArea;
        return this;
    }

    /**
     * Returns whether has furniture
     *
     * @return hasFurniture
     */
    public boolean isHasFurniture() {
        return hasFurniture;
    }

    /**
     * Sets whether has furniture and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param hasFurniture
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
        return this;
    }

    /**
     * Returns whether has home appliances
     *
     * @return hasHomeAppliacnes
     */
    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    /**
     * Sets whether has home appliances and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param hasHomeAppliances
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
        return this;
    }

    /**
     * Returns whether has the Internet
     *
     * @return hasTheInternet
     */
    public boolean isHasTheInternet() {
        return hasTheInternet;
    }

    /**
     * Sets whether has the Internet and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param hasTheInternet
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildHasTheInternet(boolean hasTheInternet) {
        this.hasTheInternet = hasTheInternet;
        return this;
    }

    /**
     * Returns whether is it possible with child
     *
     * @return possibleWithChild
     */
    public boolean isPossibleWithChild() {
        return possibleWithChild;
    }

    /**
     * Sets whether is it possible with child and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param possibleWithChild
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildPossibleWithChild(boolean possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
        return this;
    }

    /**
     * Returns whether is it possible with pets
     *
     * @return possibleWithPets
     */
    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    /**
     * Sets whether is it possible with pets and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param possibleWithPets
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
        return this;
    }

    /**
     * Returns users description
     *
     * @return usersDescription
     */
    public String getUsersDescription() {
        return usersDescription;
    }

    /**
     * Sets users description and returns {@code FlatDescriptionBuilder} object with built field
     *
     * @param usersDescription
     * @return {@see FlatDescriptionBuilder}
     */
    public FlatDescriptionBuilder buildUsersDescription(String usersDescription) {
        this.usersDescription = usersDescription;
        return this;
    }

    /**
     * Returns newly created {@link FlatDescription} with fields, built in builder
     *
     * @return {@see FlatDescription}
     */
    public FlatDescription buildFlatDescription() {
        return new FlatDescription(this);
    }
}
