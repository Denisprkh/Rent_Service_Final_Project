package by.prokhorenko.rentservice.entity.flat;

import by.prokhorenko.rentservice.builder.FlatPhotoBuilder;

import java.io.InputStream;
import java.io.Serializable;

public class FlatPhoto implements Serializable {

    private int id;
    private int flatsId;
    private InputStream flatPhotoData;
    private String base64PhotoData;

    public FlatPhoto(){}

    public FlatPhoto(FlatPhotoBuilder builder){
        this.id = builder.getId();
        this.flatsId = builder.getFlatsId();
        this.flatPhotoData = builder.getFlatPhotoData();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlatsId() {
        return flatsId;
    }

    public void setFlatsId(int flatsId) {
        this.flatsId = flatsId;
    }

    public InputStream getFlatPhotoData() {
        return flatPhotoData;
    }

    public void setFlatPhotoData(InputStream flatPhotoData) {
        this.flatPhotoData = flatPhotoData;
    }

    public String getBase64PhotoData() {
        return base64PhotoData;
    }

    public void setBase64PhotoData(String base64PhotoData) {
        this.base64PhotoData = base64PhotoData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatPhoto flatPhoto = (FlatPhoto) o;

        if (id != flatPhoto.id) return false;
        if (flatsId != flatPhoto.flatsId) return false;
        if (flatPhotoData != null ? !flatPhotoData.equals(flatPhoto.flatPhotoData) : flatPhoto.flatPhotoData != null)
            return false;
        return base64PhotoData != null ? base64PhotoData.equals(flatPhoto.base64PhotoData) : flatPhoto.base64PhotoData == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + flatsId;
        result = 31 * result + (flatPhotoData != null ? flatPhotoData.hashCode() : 0);
        result = 31 * result + (base64PhotoData != null ? base64PhotoData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlatPhoto{");
        sb.append("id=").append(id);
        sb.append(", flatsId=").append(flatsId);
        sb.append(", flatPhotoData=").append(flatPhotoData);
        sb.append('}');
        return sb.toString();
    }
}
