package by.prokhorenko.rentservice.entity.flat;

import by.prokhorenko.rentservice.entity.user.User;

import java.io.Serializable;

public class Flat implements Serializable {

    private int id;
    private boolean isFree;
    private FlatDescription flatDescription;
    private FlatAddress flatAddress;
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public FlatDescription getFlatDescription() {
        return flatDescription;
    }

    public void setFlatDescription(FlatDescription flatDescription) {
        this.flatDescription = flatDescription;
    }

    public FlatAddress getFlatAddress() {
        return flatAddress;
    }

    public void setFlatAddress(FlatAddress flatAddress) {
        this.flatAddress = flatAddress;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;

        if (id != flat.id) return false;
        if (isFree != flat.isFree) return false;
        if (flatDescription != null ? !flatDescription.equals(flat.flatDescription) : flat.flatDescription != null)
            return false;
        if (flatAddress != null ? !flatAddress.equals(flat.flatAddress) : flat.flatAddress != null) return false;
        return owner != null ? owner.equals(flat.owner) : flat.owner == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isFree ? 1 : 0);
        result = 31 * result + (flatDescription != null ? flatDescription.hashCode() : 0);
        result = 31 * result + (flatAddress != null ? flatAddress.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
