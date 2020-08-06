package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.AdvertisementDataHandler;

/**
 * Class for building auxiliary class {@link AdvertisementDataHandler}, fields in that class are
 * from {@link FlatAddressBuilder},{@link FlatDescriptionBuilder},{@link AdvertisementBuilder}, methods do
 * same things, but build methods return {@see AdvertisementDataHandlerBuilder}, entity construct method returns
 *  * {@link AdvertisementDataHandler}
 */
public class AdvertisementDataHandlerBuilder {

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
    private String usersDescription;

    public String getCity() {
        return city;
    }

    public AdvertisementDataHandlerBuilder buildCity(String city) {
        this.city = city;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public AdvertisementDataHandlerBuilder buildHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AdvertisementDataHandlerBuilder buildDistrict(String district) {
        this.district = district;
        return this;
    }

    public boolean isHasFurniture() {
        return hasFurniture;
    }

    public AdvertisementDataHandlerBuilder buildIsHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
        return this;
    }

    public boolean isHasHomeAppliances() {
        return hasHomeAppliances;
    }

    public AdvertisementDataHandlerBuilder buildIsHasHomeAppliances(boolean hasHomeAppliances) {
        this.hasHomeAppliances = hasHomeAppliances;
        return this;
    }

    public boolean isHasTheInternet() {
        return hasTheInternet;
    }

    public AdvertisementDataHandlerBuilder buildIsHasTheInternet(boolean hasTheInternet) {
        this.hasTheInternet = hasTheInternet;
        return this;
    }

    public boolean isPossibleWithChildren() {
        return possibleWithChildren;
    }

    public AdvertisementDataHandlerBuilder buildPossibleWithChildren(boolean possibleWithChildren) {
        this.possibleWithChildren = possibleWithChildren;
        return this;
    }

    public boolean isPossibleWithPets() {
        return possibleWithPets;
    }

    public AdvertisementDataHandlerBuilder buildPossibleWithPets(boolean possibleWithPets) {
        this.possibleWithPets = possibleWithPets;
        return this;
    }


    public String getUsersDescription() {
        return usersDescription;
    }

    public AdvertisementDataHandlerBuilder buildUsersDescription(String usersDescription) {
        this.usersDescription = usersDescription;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AdvertisementDataHandlerBuilder buildTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AdvertisementDataHandlerBuilder buildStreet(String street) {
        this.street = street;
        return this;
    }

    public String getRooms() {
        return rooms;
    }

    public AdvertisementDataHandlerBuilder buildRooms(String rooms) {
        this.rooms = rooms;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public AdvertisementDataHandlerBuilder buildPrice(String price) {
        this.price = price;
        return this;
    }

    public String getLivingArea() {
        return livingArea;
    }

    public AdvertisementDataHandlerBuilder buildLivingArea(String livingArea) {
        this.livingArea = livingArea;
        return this;
    }

    public AdvertisementDataHandler buildUserChoiceHandler() {

        return new AdvertisementDataHandler(this);
    }
}
