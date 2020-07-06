package by.prokhorenko.rentservice.builder;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.entity.user.UserRole;

public class UserBuilder {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private UserRole userRole;
    private boolean isActivated;
    private boolean isBanned;

    public boolean isBanned() {
        return isBanned;
    }

    public UserBuilder buildIsBanned(boolean banned) {
        isBanned = banned;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserBuilder buildFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserBuilder buildLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBuilder buildEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBuilder buildPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserBuilder buildPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserBuilder buildUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public boolean getActivated() {
        return isActivated;
    }

    public UserBuilder buildIsActivated(boolean logInToken) {
        this.isActivated = logInToken;
        return this;
    }

    public User buildUser(){
        return new User(this);
    }
}
