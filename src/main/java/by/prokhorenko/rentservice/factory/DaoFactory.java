package by.prokhorenko.rentservice.factory;

import by.prokhorenko.rentservice.dao.FlatAddressDao;
import by.prokhorenko.rentservice.dao.FlatDao;
import by.prokhorenko.rentservice.dao.FlatDescriptionDao;
import by.prokhorenko.rentservice.dao.FlatPhotoDao;
import by.prokhorenko.rentservice.dao.impl.FlatAddressDaoImpl;
import by.prokhorenko.rentservice.dao.impl.FlatDaoImpl;
import by.prokhorenko.rentservice.dao.impl.FlatDescriptionDaoImpl;
import by.prokhorenko.rentservice.dao.impl.FlatPhotoDaoImpl;
import by.prokhorenko.rentservice.dao.UserDao;
import by.prokhorenko.rentservice.dao.impl.UserDaoImpl;

public class DaoFactory {

    private final UserDao userDao;
    private final FlatAddressDao flatAddressDao;
    private final FlatDescriptionDao flatDescriptionDao;
    private final FlatDao flatDao;
    private final FlatPhotoDao flatPhotoDao;

    private DaoFactory(){
        userDao = UserDaoImpl.getInstance();
        flatAddressDao = FlatAddressDaoImpl.getInstance();
        flatDescriptionDao = FlatDescriptionDaoImpl.getInstance();
        flatDao = FlatDaoImpl.getInstance();
        flatPhotoDao = FlatPhotoDaoImpl.getInstance();

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
    public FlatAddressDao getFlatAddressDao(){
        return flatAddressDao;
    }
    public FlatDescriptionDao getFlatDescriptionDao(){
        return flatDescriptionDao;
    }
    public FlatDao getFlatDao(){
        return flatDao;
    }
    public FlatPhotoDao getFlatPhotoDao(){
        return flatPhotoDao;
    }

}
