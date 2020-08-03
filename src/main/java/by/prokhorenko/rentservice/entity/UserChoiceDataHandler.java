package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.FlatDescriptionBuilder;
import by.prokhorenko.rentservice.builder.UserChoiceDataHandlerBuilder;

import java.io.Serializable;

/**
 * Auxiliary class for handling users chosen data from filters to build regex for search,
 * contains properties and get and set methods from {@link FlatAddress}, {@link FlatDescription},
 * {@link Advertisement} entities;
 */
public class UserChoiceDataHandler implements Serializable {
    private String city;
    private String district;
    private String street;
    private String rooms;
    private String livingArea;
    private String hasFurniture;
    private String hasHomeAppliances;
    private String possibleWithChild;
    private String possibleWithPets;
    private String price;

    /**
     * Initializes a newly created {@code UserChoiceDataHandler} object so that it represents
     * an empty UserChoiceDataHandler entity.
     */
    public UserChoiceDataHandler() {
    }

    /**
     * Initializes a newly created {@code UserChoiceDataHandler} object with
     * fields which have been built with {@link UserChoiceDataHandlerBuilder}
     *
     * @param userChoiceDataHandlerBuilder
     */
    public UserChoiceDataHandler(UserChoiceDataHandlerBuilder userChoiceDataHandlerBuilder) {
        this.city = userChoiceDataHandlerBuilder.getCity();
        this.district = userChoiceDataHandlerBuilder.getDistrict();
        this.street = userChoiceDataHandlerBuilder.getStreet();
        this.rooms = userChoiceDataHandlerBuilder.getRooms();
        this.livingArea = userChoiceDataHandlerBuilder.getLivingArea();
        this.hasFurniture = userChoiceDataHandlerBuilder.getHasFurniture();
        this.hasHomeAppliances = userChoiceDataHandlerBuilder.getHasHomeAppliances();
        this.possibleWithChild = userChoiceDataHandlerBuilder.getPossibleWithChild();
        this.possibleWithPets = userChoiceDataHandlerBuilder.getPossibleWithPets();
        this.price = userChoiceDataHandlerBuilder.getPrice();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(String livingArea) {
        this.livingArea = livingArea;
    }

    public String getHasFurniture() {
        return hasFurniture;
    }

    public void setHasFurniture(String hasFurniture) {
        this.hasFurniture = hasFurniture;
    }

    public String getHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public void setHasHomeAppliances(String hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
    }

    public String getPossibleWithChild() {
        return possibleWithChild;
    }

    public void setPossibleWithChild(String possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
    }

    public String getPossibleWithPets() {
        return possibleWithPets;
    }

    public void setPossibleWithPets(String possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Compares this userChoiceDataHandler to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * UserChoiceDataHandler} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code UserChoiceDataHandler} against
     * @return {@code true} if the given object represents a {@code UserChoiceDataHandler}
     * equivalent to this userChoiceDataHandler, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserChoiceDataHandler that = (UserChoiceDataHandler) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (rooms != null ? !rooms.equals(that.rooms) : that.rooms != null) return false;
        if (livingArea != null ? !livingArea.equals(that.livingArea) : that.livingArea != null) return false;
        if (hasFurniture != null ? !hasFurniture.equals(that.hasFurniture) : that.hasFurniture != null) return false;
        if (hasHomeAppliances != null ? !hasHomeAppliances.equals(that.hasHomeAppliances) : that.hasHomeAppliances != null)
            return false;
        if (possibleWithChild != null ? !possibleWithChild.equals(that.possibleWithChild) : that.possibleWithChild != null)
            return false;
        if (possibleWithPets != null ? !possibleWithPets.equals(that.possibleWithPets) : that.possibleWithPets != null)
            return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    /**
     * Returns a hash code for this {@code UserChoiceDataHandler} object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        result = 31 * result + (livingArea != null ? livingArea.hashCode() : 0);
        result = 31 * result + (hasFurniture != null ? hasFurniture.hashCode() : 0);
        result = 31 * result + (hasHomeAppliances != null ? hasHomeAppliances.hashCode() : 0);
        result = 31 * result + (possibleWithChild != null ? possibleWithChild.hashCode() : 0);
        result = 31 * result + (possibleWithPets != null ? possibleWithPets.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code UserChoiceDataHandler} object
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserChoiceDataHandler{");
        sb.append("city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", rooms='").append(rooms).append('\'');
        sb.append(", livingArea='").append(livingArea).append('\'');
        sb.append(", hasFurniture='").append(hasFurniture).append('\'');
        sb.append(", hasHomeAppliances='").append(hasHomeAppliances).append('\'');
        sb.append(", possibleWithChild='").append(possibleWithChild).append('\'');
        sb.append(", possibleWithPets='").append(possibleWithPets).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
