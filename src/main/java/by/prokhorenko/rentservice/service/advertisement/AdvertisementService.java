package by.prokhorenko.rentservice.service.advertisement;

import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.AdvertisementDataHandler;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> findAllAdvertisements(int start,int total) throws ServiceException;
    int findAdvertisementsQuantity() throws ServiceException;
    boolean addAnAdvertisement(Advertisement advertisement) throws  ServiceException;
    List<Boolean> defineIncorrectData(AdvertisementDataHandler handler) throws ServiceException;
    Advertisement findAdvertisementById(int id) throws ServiceException;
    List<Advertisement> findAdvertisementsByUserId(int usersId) throws ServiceException;
    boolean deleteAdvertisement(int advertisementsId) throws ServiceException;
}
