package by.prokhorenko.rentservice.dao.constant;

public class SqlQuery {

    private SqlQuery(){}

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
            "fd.possible_with_childs = ?, fd.possible_with_pets = ?, fd.repair = ?, fd.users_description = ? " +
            "WHERE fd.flats_description_id = ?";
    public static final String FIND_FLAT_DESCRIPTION_BY_ROOMS_AMOUNT = "SELECT flats_description_id,rooms,living_area, " +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets, repair," +
            "users_description FROM flats_description WHERE rooms = ?";
    public static final String FIND_FLAT_DESCRIPTION_BY_LIVING_AREA = "SELECT flats_description_id,rooms,living_area, " +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets, repair," +
            "users_description FROM flats_description WHERE living_area = ?";
    public static final String FIND_FLAT_DESCRIPTION_BY_REPAIR_TYPE = "SELECT flats_description_id,rooms,living_area, " +
            "has_furniture, has_home_appliciances,has_the_internet,possible_with_childs,possible_with_pets, repair," +
            "users_description FROM flats_description WHERE repair = ?";

    public static final String ADD_FLAT = "INSERT INTO flats (flats_description_id,flats_address_id) VALUES (?,?)";
    public static final String FIND_ALL_FLATS = "SELECT flats.flats_id, flats.is_free, flats.flats_description_id," +
            "flats.flats_address_id, flats_address.flats_address_id,flats_address.city," +
            "flats_address.district, flats_address.street, flats_address.house, flats_description.flats_description_id," +
            "flats_description.rooms,flats_description.living_area,flats_description.has_furniture," +
            "flats_description.has_home_appliciances,flats_description.has_the_internet," +
            "flats_description.possible_with_childs,flats_description.possible_with_pets, flats_description.repair," +
            "flats_description.users_description FROM flats,flats_address,flats_description WHERE " +
            "flats.flats_description_id = flats_description.flats_description_id AND " +
            "flats.flats_address_id = flats_address.flats_address_id ";
    public static final String FIND_FLAT_BY_ID = "SELECT flats.flats_id, flats.is_free, flats.flats_description_id," +
            "flats.flats_address_id, flats_address.flats_address_id,flats_address.city," +
            "flats_address.district, flats_address.street, flats_address.house, flats_description.flats_description_id," +
            "flats_description.rooms,flats_description.living_area,flats_description.has_furniture," +
            "flats_description.has_home_appliciances,flats_description.has_the_internet," +
            "flats_description.possible_with_childs,flats_description.possible_with_pets, flats_description.repair," +
            "flats_description.users_description FROM flats,flats_address,flats_description WHERE " +
            "flats.flats_description_id = flats_description.flats_description_id AND " +
            "flats.flats_address_id = flats_address.flats_address_id AND flats.flats_id = ?";
    public static final String UPDATE_FLAT_BY_ID = "UPDATE flats fl SET fl.is_free = ? WHERE fl.flats_id = ?";

    public static final String FIND_FLAT_BY_FLATS_DESCRIPTION_ID = "SELECT flats.flats_id, flats.is_free, flats.flats_description_id," +
            "flats.flats_address_id, flats_address.flats_address_id,flats_address.city," +
            "flats_address.district, flats_address.street, flats_address.house, flats_description.flats_description_id," +
            "flats_description.rooms,flats_description.living_area,flats_description.has_furniture," +
            "flats_description.has_home_appliciances,flats_description.has_the_internet," +
            "flats_description.possible_with_childs,flats_description.possible_with_pets, flats_description.repair," +
            "flats_description.users_description FROM flats,flats_address,flats_description WHERE " +
            "flats.flats_description_id = flats_description.flats_description_id AND " +
            "flats.flats_address_id = flats_address.flats_address_id AND flats_description.flats_description_id = ?";

    public static final String FIND_FLAT_BY_FLATS_ADDRESS_ID = "SELECT flats.flats_id, flats.is_free, flats.flats_description_id," +
            "flats.flats_address_id, flats_address.flats_address_id,flats_address.city," +
            "flats_address.district, flats_address.street, flats_address.house, flats_description.flats_description_id," +
            "flats_description.rooms,flats_description.living_area,flats_description.has_furniture," +
            "flats_description.has_home_appliciances,flats_description.has_the_internet," +
            "flats_description.possible_with_childs,flats_description.possible_with_pets, flats_description.repair," +
            "flats_description.users_description FROM flats,flats_address,flats_description WHERE " +
            "flats.flats_description_id = flats_description.flats_description_id AND " +
            "flats.flats_address_id = flats_address.flats_address_id AND flats_address.flats_address_id = ?";

    public static final String ADD_FLATS_PHOTO = "INSERT INTO flats_photo(flats_id,photo) VALUES (?,?)";
    public static final String FIND_FLAT_PHOTO_BY_FLATS_ID = "SELECT flats_photo_id,flats_id,photo FROM flats_photo " +
            "WHERE flats_id = ?";
    public static final String FIND_FLAT_PHOTO_BY_ID = "SELECT flats_photo_id,flats_id,photo FROM flats_photo " +
            "WHERE flats_photo_id = ?";
    public static final String FIND_ALL_FLAT_PHOTO= "SELECT flats_photo_id,flats_id,photo FROM flats_photo";

    public static final String ADD_ADVERTISEMENT = "INSERT INTO advertisements (author_id,flats_id,title,price," +
            "date_of_creation) VALUES (?,?,?,?,?)";
    public static final String FIND_ALL_ADVERTISEMENTS = "SELECT flats.flats_id, flats.is_free, flats.flats_description_id," +
            "flats.flats_address_id, flats_address.flats_address_id,flats_address.city," +
            "flats_address.district, flats_address.street, flats_address.house, flats_description.flats_description_id," +
            "flats_description.rooms,flats_description.living_area,flats_description.has_furniture," +
            "flats_description.has_home_appliciances,flats_description.has_the_internet," +
            "flats_description.possible_with_childs,flats_description.possible_with_pets, flats_description.repair," +
            "flats_description.users_description, users.users_id, users.first_name, users.last_name, users.email," +
            "users.password, users.phone, users.users_role_id, users.log_in_token, users.is_banned, " +
            "advertisements.advertisements_id, advertisements.author_id, advertisements.flats_id, advertisements.title," +
            "advertisements.price, advertisements.date_of_creation FROM flats,flats_address,flats_description,users," +
            "advertisements WHERE flats.flats_description_id = flats_description.flats_description_id AND" +
            " flats.flats_description_id = flats_description.flats_description_id AND flats.flats_address_id = " +
            "flats_address.flats_address_id AND flats.flats_id = advertisements.flats_id AND users.users_id = " +
            "advertisements.author_id ";
    public static final String FIND_ADVERTISEMENT_BY_ID = "SELECT flats.flats_id, flats.is_free, flats.flats_description_id," +
            "flats.flats_address_id, flats_address.flats_address_id,flats_address.city," +
            "flats_address.district, flats_address.street, flats_address.house, flats_description.flats_description_id," +
            "flats_description.rooms,flats_description.living_area,flats_description.has_furniture," +
            "flats_description.has_home_appliciances,flats_description.has_the_internet," +
            "flats_description.possible_with_childs,flats_description.possible_with_pets, flats_description.repair," +
            "flats_description.users_description, users.users_id, users.first_name, users.last_name, users.email," +
            "users.password, users.phone, users.users_role_id, users.log_in_token, users.is_banned, " +
            "advertisements.advertisements_id, advertisements.author_id, advertisements.flats_id, advertisements.title," +
            "advertisements.price, advertisements.date_of_creation FROM flats,flats_address,flats_description,users," +
            "advertisements WHERE flats.flats_description_id = flats_description.flats_description_id AND" +
            " flats.flats_description_id = flats_description.flats_description_id AND flats.flats_address_id = " +
            "flats_address.flats_address_id AND flats.flats_id = advertisements.flats_id AND users.users_id = " +
            "advertisements.author_id AND advertisements.advertisements_id = ?";
    public static final String UPDATE_ADVERTISEMENT_BY_ID = "UPDATE advertisements ad SET ad.title = ?, " +
            "ad.price = ? WHERE advertisements.advertisements_id = ?";
    public static final String FIND_ADVERTISEMENTS_BY_USERS_ID = "SELECT flats.flats_id, flats.is_free, flats.flats_description_id," +
            "flats.flats_address_id, flats_address.flats_address_id,flats_address.city," +
            "flats_address.district, flats_address.street, flats_address.house, flats_description.flats_description_id," +
            "flats_description.rooms,flats_description.living_area,flats_description.has_furniture," +
            "flats_description.has_home_appliciances,flats_description.has_the_internet," +
            "flats_description.possible_with_childs,flats_description.possible_with_pets, flats_description.repair," +
            "flats_description.users_description, users.users_id, users.first_name, users.last_name, users.email," +
            "users.password, users.phone, users.users_role_id, users.log_in_token, users.is_banned, " +
            "advertisements.advertisements_id, advertisements.author_id, advertisements.flats_id, advertisements.title," +
            "advertisements.price, advertisements.date_of_creation FROM flats,flats_address,flats_description,users," +
            "advertisements WHERE flats.flats_description_id = flats_description.flats_description_id AND" +
            " flats.flats_description_id = flats_description.flats_description_id AND flats.flats_address_id = " +
            "flats_address.flats_address_id AND flats.flats_id = advertisements.flats_id AND users.users_id = " +
            "advertisements.author_id AND advertisements.author_id = ?";

    public static final String ADD_REQUEST = "INSERT INTO requests users_id,start_date,end_date,application_date " +
            "VALUES (?,?,?,?)";




}