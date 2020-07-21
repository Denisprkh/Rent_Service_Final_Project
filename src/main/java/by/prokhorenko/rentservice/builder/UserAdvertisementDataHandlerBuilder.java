package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.advertisement.UserAdvertisementDataHandler;
import by.prokhorenko.rentservice.entity.flat.FlatRepairType;

import java.math.BigDecimal;

public class UserAdvertisementDataHandlerBuilder {

    private String city;
    private String district;
    private String street;
    private String title;
    private String rooms;
    private String price;
    private String livingArea;
    private String houseNumber;
    private boolean hasFurniture;
    private boolean hasHomeAppliances;
    private boolean hasTheInternet;
    private boolean possibleWithChildren;
    private boolean possibleWithPets;
    private FlatRepairType repairType;
    private String usersDescription;



    public String getCity() {
        return city;
    }

    public UserAdvertisementDataHandlerBuilder buildCity(String city) {
        this.city = city;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public UserAdvertisementDataHandlerBuilder buildHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public UserAdvertisementDataHandlerBuilder buildDistrict(String district) {
        this.district = district;
        return this;
    }

    public boolean isHasFurniture() {
        return hasFurniture;
    }

    public UserAdvertisementDataHandlerBuilder buildIsHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
        return this;
    }

    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public UserAdvertisementDataHandlerBuilder buildIsHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
        return this;
    }

    public boolean isHasTheInternet() {
        return hasTheInternet;
    }

    public UserAdvertisementDataHandlerBuilder buildIsHasTheInternet(boolean hasTheInternet) {
        this.hasTheInternet = hasTheInternet;
        return this;
    }

    public boolean isPossibleWithChildren() {
        return possibleWithChildren;
    }

    public UserAdvertisementDataHandlerBuilder buildPossibleWithChildren(boolean possibleWithChildren) {
        this.possibleWithChildren = possibleWithChildren;
        return this;
    }

    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    public UserAdvertisementDataHandlerBuilder buildPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
        return this;
    }

    public FlatRepairType getRepairType() {
        return repairType;
    }

    public UserAdvertisementDataHandlerBuilder buildRepairType(FlatRepairType repairType) {
        this.repairType = repairType;
        return this;
    }

    public String getUsersDescription() {
        return usersDescription;
    }

    public UserAdvertisementDataHandlerBuilder buildUsersDescription(String usersDescription) {
        this.usersDescription = usersDescription;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UserAdvertisementDataHandlerBuilder buildTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserAdvertisementDataHandlerBuilder buildStreet(String street) {
        this.street = street;
        return this;
    }

    public String getRooms() {
        return rooms;
    }

    public UserAdvertisementDataHandlerBuilder buildRooms(String rooms) {
        this.rooms = rooms;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public UserAdvertisementDataHandlerBuilder buildPrice(String price) {
        this.price = price;
        return this;
    }

    public String getLivingArea() {
        return livingArea;
    }

    public UserAdvertisementDataHandlerBuilder buildLivingArea(String livingArea) {
        this.livingArea = livingArea;
        return this;
    }

    public UserAdvertisementDataHandler buildUserChoiceHandler(){

        return new UserAdvertisementDataHandler(this);
    }
}
