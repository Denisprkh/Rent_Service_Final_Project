package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatPhoto;
import by.prokhorenko.rentservice.entity.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdvertisementBuilder {
    private int id;
    private User author;
    private Flat flat;
    private String title;
    private BigDecimal price;
    private LocalDateTime dateOfCreation;
    private boolean isVisible;

    public int getId() {
        return id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public AdvertisementBuilder buildVisible(boolean visible) {
        isVisible = visible;
        return this;
    }

    public AdvertisementBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public AdvertisementBuilder buildAuthor(User author) {
        this.author = author;
        return this;
    }

    public Flat getFlat() {
        return flat;
    }

    public AdvertisementBuilder buildFlat(Flat flat) {
        this.flat = flat;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AdvertisementBuilder buildTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AdvertisementBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public AdvertisementBuilder buildDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }

    public Advertisement buildAdvertisement(){
        return new Advertisement(this);
    }
}
