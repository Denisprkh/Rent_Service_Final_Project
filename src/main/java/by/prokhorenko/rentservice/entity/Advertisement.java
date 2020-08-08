package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.AdvertisementBuilder;
import by.prokhorenko.rentservice.builder.FlatBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Advertisement entity. Has next properties:
 * <b>id</b>,<b>author</b>,<b>flat</b>,<b>title</b>,<b>price</b>,
 * <b>dateOfCreation</b>, <b>isVisible</b>.
 */
public class Advertisement implements Serializable {

    /**
     * Property - id.
     */
    private int id;

    /**
     * Property - {@link User} author.
     */
    private User author;

    /**
     * Property - {@link Flat}.
     */
    private Flat flat;

    /**
     * Property - advertisements title.
     */
    private String title;

    /**
     * Property - price.
     */
    private BigDecimal price;

    /**
     * Property - date of creation.
     */
    private LocalDateTime dateOfCreation;

    /**
     * Property - isVisible status.
     */
    private boolean isVisible;

    /**
     * Initializes a newly created {@code Advertisement} object with
     * fields which have been built with {@link AdvertisementBuilder}.
     *
     * @param advertisementBuilder
     */
    public Advertisement(AdvertisementBuilder advertisementBuilder) {
        this.id = advertisementBuilder.getId();
        this.author = advertisementBuilder.getAuthor();
        this.flat = advertisementBuilder.getFlat();
        this.title = advertisementBuilder.getTitle();
        this.price = advertisementBuilder.getPrice();
        this.dateOfCreation = advertisementBuilder.getDateOfCreation();
        this.isVisible = advertisementBuilder.isVisible();
    }

    /**
     * Initializes a newly created {@code Advertisement} object so that it represents
     * an empty Advertisement entity.
     */
    public Advertisement() {

    }

    /**
     * Returns whether advertisement is visible of an {@code Advertisement} object.
     *
     * @return true if the advertisement is visible and vice versa
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Sets isVisible to an {@code Advertisement} object.
     *
     * @param visible visible status
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Returns id of an {@code Advertisement} object.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id to {@code Advertisement} object.
     *
     * @param id advertisements id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns author of the {@code Advertisement} object.
     *
     * @return author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets author to the {@code Advertisement} object.
     *
     * @param author {@link User} advertisement's author
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Returns {@link Flat} of the {@code Advertisement} object.
     *
     * @return flat
     */
    public Flat getFlat() {
        return flat;
    }

    /**
     * Sets {@link Flat} to the {@code Advertisement} object.
     *
     * @param flat {@link Flat}
     */
    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    /**
     * Returns title of the {@code Advertisement} object.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title to the {@code Advertisement} object.
     *
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns price of the {@code Advertisement} object.
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price to the {@code Advertisement}.
     *
     * @param price price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns date of creation of the {@code Advertisement} object.
     *
     * @return dateOfCreation
     */
    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * Sets dateOfCreation to the {@code Advertisement} object.
     *
     * @param dateOfCreation date of creation
     */
    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    /**
     * Compares this advertisement to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * Advertisement} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code Advertisement} against
     * @return {@code true} if the given object represents a {@code Advertisement}
     * equivalent to this advertisement, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertisement that = (Advertisement) o;

        if (id != that.id) return false;
        if (isVisible != that.isVisible) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (flat != null ? !flat.equals(that.flat) : that.flat != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return dateOfCreation != null ? dateOfCreation.equals(that.dateOfCreation) : that.dateOfCreation == null;
    }

    /**
     * Returns a hash code for this {@code Advertisement} object.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code Advertisement} object.
     *
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Advertisement{");
        sb.append("id=").append(id);
        sb.append(", author=").append(author);
        sb.append(", flat=").append(flat);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append(", isVisible=").append(isVisible);
        sb.append('}');
        return sb.toString();
    }
}
