package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.FlatAddress;

/**
 * Class for building {@link FlatAddress}
 */
public class FlatAddressBuilder {

    /**
     * Id
     */
    private int id;

    /**
     * City
     */
    private String city;

    /**
     * District
     */
    private String district;

    /**
     * Street
     */
    private String street;

    /**
     * House
     */
    private String house;

    /**
     * Initializes a newly created {@code FlatAddressBuilder} object so that it represents
     * an empty FlatAddressBuilder entity.
     */
    public FlatAddressBuilder() {
    }

    /**
     * Returns id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id and returns {@code FlatAddressBuilder} object with built field
     *
     * @param id
     * @return {@see FlatAddressBuilder}
     */
    public FlatAddressBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns city
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city and returns {@code FlatAddressBuilder} object with built field
     *
     * @param city
     * @return {@see FlatAddressBuilder}
     */
    public FlatAddressBuilder buildCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * Returns district
     *
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets district and returns {@code FlatAddressBuilder} object with built field
     *
     * @param district
     * @return {@see FlatAddressBuilder}
     */
    public FlatAddressBuilder buildDistrict(String district) {
        this.district = district;
        return this;
    }

    /**
     * Returns street
     *
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street and returns {@code FlatAddressBuilder} object with built field
     *
     * @param street
     * @return {@see FlatAddressBuilder}
     */
    public FlatAddressBuilder buildStreet(String street) {
        this.street = street;
        return this;
    }

    /**
     * Returns house
     *
     * @return house
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets house and returns {@code FlatAddressBuilder} object with built field
     *
     * @param house
     * @return {@see FlatAddressBuilder}
     */
    public FlatAddressBuilder buildHouse(String house) {
        this.house = house;
        return this;
    }

    /**
     * Returns newly created {@link FlatAddress} with fields, built in builder
     *
     * @return {@see FlatAddress}
     */
    public FlatAddress buildFlatAddress() {
        return new FlatAddress(this);
    }
}
