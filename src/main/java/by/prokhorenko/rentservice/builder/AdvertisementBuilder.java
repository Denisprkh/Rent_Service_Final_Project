package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.Flat;
import by.prokhorenko.rentservice.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class for building {@link Advertisement} entity
 */
public class AdvertisementBuilder {

    /**
     * Id
     */
    private int id;

    /**
     * Author
     */
    private User author;

    /**
     * Flat
     */
    private Flat flat;

    /**
     * Title
     */
    private String title;

    /**
     * Price
     */
    private BigDecimal price;

    /**
     * Date of creation
     */
    private LocalDateTime dateOfCreation;

    /**
     * Visible status
     */
    private boolean isVisible;

    /**
     * Initializes a newly created {@code AdvertisementBuilder} object so that it represents
     * an empty AdvertisementBuilder entity.
     */
    public AdvertisementBuilder() {

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
     * Sets id and returns {@code AdvertisementBuilder} object with built field
     *
     * @param id
     * @return {@see AdvertisementBuilder}
     */
    public AdvertisementBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns visibility status
     *
     * @return isVisible
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Sets visibility status and returns {@code AdvertisementBuilder} object with built field
     *
     * @param visible
     * @return {@see AdvertisementBuilder}
     */
    public AdvertisementBuilder buildVisible(boolean visible) {
        isVisible = visible;
        return this;
    }

    /**
     * Returns author
     *
     * @return author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets author and returns {@code AdvertisementBuilder} object with built field
     *
     * @param author
     * @return {@see AdvertisementBuilder}
     */
    public AdvertisementBuilder buildAuthor(User author) {
        this.author = author;
        return this;
    }

    /**
     * Returns flat
     *
     * @return flat
     */
    public Flat getFlat() {
        return flat;
    }

    /**
     * Sets flat and returns {@code AdvertisementBuilder} object with built field
     *
     * @param flat
     * @return {@see AdvertisementBuilder}
     */
    public AdvertisementBuilder buildFlat(Flat flat) {
        this.flat = flat;
        return this;
    }

    /**
     * Returns title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets advertisement and returns {@code AdvertisementBuilder} object with built field
     *
     * @param title
     * @return {@see AdvertisementBuilder}
     */
    public AdvertisementBuilder buildTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Returns price
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price and returns {@code AdvertisementBuilder} object with built field
     *
     * @param price
     * @return {@see AdvertisementBuilder}
     */
    public AdvertisementBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * Returns dateOfCreation
     *
     * @return dateOfCreation
     */
    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * Sets dateOfCreation and returns {@code AdvertisementBuilder} object with built field
     *
     * @param dateOfCreation
     * @return {@see AdvertisementBuilder}
     */
    public AdvertisementBuilder buildDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }

    /**
     * Returns a newly created {@link Advertisement} object with fields, built with {@code AdvertisementBuilder}
     *
     * @return {@see Advertisement}
     */
    public Advertisement buildAdvertisement() {
        return new Advertisement(this);
    }
}
