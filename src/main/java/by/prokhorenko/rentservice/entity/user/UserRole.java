package by.prokhorenko.rentservice.entity.user;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ADMIN(1),
    GUEST(2),
    USER(3);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public int getUserRolesId(){
        return id;
    }

    public Optional<UserRole> getUserRoleById(int id){
        UserRole[] usersId = UserRole.values();
        Optional<UserRole> role = Arrays.stream(usersId).filter(o -> o.getUserRolesId() == id).findAny();
        return role;

    }

}
