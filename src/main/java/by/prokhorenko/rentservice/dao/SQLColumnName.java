package by.prokhorenko.rentservice.dao;

public class SQLColumnName {

    private SQLColumnName(){}

    public static final String USERS_ID_COLUMN_NAME = "users_id";
    public static final String USERS_FIRST_NAME_COLUMN_NAME = "first_name";
    public static final String USERS_LAST_NAME_COLUMN_NAME = "last_name";
    public static final String USERS_EMAIL_COLUMN_NAME = "email";
    public static final String USERS_PASSWORD_COLUMN_NAME = "password";
    public static final String USERS_PHONE_COLUMN_NAME = "phone";
    public static final String USERS_ROLE_ID_COLUMN_NAME = "users_role_id";
    public static final String USERS_LOG_IN_TOKEN_COLUMN_NAME = "log_in_token";
    public static final String USERS_IS_BANNED_COLUMN_NAME = "is_banned";

    public static final String FLAT_ADDRESS_ID_COLUMN_NAME = "flats_address_id";
    public static final String FLAT_ADDRESS_CITY_COLUMN_NAME = "city";
    public static final String FLAT_ADDRESS_DISTRICT_COLUMN_NAME = "district";
    public static final String FLAT_ADDRESS_STREET_COLUMN_NAME = "street";
    public static final String FLAT_ADDRESS_HOUSE_COLUMN_NAME = "house";

    public static final String FLAT_DESCRIPTION_ID_COLUMN_NAME = "flats_description_id";
    public static final String FLAT_DESCRIPTION_ROOMS_COLUMN_NAME = "rooms";
    public static final String FLAT_DESCRIPTION_LIVING_AREA_COLUMN_NAME = "living_area";
    public static final String FLAT_DESCRIPTION_HAS_FURNITURE_COLUMN_NAME = "has_furniture";
    public static final String FLAT_DESCRIPTION_HAS_HOME_APPLIANCES_COLUMN_NAME = "has_home_appliciances";
    public static final String FLAT_DESCRIPTION_HAS_THE_INTERNET_COLUMN_NAME = "has_the_internet";
    public static final String FLAT_DESCRIPTION_POSSIBLE_WITH_CHILD_COLUMN_NAME = "possible_with_childs";
    public static final String FLAT_DESCRIPTION_POSSIBLE_WITH_PETS_COLUMN_NAME = "possible_with_pets";
    public static final String FLAT_DESCRIPTION_REPAIR_COLUMN_NAME = "repair";
    public static final String FLAT_DESCRIPTION_USERS_DESCRIPTION_COLUMN_NAME = "users_description";
}
