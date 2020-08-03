package by.prokhorenko.rentservice.entity;

import by.prokhorenko.rentservice.builder.UserBuilder;

import java.io.Serializable;

/**
 * Class for User entity. Has next properties:
 * <b>id</b>,<b>firstName</b>,<b>lastName</b>,<b>email</b>,<b>password</b>,<b>phone</b>,<b>userRole</b>
 * <b>isActivated</b>,<b>isBanned</b>
 */
public class User implements Serializable {

    /**
     * Property - users id
     */
    private int id;

    /**
     * Property - users first name
     */
    private String firstName;

    /**
     * Property - users last name
     */
    private String lastName;

    /**
     * Property - users email
     */
    private String email;

    /**
     * Property - users password
     */
    private String password;

    /**
     * Property - users phone
     */
    private String phone;

    /**
     * Property - userRole
     */
    private UserRole userRole;

    /**
     * Property - users activation status
     */
    private boolean isActivated;

    /**
     * Property - user ban status
     */
    private boolean isBanned;

    /**
     * Initializes a newly created {@code User} object with
     * fields which have been built with {@link UserBuilder}
     *
     * @param userBuilder
     */

    public User(UserBuilder userBuilder) {
        this.id = userBuilder.getId();
        this.firstName = userBuilder.getFirstName();
        this.lastName = userBuilder.getLastName();
        this.email = userBuilder.getEmail();
        this.password = userBuilder.getPassword();
        this.phone = userBuilder.getPhone();
        this.userRole = userBuilder.getUserRole();
        this.isActivated = userBuilder.getActivated();
        this.isBanned = userBuilder.isBanned();
    }

    /**
     * Initializes a newly created {@code User} object with parameters
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param phone
     */
    public User(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    /**
     * Initializes a newly created {@code User} object with parameters
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param phone
     */
    public User(int id, String firstName, String lastName, String email, String password, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    /**
     * Initializes a newly created {@code User} object so that it represents
     * an empty User entity.
     */
    public User() {

    }

    /**
     * Returns user ID of an {@code User} object
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets user ID to an {@code User} object
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns user firstName of an {@code User} object
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets user firsName to an {@code User} object
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns user lastName of an {@code User} object
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets user lastName to an {@code User} object
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns user email of an {@code User} object
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user email to an {@code User} object
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns user password of an {@code User} object
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user password to an {@code User} object
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns user phone of an {@code User} object
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets user phone to an {@code User} object
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns user userRole of an {@code User} object
     *
     * @return userRole
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets userRole to an {@code User} object
     *
     * @param userRole
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Returns whether user is activated of an {@code User} object
     *
     * @return isActivated
     */
    public boolean isActivated() {
        return isActivated;
    }

    /**
     * Sets whether user is activated to an {@code User} object
     *
     * @param activated
     */
    public void setActivated(boolean activated) {
        this.isActivated = activated;
    }

    /**
     * Returns whether user is banned of an {@code User} object
     *
     * @return isBanned
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Sets whether user is banned to an {@code User} object
     *
     * @param banned
     */
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * Compares this user to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * User} object that represents the same sequence of parameters as this
     * object.
     *
     * @param o The object to compare this {@code User} against
     * @return {@code true} if the given object represents a {@code User}
     * equivalent to this user, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isActivated != user.isActivated) return false;
        if (isBanned != user.isBanned) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return userRole == user.userRole;
    }

    /**
     * Returns a hash code for this {@code User} object
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (isActivated ? 1 : 0);
        result = 31 * result + (isBanned ? 1 : 0);
        return result;
    }

    /**
     * Returns a {@code String} representation for this {@code User} object
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append(", isActivated=").append(isActivated);
        sb.append(", isBanned=").append(isBanned);
        sb.append('}');
        return sb.toString();
    }
}
