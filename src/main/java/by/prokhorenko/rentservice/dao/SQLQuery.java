package by.prokhorenko.rentservice.dao;

public class SQLQuery {

    private SQLQuery(){}

    public static final String ADD_USER = "INSERT INTO users (first_name,last_name,email,password,phone) " +
            "VALUES (?,?,?,?,?)";
    public static final String UPDATE_USER_BY_ID ="UPDATE users us SET us.first_name = ?, " +
            "us.last_name = ?, us.email = ? us.password = ?, us.phone = ? ";
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

}
