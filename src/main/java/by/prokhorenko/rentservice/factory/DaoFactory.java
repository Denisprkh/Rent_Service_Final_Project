package by.prokhorenko.rentservice.factory;

import by.prokhorenko.rentservice.dao.*;
import by.prokhorenko.rentservice.dao.impl.*;

public class DaoFactory {

    private final UserDao userDao;
    private final FlatAddressDao flatAddressDao;
    private final FlatDescriptionDao flatDescriptionDao;
    private final FlatDao flatDao;
    private final FlatPhotoDao flatPhotoDao;
    private final AdvertisementDao advertisementDao;
    private final RequestDao requestDao;

    private DaoFactory() {
        this.userDao = UserDaoImpl.getInstance();
        this.flatAddressDao = FlatAddressDaoImpl.getInstance();
        this.flatDescriptionDao = FlatDescriptionDaoImpl.getInstance();
        this.flatDao = FlatDaoImpl.getInstance();
        this.flatPhotoDao = FlatPhotoDaoImpl.getInstance();
        this.advertisementDao = AdvertisementDaoImpl.getInstance();
        this.requestDao = RequestDaoImpl.getInstance();

    }

    private static class DaoFactoryHolder {
        private static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return DaoFactory.DaoFactoryHolder.INSTANCE;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public FlatAddressDao getFlatAddressDao() {
        return flatAddressDao;
    }

    public FlatDescriptionDao getFlatDescriptionDao() {
        return flatDescriptionDao;
    }

    public FlatDao getFlatDao() {
        return flatDao;
    }

    public FlatPhotoDao getFlatPhotoDao() {
        return flatPhotoDao;
    }

    public AdvertisementDao getAdvertisementDao() {
        return advertisementDao;
    }

    public RequestDao getRequestDao() {
        return requestDao;
    }

}
