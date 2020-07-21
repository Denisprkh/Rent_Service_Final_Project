package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.entity.flat.FlatPhoto;
import by.prokhorenko.rentservice.entity.user.User;

import java.util.List;

public class FlatBuilder {

    private int id;
    private boolean isFree;
    private FlatDescription flatDescription;
    private FlatAddress flatAddress;
    private List<FlatPhoto> flatPhotos;

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

    public List<FlatPhoto> getFlatPhotos() {
        return flatPhotos;
    }

    public FlatBuilder buildFlatPhotos(List<FlatPhoto> flatPhotos) {
        this.flatPhotos = flatPhotos;
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

    public Flat buildFlat(){
        return new Flat(this);
    }
}
