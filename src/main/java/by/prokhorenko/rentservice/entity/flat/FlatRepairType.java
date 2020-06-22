package by.prokhorenko.rentservice.entity.flat;

import by.prokhorenko.rentservice.entity.user.UserRole;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public enum FlatRepairType implements Serializable {
    EUROPEAN_QUALITY("european quality"),
    REDECORATING("redecorating"),
    DESIGN("design"),
    WITHOUT("without");

    private final String repairType;

    FlatRepairType(String repairType){
        this.repairType = repairType;
    }

    public String getRepairType(){
        return repairType;
    }

    public static Optional<FlatRepairType> getRepairTypeByName(String repairType){
        FlatRepairType[] repairTypes = FlatRepairType.values();
        Optional<FlatRepairType> type = Arrays.stream(repairTypes).filter(o -> o.getRepairType().equals(repairType)).findAny();
        return type;
    }
}
