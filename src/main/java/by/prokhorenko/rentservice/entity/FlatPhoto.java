package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.FlatPhotoBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * FlatPhoto entity. Has next properties:
 * <b>LOG</b>,<b>id</b>,<b>flatsId</b>,<b>flatPhotoData</b>,<b>base64PhotoData</b>.
 */
public class FlatPhoto implements Serializable {

    /**
     * Property - LOG is for logging errors.
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Property - id.
     */
    private int id;

    /**
     * Property - flatsId for storing photo in database according to flat
     * to which it belongs.
     */
    private int flatsId;

    /**
     * Property - flatPhotoData, the data from which the photo consists of.
     */
    private InputStream flatPhotoData;

    /**
     * Property - base64PhotoData, data for displaying photo on the web page.
     */
    private String base64PhotoData;

    /**
     * Initializes a newly created {@code FlatPhoto} object so that it represents
     * an empty FlatPhoto entity.
     */
    public FlatPhoto() {
    }

    /**
     * Initializes a newly created {@code FlatPhoto} object with
     * fields which have been built with {@link FlatPhotoBuilder}.
     *
     * @param flatPhotoBuilder {@link FlatPhotoBuilder}
     */
    public FlatPhoto(FlatPhotoBuilder flatPhotoBuilder) {
        this.id = flatPhotoBuilder.getId();
        this.flatsId = flatPhotoBuilder.getFlatsId();
        this.flatPhotoData = flatPhotoBuilder.getFlatPhotoData();
    }

    /**
     * Returns id of the {@code FlatPhoto} object.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id to the {@code FlatPhoto} object.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns id of the flat, which photo belongs to ,to the {@code FlatPhoto} object.
     *
     * @return flatsId
     */
    public int getFlatsId() {
        return flatsId;
    }

    /**
     * Sets id of the flat, which photo belongs to, of the {@code FlatPhoto} object.
     *
     * @param flatsId
     */
    public void setFlatsId(int flatsId) {
        this.flatsId = flatsId;
    }

    /**
     * Returns data which the photo consists of, of the {@code FlatPhoto} object.
     *
     * @return flatPhotoData
     */
    public InputStream getFlatPhotoData() {
        return flatPhotoData;
    }

    /**
     * Sets data which the photo consists of, of the {@code FlatPhoto} object.
     *
     * @param flatPhotoData inputStream
     */
    public void setFlatPhotoData(InputStream flatPhotoData) {
        this.flatPhotoData = flatPhotoData;
    }

    /**
     * Returns the data for displaying photo on a webPage of the {@code FlatPhoto} object.
     *
     * @return base64PhotoData
     */
    public String getBase64PhotoData() {
        return base64PhotoData;
    }

    /**
     * Sets the data for displaying photo on a webPage to the {@code FlatPhoto} object.
     *
     * @param base64PhotoData
     */
    public void setBase64PhotoData(String base64PhotoData) {
        this.base64PhotoData = base64PhotoData;
    }

    /**
     * Compares this flatPhoto to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * FlatPhoto} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code FlatPhoto} against
     * @return {@code true} if the given object represents a {@code FlatPhoto}
     * equivalent to this flatPhoto, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatPhoto flatPhoto = (FlatPhoto) o;

        if (id != flatPhoto.id) return false;
        if (flatsId != flatPhoto.flatsId) return false;
        if (flatPhotoData == null) {
            if (flatPhoto.flatPhotoData != null) {
                return false;
            }
        } else if (flatPhotoData != null) {
            if (flatPhoto.flatPhotoData == null) {
                return false;
            } else {
                try {
                    while (true) {
                        int fr = flatPhotoData.read();
                        int tr = flatPhoto.flatPhotoData.read();
                        if (fr != tr)
                            return false;

                        if (fr == -1)
                            return true;
                    }
                } catch (IOException e) {
                    LOG.error(e);
                } finally {
                    try {
                        flatPhotoData.close();
                        flatPhoto.flatPhotoData.close();
                    } catch (IOException e) {
                        LOG.error(e);
                    }

                }
            }

        }
        return base64PhotoData != null ? base64PhotoData.equals(flatPhoto.base64PhotoData) : flatPhoto.base64PhotoData == null;
    }

    /**
     * Returns a hash code for this {@code FlatPhoto} object.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + flatsId;
        result = 31 * result + (flatPhotoData != null ? flatPhotoData.hashCode() : 0);
        result = 31 * result + (base64PhotoData != null ? base64PhotoData.hashCode() : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code FlatPhoto} object.
     *
     * @return String
     */
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
