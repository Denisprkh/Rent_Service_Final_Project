package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.flat.FlatPhoto;

import java.io.InputStream;

public class FlatPhotoBuilder {

    private int id;
    private int flatsId;
    private InputStream flatPhotoData;

    public int getId() {
        return id;
    }

    public FlatPhotoBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public int getFlatsId() {
        return flatsId;
    }

    public FlatPhotoBuilder buildFlatsId(int flatsId) {
        this.flatsId = flatsId;
        return this;
    }

    public InputStream getFlatPhotoData() {
        return flatPhotoData;
    }

    public FlatPhotoBuilder buildFlatPhotoData(InputStream flatPhotoData) {
        this.flatPhotoData = flatPhotoData;
        return this;
    }

    public FlatPhoto buildFlatPhoto(){
        return new FlatPhoto(this);
    }
}
