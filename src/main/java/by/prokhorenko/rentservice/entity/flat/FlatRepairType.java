package by.prokhorenko.rentservice.entity.flat;

public enum FlatRepairType {
    EUROPEAN_QUALITY("european quality"),
    REDECORATING("redecorating"),
    DESIGN("design"),
    WITHOUT("without");

    private final String repairType;

    FlatRepairType(String repairType){
        this.repairType = repairType;
    }
}
