package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.entity.user.User;

import java.util.Date;

public class RequestBuilder {

    private int id;
    private User user;
    private Date startDate;
    private Date endDate;
    private Date applicationDate;
    private Advertisement advertisement;

    public int getId() {
        return id;
    }

    public RequestBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RequestBuilder buildUser(User user) {
        this.user = user;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public RequestBuilder buildStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public RequestBuilder buildEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public RequestBuilder buildApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
        return this;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public RequestBuilder buildAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
        return this;
    }

    public Request buildRequest(){
        return new Request(this);
    }
}
