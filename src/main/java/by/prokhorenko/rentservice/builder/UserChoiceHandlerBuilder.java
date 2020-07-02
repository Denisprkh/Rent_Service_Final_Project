package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.advertisement.UserChoiceHandler;
import by.prokhorenko.rentservice.entity.flat.FlatRepairType;

import java.math.BigDecimal;

public class UserChoiceHandlerBuilder {

    private String city;
    private String district;
    private String street;
    private int rooms;
    private BigDecimal price;
    private float livingArea;
    private FlatRepairType repairType;
    private boolean hasFurniture;
    private boolean hasHomeAppliances;
    private boolean possibleWithChild;
    private boolean possibleWithPets;

    public String getCity() {
        return city;
    }

    public UserChoiceHandlerBuilder buildCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public UserChoiceHandlerBuilder buildDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserChoiceHandlerBuilder buildStreet(String street) {
        this.street = street;
        return this;
    }

    public int getRooms() {
        return rooms;
    }

    public UserChoiceHandlerBuilder buildRooms(int rooms) {
        this.rooms = rooms;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserChoiceHandlerBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public float getLivingArea() {
        return livingArea;
    }

    public UserChoiceHandlerBuilder buildLivingArea(float livingArea) {
        this.livingArea = livingArea;
        return this;
    }

    public FlatRepairType getRepairType() {
        return repairType;
    }

    public UserChoiceHandlerBuilder buildRepairType(FlatRepairType repairType) {
        this.repairType = repairType;
        return this;
    }

    public boolean isHasFurniture() {
        return hasFurniture;
    }

    public UserChoiceHandlerBuilder buildHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
        return this;
    }

    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public UserChoiceHandlerBuilder buildHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
        return this;
    }

    public boolean isPossibleWithChild() {
        return possibleWithChild;
    }

    public UserChoiceHandlerBuilder buildPossibleWithChild(boolean possibleWithChild) {
        this.possibleWithChild = possibleWithChild;
        return this;
    }

    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    public UserChoiceHandlerBuilder buildPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
        return this;
    }

    public UserChoiceHandler buildUserChoiceHandler(){
        return new UserChoiceHandler(this);
    }
}
