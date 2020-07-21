package by.prokhorenko.rentservice.service.advertisement.impl;

import by.prokhorenko.rentservice.controller.command.ResourceBundleErrorMessageKey;
import by.prokhorenko.rentservice.dao.AdvertisementDao;
import by.prokhorenko.rentservice.dao.FlatPhotoDao;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserAdvertisementDataHandler;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.validator.AdvertisementValidator;

import java.io.IOException;
import java.util.List;

public class AdvertisementServiceImpl implements AdvertisementService {

    private static final AdvertisementServiceImpl INSTANCE = new AdvertisementServiceImpl();
    public static AdvertisementService getInstance(){
        return INSTANCE;
    }
    private AdvertisementServiceImpl(){}

    @Override
    public List<Advertisement> findAllAdvertisements(int start,int total) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            List<Advertisement> allAdvertisements = advertisementDao.findAll(start,total);
            return allAdvertisements;
        } catch (IOException | DaoException e) {
            throw new ServiceException("Finding",e);
        }
    }

    @Override
    public int findAdvertisementsQuantity() throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            int advertisementsQuantity = advertisementDao.findQuantity();
            return advertisementsQuantity;
        } catch (IOException | DaoException e) {
            throw new ServiceException("Finding",e);
        }
    }

    @Override
    public boolean addAnAdvertisement(Advertisement advertisement) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            return advertisementDao.add(advertisement).isPresent();
        } catch (IOException | DaoException e) {
            throw new ServiceException("Adding error",e);
        }
    }

    @Override
    public List<Boolean> defineIncorrectData(UserAdvertisementDataHandler handler) throws ServiceException {
        AdvertisementValidator advertisementValidator = AdvertisementValidator.getInstance();
        List<Boolean> validations = advertisementValidator.validateAdvertisementsData(handler);
        if(validations.contains(Boolean.FALSE)){
            throw new ServiceException(ResourceBundleErrorMessageKey.ADVERTISEMENT_INVALID_INPUT_VALUES);
        }
        return validations;
    }

}
