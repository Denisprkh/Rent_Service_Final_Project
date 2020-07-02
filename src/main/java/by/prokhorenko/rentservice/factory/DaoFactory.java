package by.prokhorenko.rentservice.factory;

import by.prokhorenko.rentservice.dao.*;
import by.prokhorenko.rentservice.dao.impl.*;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;

public class DaoFactory {

    private final UserDao userDao;
    private final FlatAddressDao flatAddressDao;
    private final FlatDescriptionDao flatDescriptionDao;
    private final FlatDao flatDao;
    private final FlatPhotoDao flatPhotoDao;
    private final AdvertisementDao advertisementDao;

    private DaoFactory(){
        userDao = UserDaoImpl.getInstance();
        flatAddressDao = FlatAddressDaoImpl.getInstance();
        flatDescriptionDao = FlatDescriptionDaoImpl.getInstance();
        flatDao = FlatDaoImpl.getInstance();
        flatPhotoDao = FlatPhotoDaoImpl.getInstance();
        advertisementDao = AdvertisementDaoImpl.getInstance();

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
    public AdvertisementDao getAdvertisementDao(){return advertisementDao; }

}
