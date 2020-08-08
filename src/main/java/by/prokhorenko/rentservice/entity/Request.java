package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.RequestBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Request entity. Has next properties:
 * <b>id</b>,<b>user</b>,<b>startDate</b>,<b>endDate</b>,
 * <b>applicationDate</b>,<b>isApproved</b>.
 */
public class Request implements Serializable {
    /**
     * Property - id.
     */
    private int id;

    /**
     * Property - {@link User} who has sent a request.
     */
    private User user;

    /**
     * Property - startDate represents the start date of start.
     */
    private LocalDateTime startDate;

    /**
     * Property - endDate represents the end date of renting.
     */
    private LocalDateTime endDate;

    /**
     * Property - applicationDate.
     */
    private LocalDateTime applicationDate;

    /**
     * Property - {@link Advertisement}, advertisement for which the request was sent.
     */
    private Advertisement advertisement;

    /**
     * Property - isApproved, status of approving rhe request.
     */
    private boolean isApproved;

    /**
     * Initializes a newly created {@code Request} object with
     * fields which have been built with {@link RequestBuilder}.
     *
     * @param requestBuilder {@link RequestBuilder}
     */
    public Request(RequestBuilder requestBuilder) {
        this.id = requestBuilder.getId();
        this.user = requestBuilder.getUser();
        this.startDate = requestBuilder.getStartDate();
        this.endDate = requestBuilder.getEndDate();
        this.applicationDate = requestBuilder.getApplicationDate();
        this.advertisement = requestBuilder.getAdvertisement();
        this.isApproved = requestBuilder.isApproved();
    }

    /**
     * Initializes a newly created {@code Request} object so that it represents
     * an empty Request entity.
     */
    public Request() {

    }

    /**
     * Returns id of the {@code Request} object.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id to the {@code Request} object.
     *
     * @param id requests id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns {@link User} who has sent a request of the {@code Request} object.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets {@link User} who has sent a request to the {@code Request} object.
     *
     * @param user {@link User}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns startDate of the {@code Request} object.
     *
     * @return startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets startDate to the {@code Request} object.
     *
     * @param startDate start date
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns endDate of the {@code Request} object.
     *
     * @return endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets endDate to the {@code Request} object.
     *
     * @param endDate end date
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns applicationDate of the {@code Request} object.
     *
     * @return applicationDate
     */
    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    /**
     * Sets applicationDate to the {@code Request} object.
     *
     * @param applicationDate application date
     */
    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     * Returns {@link Advertisement} for which the request was sent,
     * of the {@code Request} object.
     *
     * @return advertisement
     */
    public Advertisement getAdvertisement() {
        return advertisement;
    }

    /**
     * Sets {@link Advertisement} for which the request was sent,
     * to the {@code Request} object.
     *
     * @param advertisement {@link Advertisement}
     */
    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    /**
     * Returns isApproved of the {@code Request} object.
     *
     * @return isApproved
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Sets isApproved to the {@code Request} object.
     *
     * @param approved approve status
     */
    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    /**
     * Compares this request to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * Request} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code Request} against
     * @return {@code true} if the given object represents a {@code Request}
     * equivalent to this request, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (id != request.id) return false;
        if (isApproved != request.isApproved) return false;
        if (user != null ? !user.equals(request.user) : request.user != null) return false;
        if (startDate != null ? !startDate.equals(request.startDate) : request.startDate != null) return false;
        if (endDate != null ? !endDate.equals(request.endDate) : request.endDate != null) return false;
        if (applicationDate != null ? !applicationDate.equals(request.applicationDate) : request.applicationDate != null)
            return false;
        return advertisement != null ? advertisement.equals(request.advertisement) : request.advertisement == null;
    }

    /**
     * Returns a hash code for this {@code Request} object.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (applicationDate != null ? applicationDate.hashCode() : 0);
        result = 31 * result + (advertisement != null ? advertisement.hashCode() : 0);
        result = 31 * result + (isApproved ? 1 : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code Request} object.
     *
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", applicationDate=").append(applicationDate);
        sb.append(", advertisement=").append(advertisement);
        sb.append(", isApproved=").append(isApproved);
        sb.append('}');
        return sb.toString();
    }
}
