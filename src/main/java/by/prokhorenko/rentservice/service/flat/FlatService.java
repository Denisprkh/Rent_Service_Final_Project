package by.prokhorenko.rentservice.service.flat;

import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.exception.ServiceException;

public interface FlatService {
    boolean setFlatIsInRent(int flatsId) throws ServiceException;
    boolean setFlatIsNotInRent(int flatsId) throws ServiceException;
}
