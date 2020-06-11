package by.prokhorenko.rentservice.entity.user;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ADMIN(1),
    USER(2),
    GUEST(3);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public int getUserRolesId(){
        return id;
    }

    public static Optional<UserRole> getUserRoleById(int id){
        UserRole[] usersId = UserRole.values();
        Optional<UserRole> role = Arrays.stream(usersId).filter(o -> o.getUserRolesId() == id).findAny();
        return role;
    }

}
