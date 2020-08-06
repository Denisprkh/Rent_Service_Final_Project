package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.entity.User;

import java.time.LocalDateTime;

/**
 * Class for building {@link Request} entity
 */
public class RequestBuilder {

    /**
     * Id
     */
    private int id;

    /**
     * User
     */
    private User user;

    /**
     * Start date
     */
    private LocalDateTime startDate;

    /**
     * End date
     */
    private LocalDateTime endDate;

    /**
     * Application Date
     */
    private LocalDateTime applicationDate;

    /**
     * {@link Advertisement}
     */
    private Advertisement advertisement;

    /**
     * Approved status
     */
    private boolean isApproved;

    /**
     * Initializes a newly created {@code RequestBuilder} object so that it represents
     * an empty RequestBuilder entity.
     */
    public RequestBuilder() {

    }

    /**
     * Returns approved status
     *
     * @return isApproved
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Sets approved status and returns {@code RequestBuilder} object with built field
     *
     * @param approved
     * @return {@see RequestBuilder}
     */
    public RequestBuilder buildApproved(boolean approved) {
        isApproved = approved;
        return this;
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
     * Sets id and returns {@code RequestBuilder} object with built field
     *
     * @param id
     * @return {@see RequestBuilder}
     */
    public RequestBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns user
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user and returns {@code RequestBuilder} object with built field
     *
     * @param user
     * @return {@see RequestBuilder}
     */
    public RequestBuilder buildUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Returns startDate
     *
     * @return startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets startDate and returns {@code RequestBuilder} object with built field
     *
     * @param startDate
     * @return {@see RequestBuilder}
     */
    public RequestBuilder buildStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Returns endDate
     *
     * @return endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets endDate and returns {@code RequestBuilder} object with built field
     *
     * @param endDate
     * @return {@see RequestBuilder}
     */
    public RequestBuilder buildEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Returns applicationDate
     *
     * @return applicationDate
     */
    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    /**
     * Sets applicationDate and returns {@code RequestBuilder} object with built field
     *
     * @param applicationDate
     * @return {@see RequestBuilder}
     */
    public RequestBuilder buildApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
        return this;
    }

    /**
     * Returns advertisement
     *
     * @return advertisement
     */
    public Advertisement getAdvertisement() {
        return advertisement;
    }

    /**
     * Sets advertisement and returns {@code RequestBuilder} object with built field
     *
     * @param advertisement
     * @return {@see RequestBuilder}
     */
    public RequestBuilder buildAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
        return this;
    }

    /**
     * Returns newly created {@link Request} with fields, built in builder
     *
     * @return {@see Request}
     */
    public Request buildRequest() {
        return new Request(this);
    }
}
