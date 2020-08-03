package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.FlatAddressBuilder;
import java.io.Serializable;

/**
 * FlatAddress entity. Has next properties:
 * <b>id</b>,<b>city</b>,<b>district</b>,<b>street</b>,<b>house</b>
 */
public class FlatAddress implements Serializable {

    /**
     * Property - flatAddress id
     */
    private int id;

    /**
     * Property - flatAddress city
     */
    private String city;

    /**
     * Property - flatAddress district
     */
    private String district;

    /**
     * Property - flatAddress street
     */
    private String street;

    /**
     * Property - flatAddress house
     */
    private String house;

    /**
     * Initializes a newly created {@code FlatAddress} object with
     * fields which have been built with {@link FlatAddressBuilder}
     *
     * @param flatAddressBuilder
     */
    public FlatAddress(FlatAddressBuilder flatAddressBuilder) {
        this.id = flatAddressBuilder.getId();
        this.city = flatAddressBuilder.getCity();
        this.district = flatAddressBuilder.getDistrict();
        this.street = flatAddressBuilder.getStreet();
        this.house = flatAddressBuilder.getHouse();
    }

    /**
     * Initializes a newly created {@code FlatAddress} object with
     * parameters:
     *
     * @param city
     * @param district
     * @param street
     * @param house
     */
    public FlatAddress(String city, String district, String street, String house) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.house = house;
    }

    /**
     * Initializes a newly created {@code FlatAddress} object with
     * parameters:
     *
     * @param city
     * @param district
     * @param street
     * @param house
     */
    public FlatAddress(int id, String city, String district, String street, String house) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.street = street;
        this.house = house;
    }

    /**
     * Initializes a newly created {@code FlatAddress} object so that it represents
     * an empty FlatAddress entity.
     */
    public FlatAddress(){

    }

    /**
     * Returns flat addresses id of an {@code FlatAddress} object
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets flat addresses id to an {@code FlatAddress} object
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns flat addresses city of an {@code FlatAddress} object
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets flat addresses city to an {@code FlatAddress} object
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns flat addresses district of an {@code FlatAddress} object
     *
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets flat addresses district to an {@code FlatAddress} object
     *
     * @param district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Returns flat addresses street of an {@code FlatAddress} object
     *
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets flat addresses street to an {@code FlatAddress} object
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns flat addresses house of an {@code FlatAddress} object
     *
     * @return
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets flat addresses house to an {@code FlatAddress} object
     *
     * @param house
     */
    public void setHouse(String house) {
        this.house = house;
    }

    /**
     * Compares this flatAddress to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * FlatAddress} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code FlatAddress} against
     * @return {@code true} if the given object represents a {@code FlatAddress}
     * equivalent to this flatAddress, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatAddress that = (FlatAddress) o;

        if (id != that.id) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        return house != null ? house.equals(that.house) : that.house == null;
    }

    /**
     * Returns a hash code for this {@code FlatAddress} object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code FlatAddress} object
     *
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlatAddress{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", house='").append(house).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
