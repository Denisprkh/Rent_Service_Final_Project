package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.flat.FlatDescription;

public class FlatDescriptionBuilder {

    private int id;
    private int rooms;
    private float livingArea;
    private boolean hasFurniture;
    private boolean hasHomeAppliances;
    private boolean hasTheInternet;
    private boolean possibleWithChild;
    private boolean possibleWithPets;
    private String usersDescription;

    public int getId() {
        return id;
    }

    public FlatDescriptionBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public int getRooms() {
        return rooms;
    }

    public FlatDescriptionBuilder buildRooms(int rooms) {
        this.rooms = rooms;
        return this;
    }

    public float getLivingArea() {
        return livingArea;
    }

    public FlatDescriptionBuilder buildLivingArea(float livingArea) {
        this.livingArea = livingArea;
        return this;
    }

    public boolean isHasFurniture() {
        return hasFurniture;
    }

    public FlatDescriptionBuilder buildHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
        return this;
    }

    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public FlatDescriptionBuilder buildHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
        return this;
    }

    public boolean isHasTheInternet() {
        return hasTheInternet;
    }

    public FlatDescriptionBuilder buildHasTheInternet(boolean hasTheInternet) {
        this.hasTheInternet = hasTheInternet;
        return this;
    }

    public boolean isPossibleWithChild() {
        return possibleWithChild;
    }

    public FlatDescriptionBuilder buildPossibleWithChild(boolean possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
        return this;
    }

    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    public FlatDescriptionBuilder buildPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
        return this;
    }

    public String getUsersDescription() {
        return usersDescription;
    }

    public FlatDescriptionBuilder buildUsersDescription(String usersDescription) {
        this.usersDescription = usersDescription;
        return this;
    }

    public FlatDescription buildFlatDescription(){
        return new FlatDescription(this);
    }
}
