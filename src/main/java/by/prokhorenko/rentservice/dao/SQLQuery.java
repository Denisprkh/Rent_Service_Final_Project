package by.prokhorenko.rentservice.dao;

public class SQLQuery {

    private SQLQuery(){}

    public static final String ADD_USER = "INSERT INTO users (first_name,last_name,email,password,phone) " +
            "VALUES (?,?,?,?,?)";
    public static final String UPDATE_USER_BY_ID ="UPDATE users us SET us.first_name = ?, " +
            "us.last_name = ?, us.email = ?, us.password = ?, us.phone = ? WHERE us.users_id = ?";
    public static final String FIND_USER_BY_ID = "SELECT users_id,first_name,last_name,email,password," +
            "phone,users_role_id,log_in_token,is_banned FROM users WHERE users_id = ?";
    public static final String FIND_ALL_USERS = "SELECT users_id,first_name,last_name,email,password,phone," +
            "users_role_id,log_in_token,is_banned FROM users";
    public static final String FIND_USER_BY_EMAIL = "SELECT users_id,first_name,last_name,email,password,phone," +
            "users_role_id,log_in_token,is_banned FROM users WHERE email = ? ";
    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT users_id,first_name,last_name,email,password,"+
            "phone,users_role_id,log_in_token,is_banned FROM users WHERE email = ? AND password = ?";
    public static final String UPDATE_USERS_ROLE = "UPDATE users us SET us.users_role_id = ? WHERE us.users_id = ?";
    public static final String UPDATE_USERS_BAN_STATUS_TRUE = "UPDATE users us SET us.is_banned =" +
            " TRUE WHERE us.users_id = ?";
    public static final String UPDATE_USERS_BAN_STATUS_FALSE = "UPDATE users us SET us.is_banned =" +
            " FALSE WHERE us.users_id = ?";
    public static final String FIND_USER_BY_PHONE = "SELECT users_id,first_name,last_name,email,password,phone," +
            "users_role_id,log_in_token,is_banned FROM users WHERE phone = ? ";

    public static final String ADD_FLAT_ADDRESS = "INSERT INTO flats_address (city,district,street,house) " +
            "VALUES (?,?,?,?)";
    public static final String FIND_ALL_FLAT_ADDRESSES = "SELECT flats_address_id,city,district,street,house " +
            "FROM flats_address";
    public static final String FIND_FLAT_ADDRESS_BY_FULL_DATA = "SELECT flats_address_id,city,district,street,house " +
            "FROM flats_address WHERE city = ? AND district = ? AND street = ? AND house = ? ";
    public static final String FIND_FLAT_ADDRESS_BY_ID = "SELECT flats_address_id,city,district,street,house " +
            "FROM flats_address WHERE flats_address_id = ?";
    public static final String UPDATE_FLAT_ADDRESS_BY_ID = "UPDATE flats_address fa SET fa.city = ?, " +
            "fa.district = ?, fa.street = ?, fa.house = ? WHERE fa.flats_address_id = ?";

    public static final String ADD_FLAT_DESCRIPTION = "INSERT INTO flats_description (rooms,living_area,has_furniture," +
            "has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets,repair,users_description)" +
            "VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String FIND_ALL_FLAT_DESCRIPTIONS = "SELECT (flats_description_id,rooms,living_area," +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets,repair," +
            "users_description) FROM flats_description";
    public static final String FIND_FLAT_DESCRIPTION_BY_ID = "SELECT (flats_description_id,rooms,living_area," +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets," +
            "repair,users_description) FROM flats_description WHERE flats_description_id = ?";
    public static final String UPDATE_FLAT_DESCRIPTION_BY_ID = "UPDATE flats_description fd SET fd.rooms = ?," +
            "fd.living_area = ?, fd.has_furniture = ? fd.has_home_appliciances = ?, df.has_the_internet = ?," +
            "fd.possible_with_childs = ?, fd.possible_with_pets = ?, fd.repair = ?, fd.users_description = ?";
    public static final String FIND_FLAT_DESCRIPTION_BY_ROOMS_AMOUNT = "SELECT (flats_description_id,rooms,living_area, " +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets, repair," +
            "users_description) FROM flats_description WHERE rooms = ?";
    public static final String FIND_FLAT_DESCRIPTION_BY_LIVING_AREA = "SELECT (flats_description_id,rooms,living_area, " +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets, repair," +
            "users_description) FROM flats_description WHERE living_area = ?";
    public static final String FIND_FLAT_DESCRIPTION_BY_REPAIR_TYPE = "SELECT (flats_description_id,rooms,living_area, " +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets, repair," +
            "users_description) FROM flats_description WHERE repair = ?";

}
