package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.FlatAddress;

public class FlatAddressBuilder {

    private int id;
    private String city;
    private String district;
    private String street;
    private String house;

    public int getId() {
        return id;
    }

    public FlatAddressBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public FlatAddressBuilder buildCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public FlatAddressBuilder buildDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public FlatAddressBuilder buildStreet(String street) {
        this.street = street;
        return this;
    }

    public String getHouse() {
        return house;
    }

    public FlatAddressBuilder buildHouse(String house) {
        this.house = house;
        return this;
    }

    public FlatAddress buildFlatAddress(){
        return new FlatAddress(this);
    }
}
