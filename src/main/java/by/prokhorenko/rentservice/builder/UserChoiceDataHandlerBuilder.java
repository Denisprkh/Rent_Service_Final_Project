package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.advertisement.UserChoiceDataHandler;

public class UserChoiceDataHandlerBuilder {
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

    public String getRooms() {
        return rooms;
    }

    public UserChoiceDataHandlerBuilder buildRooms(String rooms) {
        this.rooms = rooms;
        return this;
    }

    public String getLivingArea() {
        return livingArea;
    }

    public UserChoiceDataHandlerBuilder buildLivingArea(String livingArea) {
        this.livingArea = livingArea;
        return this;
    }

    public String getHasFurniture() {
        return hasFurniture;
    }

    public UserChoiceDataHandlerBuilder buildHasFurniture(String hasFurniture) {
        this.hasFurniture = hasFurniture;
        return this;
    }

    public String getHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public UserChoiceDataHandlerBuilder buildHasHomeAppliances(String hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
        return this;
    }

    public String getPossibleWithChild() {
        return possibleWithChild;
    }

    public UserChoiceDataHandlerBuilder buildPossibleWithChild(String possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
        return this;
    }

    public String getPossibleWithPets() {
        return possibleWithPets;
    }

    public UserChoiceDataHandlerBuilder buildPossibleWithPets(String possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public UserChoiceDataHandlerBuilder buildPrice(String price) {
        this.price = price;
        return this;
    }

    public UserChoiceDataHandler buildUserChoiceDataHandler(){
        return new UserChoiceDataHandler(this);
    }
}
