package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.advertisement.UserChoiceDataHandler;

import java.math.BigDecimal;

public class UserChoiceDataHandlerBuilder {
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

    public String getCity() {
        return city;
    }

    public UserChoiceDataHandlerBuilder buildCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public UserChoiceDataHandlerBuilder buildDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserChoiceDataHandlerBuilder buildStreet(String street) {
        this.street = street;
        return this;
    }

    public int getRooms() {
        return rooms;
    }

    public UserChoiceDataHandlerBuilder buildRooms(int rooms) {
        this.rooms = rooms;
        return this;
    }

    public float getLivingArea() {
        return livingArea;
    }

    public UserChoiceDataHandlerBuilder buildLivingArea(float livingArea) {
        this.livingArea = livingArea;
        return this;
    }

    public boolean isHasFurniture() {
        return hasFurniture;
    }

    public UserChoiceDataHandlerBuilder buildHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
        return this;
    }

    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public UserChoiceDataHandlerBuilder buildHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
        return this;
    }

    public boolean isPossibleWithChild() {
        return possibleWithChild;
    }

    public UserChoiceDataHandlerBuilder buildPossibleWithChild(boolean possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
        return this;
    }

    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    public UserChoiceDataHandlerBuilder buildPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserChoiceDataHandlerBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UserChoiceDataHandler buildUserChoiceDataHandler(){
        return new UserChoiceDataHandler(this);
    }
}
