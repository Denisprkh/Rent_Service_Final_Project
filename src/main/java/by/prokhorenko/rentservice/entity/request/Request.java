package by.prokhorenko.rentservice.entity.request;

import by.prokhorenko.rentservice.builder.RequestBuilder;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.user.User;

import java.util.Date;

public class Request {

    private int id;
    private User user;
    private Date startDate;
    private Date endDate;
    private Date applicationDate;
    private Advertisement advertisement;

    public Request(RequestBuilder requestBuilder){
        this.id = requestBuilder.getId();
        this.user = requestBuilder.getUser();
        this.startDate = requestBuilder.getStartDate();
        this.endDate = requestBuilder.getEndDate();
        this.applicationDate = requestBuilder.getApplicationDate();
        this.advertisement = requestBuilder.getAdvertisement();
    }

    public Request(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (id != request.id) return false;
        if (user != null ? !user.equals(request.user) : request.user != null) return false;
        if (startDate != null ? !startDate.equals(request.startDate) : request.startDate != null) return false;
        if (endDate != null ? !endDate.equals(request.endDate) : request.endDate != null) return false;
        if (applicationDate != null ? !applicationDate.equals(request.applicationDate) : request.applicationDate != null)
            return false;
        return advertisement != null ? advertisement.equals(request.advertisement) : request.advertisement == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (applicationDate != null ? applicationDate.hashCode() : 0);
        result = 31 * result + (advertisement != null ? advertisement.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", applicationDate=").append(applicationDate);
        sb.append(", advertisement=").append(advertisement);
        sb.append('}');
        return sb.toString();
    }
}