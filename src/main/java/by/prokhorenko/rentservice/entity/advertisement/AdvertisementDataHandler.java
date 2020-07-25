package by.prokhorenko.rentservice.entity.advertisement;

import by.prokhorenko.rentservice.builder.AdvertisementDataHandlerBuilder;

public class AdvertisementDataHandler {
    private String city;
    private String district;
    private String street;
    private String rooms;
    private String houseNumber;
    private String title;
    private String area;
    private String price;
    private boolean hasFurniture;
    private boolean hasHomeAppliances;
    private boolean hasTheInternet;
    private boolean possibleWithChildren;
    private boolean possibleWithPets;
    private String usersDescription;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdvertisementDataHandler() {
    }

    public AdvertisementDataHandler(AdvertisementDataHandlerBuilder advertisementDataHandlerBuilder) {
        this.city = advertisementDataHandlerBuilder.getCity();
        this.district = advertisementDataHandlerBuilder.getDistrict();
        this.street = advertisementDataHandlerBuilder.getStreet();
        this.rooms = advertisementDataHandlerBuilder.getRooms();
        this.price = advertisementDataHandlerBuilder.getPrice();
        this.area = advertisementDataHandlerBuilder.getLivingArea();
        this.houseNumber = advertisementDataHandlerBuilder.getHouseNumber();
        this.title = advertisementDataHandlerBuilder.getTitle();
        this.hasFurniture = advertisementDataHandlerBuilder.isHasFurniture();
        this.hasHomeAppliances = advertisementDataHandlerBuilder.isHasHomeAppliances();
        this.hasTheInternet = advertisementDataHandlerBuilder.isHasTheInternet();
        this.possibleWithChildren = advertisementDataHandlerBuilder.isPossibleWithChildren();
        this.possibleWithPets = advertisementDataHandlerBuilder.isPossibleWithPets();
        this.usersDescription = advertisementDataHandlerBuilder.getUsersDescription();
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public boolean isPossibleWithChildren() {
        return possibleWithChildren;
    }

    public void setPossibleWithChildren(boolean possibleWithChildren) {
        this.possibleWithChildren = possibleWithChildren;
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

        AdvertisementDataHandler that = (AdvertisementDataHandler) o;

        if (hasFurniture != that.hasFurniture) return false;
        if (hasHomeAppliances != that.hasHomeAppliances) return false;
        if (hasTheInternet != that.hasTheInternet) return false;
        if (possibleWithChildren != that.possibleWithChildren) return false;
        if (possibleWithPets != that.possibleWithPets) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (rooms != null ? !rooms.equals(that.rooms) : that.rooms != null) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return usersDescription != null ? usersDescription.equals(that.usersDescription) : that.usersDescription == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (hasFurniture ? 1 : 0);
        result = 31 * result + (hasHomeAppliances ? 1 : 0);
        result = 31 * result + (hasTheInternet ? 1 : 0);
        result = 31 * result + (possibleWithChildren ? 1 : 0);
        result = 31 * result + (possibleWithPets ? 1 : 0);
        result = 31 * result + (usersDescription != null ? usersDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserAdvertisementDataHandler{");
        sb.append("city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", rooms='").append(rooms).append('\'');
        sb.append(", houseNumber='").append(houseNumber).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", hasFurniture=").append(hasFurniture);
        sb.append(", hasHomeAppliances=").append(hasHomeAppliances);
        sb.append(", hasTheInternet=").append(hasTheInternet);
        sb.append(", possibleWithChildren=").append(possibleWithChildren);
        sb.append(", possibleWithPets=").append(possibleWithPets);
        sb.append(", usersDescription='").append(usersDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
