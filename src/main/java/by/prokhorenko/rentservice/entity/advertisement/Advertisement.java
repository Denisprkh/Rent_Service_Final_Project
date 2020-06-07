package by.prokhorenko.rentservice.entity.advertisement;

import by.prokhorenko.rentservice.builder.AdvertisementBuilder;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.user.User;

import java.math.BigDecimal;
import java.util.Date;

public class Advertisement {

    private int id;
    private User author;
    private Flat flat;
    private String title;
    private BigDecimal price;
    private boolean communalPaymentsByOwner;
    private Date dateOfCreation;

    public Advertisement(AdvertisementBuilder advertisementBuilder){
        this.id = advertisementBuilder.getId();
        this.author = advertisementBuilder.getAuthor();
        this.flat = advertisementBuilder.getFlat();
        this.title = advertisementBuilder.getTitle();
        this.price = advertisementBuilder.getPrice();
        this.communalPaymentsByOwner = advertisementBuilder.isCommunalPaymentsByOwner();
        this.dateOfCreation = advertisementBuilder.getDateOfCreation();
    }

    public Advertisement(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isCommunalPaymentsByOwner() {
        return communalPaymentsByOwner;
    }

    public void setCommunalPaymentsByOwner(boolean communalPaymentsByOwner) {
        this.communalPaymentsByOwner = communalPaymentsByOwner;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertisement that = (Advertisement) o;

        if (id != that.id) return false;
        if (communalPaymentsByOwner != that.communalPaymentsByOwner) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (flat != null ? !flat.equals(that.flat) : that.flat != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return dateOfCreation != null ? dateOfCreation.equals(that.dateOfCreation) : that.dateOfCreation == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (communalPaymentsByOwner ? 1 : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Advertisement{");
        sb.append("id=").append(id);
        sb.append(", author=").append(author);
        sb.append(", flat=").append(flat);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", communalPaymentsByOwner=").append(communalPaymentsByOwner);
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append('}');
        return sb.toString();
    }
}
