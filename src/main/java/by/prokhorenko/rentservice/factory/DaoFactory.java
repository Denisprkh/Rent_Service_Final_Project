package by.prokhorenko.rentservice.factory;

import by.prokhorenko.rentservice.dao.user.UserDao;
import by.prokhorenko.rentservice.dao.user.impl.UserDaoImpl;

public class DaoFactory {

    private final UserDao userDao;

    private DaoFactory(){
        userDao = new UserDaoImpl();
    }

    private static class DaoFactoryHolder{
        private static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance(){
        return DaoFactory.DaoFactoryHolder.INSTANCE;
    }

    public UserDao getUserDao(){
        return userDao;
    }

}
