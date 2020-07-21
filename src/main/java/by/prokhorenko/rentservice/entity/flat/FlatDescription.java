package by.prokhorenko.rentservice.entity.flat;

import by.prokhorenko.rentservice.builder.FlatDescriptionBuilder;

import java.io.Serializable;

public class FlatDescription implements Serializable {

    private int id;
    private int rooms;
    private float livingArea;
    private boolean hasFurniture;
    private boolean hasHomeAppliances;
    private boolean hasTheInternet;
    private boolean possibleWithChild;
    private boolean possibleWithPets;
    private FlatRepairType repairType;
    private String usersDescription;

    public FlatDescription(FlatDescriptionBuilder flatDescriptionBuilder){
        this.id = flatDescriptionBuilder.getId();
        this.rooms = flatDescriptionBuilder.getRooms();
        this.livingArea = flatDescriptionBuilder.getLivingArea();
        this.hasFurniture = flatDescriptionBuilder.isHasFurniture();
        this.hasHomeAppliances = flatDescriptionBuilder.isHasHomeAppliances();
        this.hasTheInternet = flatDescriptionBuilder.isHasTheInternet();
        this.possibleWithChild = flatDescriptionBuilder.isPossibleWithChild();
        this.possibleWithPets = flatDescriptionBuilder.isPossibleWithPets();
        this.usersDescription = flatDescriptionBuilder.getUsersDescription();
        this.repairType = flatDescriptionBuilder.getRepairType();
    }

    public FlatDescription(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRooms() {
        return rooms;
    }

    public FlatRepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(FlatRepairType repairType) {
        this.repairType = repairType;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public float getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(float livingArea) {
        this.livingArea = livingArea;
    }

    public boolean isHasFurniture() {
        return hasFurniture;
    }

    public void setHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
    }

    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public void setHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
    }

    public boolean isHasTheInternet() {
        return hasTheInternet;
    }

    public void setHasTheInternet(boolean hasTheInternet) {
        this.hasTheInternet = hasTheInternet;
    }

    public boolean isPossibleWithChild() {
        return possibleWithChild;
    }

    public void setPossibleWithChild(boolean possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
    }

    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    public void setPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
    }

    public String getUsersDescription() {
        return usersDescription;
    }

    public void setUsersDescription(String usersDescription) {
        this.usersDescription = usersDescription;
    }

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
        sb.append(", repairType=").append(repairType);
        sb.append(", usersDescription='").append(usersDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
