package by.prokhorenko.rentservice.entity.advertisement;

import by.prokhorenko.rentservice.builder.UserChoiceDataHandlerBuilder;

import java.math.BigDecimal;

public class UserChoiceDataHandler {
    private String city;
    private String district;
    private String street;
    private int rooms;
    private float livingArea;
    private boolean hasFurniture;
    private boolean hasHomeAppliances;
    private boolean possibleWithChild;
    private boolean possibleWithPets;
    private BigDecimal price;

    public UserChoiceDataHandler() {
    }

    public UserChoiceDataHandler(UserChoiceDataHandlerBuilder userChoiceDataHandlerBuilder) {
        this.city = userChoiceDataHandlerBuilder.getCity();
        this.district = userChoiceDataHandlerBuilder.getDistrict();
        this.street = userChoiceDataHandlerBuilder.getStreet();
        this.rooms = userChoiceDataHandlerBuilder.getRooms();
        this.livingArea = userChoiceDataHandlerBuilder.getLivingArea();
        this.hasFurniture = userChoiceDataHandlerBuilder.isHasFurniture();
        this.hasHomeAppliances = userChoiceDataHandlerBuilder.isHasHomeAppliances();
        this.possibleWithChild = userChoiceDataHandlerBuilder.isPossibleWithChild();
        this.possibleWithPets = userChoiceDataHandlerBuilder.isPossibleWithPets();
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

    public int getRooms() {
        return rooms;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserChoiceDataHandler that = (UserChoiceDataHandler) o;

        if (rooms != that.rooms) return false;
        if (Float.compare(that.livingArea, livingArea) != 0) return false;
        if (hasFurniture != that.hasFurniture) return false;
        if (hasHomeAppliances != that.hasHomeAppliances) return false;
        if (possibleWithChild != that.possibleWithChild) return false;
        if (possibleWithPets != that.possibleWithPets) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + rooms;
        result = 31 * result + (livingArea != +0.0f ? Float.floatToIntBits(livingArea) : 0);
        result = 31 * result + (hasFurniture ? 1 : 0);
        result = 31 * result + (hasHomeAppliances ? 1 : 0);
        result = 31 * result + (possibleWithChild ? 1 : 0);
        result = 31 * result + (possibleWithPets ? 1 : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserChoiceDataHandler{");
        sb.append("city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", rooms=").append(rooms);
        sb.append(", livingArea=").append(livingArea);
        sb.append(", hasFurniture=").append(hasFurniture);
        sb.append(", hasHomeAppliances=").append(hasHomeAppliances);
        sb.append(", possibleWithChild=").append(possibleWithChild);
        sb.append(", possibleWithPets=").append(possibleWithPets);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
