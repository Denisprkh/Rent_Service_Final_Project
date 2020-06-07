package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.entity.user.User;

public class FlatBuilder {

    private int id;
    private boolean isFree;
    private FlatDescription flatDescription;
    private FlatAddress flatAddress;
    private User owner;

    public int getId() {
        return id;
    }

    public FlatBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public boolean isFree() {
        return isFree;
    }

    public FlatBuilder buildIsFree(boolean free) {
        isFree = free;
        return this;
    }

    public FlatDescription getFlatDescription() {
        return flatDescription;
    }

    public FlatBuilder buildFlatDescription(FlatDescription flatDescription) {
        this.flatDescription = flatDescription;
        return this;
    }

    public FlatAddress getFlatAddress() {
        return flatAddress;
    }

    public FlatBuilder buildFlatAddress(FlatAddress flatAddress) {
        this.flatAddress = flatAddress;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public FlatBuilder buildOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Flat buildFlat(){
        return new Flat(this);
    }
}
