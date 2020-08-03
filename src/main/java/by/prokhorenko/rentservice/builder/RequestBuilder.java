package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.entity.User;

import java.time.LocalDateTime;

public class RequestBuilder {

    private int id;
    private User user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime applicationDate;
    private Advertisement advertisement;
    private boolean isApproved;

    public boolean isApproved() {
        return isApproved;
    }

    public RequestBuilder buildApproved(boolean approved) {
        isApproved = approved;
        return this;
    }


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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public RequestBuilder buildStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public RequestBuilder buildEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public RequestBuilder buildApplicationDate(LocalDateTime applicationDate) {
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
