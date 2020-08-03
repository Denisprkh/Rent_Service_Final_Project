package by.prokhorenko.rentservice.entity;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum which represents users roles.
 */
public enum UserRole{

    /**
     * Admin role.
     */
    ADMIN(1),

    /**
     * User role.
     */
    USER(2),

    /**
     * Guest role.
     */
    GUEST(3);

    /**
     * Role id in database.
     */
    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public int getUserRolesId(){
        return id;
    }

    /**
     * Returns role by id
     * @param id
     * @return Optional<UserRole>
     */
    public static Optional<UserRole> getUserRoleById(int id){
        UserRole[] userRoles = UserRole.values();
        Optional<UserRole> role = Arrays.stream(userRoles).filter(o -> o.getUserRolesId() == id).findAny();
        return role;
    }

}
