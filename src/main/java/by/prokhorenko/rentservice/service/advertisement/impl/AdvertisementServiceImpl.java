package by.prokhorenko.rentservice.service.advertisement.impl;

import by.prokhorenko.rentservice.controller.command.ResourceBundleErrorMessageKey;
import by.prokhorenko.rentservice.dao.AdvertisementDao;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.AdvertisementDataHandler;
import by.prokhorenko.rentservice.entity.advertisement.UserChoiceDataHandler;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import by.prokhorenko.rentservice.validator.AdvertisementValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class AdvertisementServiceImpl implements AdvertisementService {

    private static final AdvertisementServiceImpl INSTANCE = new AdvertisementServiceImpl();
    public static AdvertisementService getInstance(){
        return INSTANCE;
    }
    private static final Logger LOG = LogManager.getLogger();
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
            LOG.debug(e.getMessage() + " " + e.getCause());
            throw new ServiceException("Adding error",e);
        }
    }

    @Override
    public List<Boolean> defineIncorrectData(AdvertisementDataHandler handler) throws ServiceException {
        AdvertisementValidator advertisementValidator = AdvertisementValidator.getInstance();
        List<Boolean> validations = advertisementValidator.validateAdvertisementsData(handler);
        if(validations.contains(Boolean.FALSE)){
            throw new ServiceException(ResourceBundleErrorMessageKey.ADVERTISEMENT_INVALID_INPUT_VALUES);
        }
        return validations;
    }

    @Override
    public Advertisement findAdvertisementById(int id) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            Advertisement advertisement = advertisementDao.findById(id).orElseThrow(ServiceException::new);
            return advertisement;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advertisement> findAdvertisementsByUserId(int usersId) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()){
            List<Advertisement> usersAdvertisements = advertisementDao.findAllAdvertisementsByUsersId(usersId);
            return usersAdvertisements;
        } catch (IOException | DaoException e) {
           throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAdvertisement(int advertisementsId) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()){
            boolean isDeleted = advertisementDao.setAdvertisementStatusInvisible(advertisementsId);
            return isDeleted;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findFilteredAdvertisementsQuantity(UserChoiceDataHandler userChoiceDataHandler) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            int filteredAdvertisementsQuantity = advertisementDao.findFilteredAdvertisementsQuantity(userChoiceDataHandler);
            return filteredAdvertisementsQuantity;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advertisement> findAdvertisementsByUsersChoice(UserChoiceDataHandler userChoiceDataHandler,int start,
                                                               int total) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            List<Advertisement> filteredAdvertisements = advertisementDao.findAdvertisementsByUsersChoice
                    (userChoiceDataHandler,start,total);
            return filteredAdvertisements;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateAdvertisement(Advertisement advertisement) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            boolean wasUpdated = advertisementDao.update(advertisement).isPresent();
            return wasUpdated;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int findNotRentedAdvertisementsQuantity() throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            int notRentedAdsQuantity = advertisementDao.findNotInRentAdvertisementsQuantity();
            return notRentedAdsQuantity;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advertisement> findAllNotRentedAdvertisements(int start, int total) throws ServiceException {
        try(AdvertisementDao advertisementDao = DaoFactory.getInstance().getAdvertisementDao()) {
            List<Advertisement> notRentedAds = advertisementDao.findAllNotRentedAdvertisements(start,total);
            return notRentedAds;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

}
