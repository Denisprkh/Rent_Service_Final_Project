package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.entity.UserRole;

/**
 * Class for building {@link User} entity
 */
public class UserBuilder {

    /**
     * Id
     */
    private int id;

    /**
     * First name.
     */
    private String firstName;

    /**
     * Last name
     */
    private String lastName;

    /**
     * Email
     */
    private String email;

    /**
     * Password
     */
    private String password;

    /**
     * Phone
     */
    private String phone;

    /**
     * User role
     */
    private UserRole userRole;

    /**
     * Activation status
     */
    private boolean isActivated;

    /**
     * Ban status
     */
    private boolean isBanned;

    /**
     * Initializes a newly created {@code UserBuilder} object so that it represents
     * an empty UserBuilder entity.
     */
    public UserBuilder() {
    }

    /**
     * Returns ban status
     *
     * @return isBanned
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Sets ban status and returns {@code UserBuilder} object with built field
     *
     * @param isBanned
     * @return {@see UserBuilder}
     */
    public UserBuilder buildIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
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
     * Sets id and returns {@code UserBuilder} object with built field
     *
     * @param id
     * @return {@see UserBuilder}
     */
    public UserBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns firstName
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName and returns {@code UserBuilder} object with built field
     *
     * @param firstName
     * @return {@see UserBuilder}
     */
    public UserBuilder buildFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Returns lastName
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName and returns {@code UserBuilder} object with built field
     *
     * @param lastName
     * @return {@see UserBuilder}
     */
    public UserBuilder buildLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Returns email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email and returns {@code UserBuilder} object with built field
     *
     * @param email
     * @return {@see UserBuilder}
     */
    public UserBuilder buildEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Returns password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password and returns {@code UserBuilder} object with built field
     *
     * @param password
     * @return {@see UserBuilder}
     */
    public UserBuilder buildPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Returns phone
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone and returns {@code UserBuilder} object with built field
     *
     * @param phone
     * @return {@see UserBuilder}
     */
    public UserBuilder buildPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Returns userRole
     *
     * @return userRole
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets userRole and returns {@code UserBuilder} object with built field
     *
     * @param userRole
     * @return {@see UserBuilder}
     */
    public UserBuilder buildUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    /**
     * Returns activation status
     *
     * @return isActivated
     */
    public boolean getActivated() {
        return isActivated;
    }

    /**
     * Sets isActivated and returns {@code UserBuilder} object with built field
     *
     * @param isActivated
     * @return {@see UserBuilder}
     */
    public UserBuilder buildIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
        return this;
    }

    /**
     * Returns newly created @see User with built fields
     *
     * @return {@see User}
     */
    public User buildUser() {
        return new User(this);
    }
}
