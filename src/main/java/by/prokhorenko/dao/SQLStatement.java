package by.prokhorenko.dao;

public enum SQLStatement {

    ADD_USER("INSERT INTO users (first_name,last_name,email,password,phone) VALUES (?,?,?,?,?)"),
    UPDATE_USER_BY_ID("UPDATE users us SET us.first_name = ?, us.last_name = ?, us.email = ? us.password = ?," +
            "us.phone = ? "),
    FIND_USER_BY_ID("SELECT users_id,first_name,last_name,email,password,phone,users_role_id FROM users" +
            "WHERE users_id = ? "),
    FIND_ALL_USERS("SELECT users_id,first_name,last_name,email,password,phone,users_role_id FROM users"),
    FIND_USER_BY_EMAIL("SELECT users_id,first_name,last_name,email,password,phone,users_role_id FROM users" +
            "WHERE email = ? "),
    FIND_PASSWORD_BY_EMAIL("SELECT password FROM users WHERE email = ?"),
    UPDATE_USERS_ROLE("UPDATE users us SET us.users_role_id = ? WHERE us.users_id = ?");



    private String query;

    SQLStatement(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }
}
