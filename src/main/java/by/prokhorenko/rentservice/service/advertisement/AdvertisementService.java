package by.prokhorenko.rentservice.service.advertisement;

import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserAdvertisementDataHandler;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> findAllAdvertisements(int start,int total) throws ServiceException;
    int findAdvertisementsQuantity() throws ServiceException;
    boolean addAnAdvertisement(Advertisement advertisement) throws  ServiceException;
    List<Boolean> defineIncorrectData(UserAdvertisementDataHandler handler) throws ServiceException;
}
