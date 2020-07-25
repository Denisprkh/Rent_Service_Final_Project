package by.prokhorenko.rentservice.factory;

import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import by.prokhorenko.rentservice.service.advertisement.impl.AdvertisementServiceImpl;
import by.prokhorenko.rentservice.service.request.RequestService;
import by.prokhorenko.rentservice.service.request.impl.RequestServiceImpl;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;

public class ServiceFactory {

    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final RequestService requestService;

    private ServiceFactory(){
        userService = UserServiceImpl.getInstance();
        advertisementService = AdvertisementServiceImpl.getInstance();
        requestService = RequestServiceImpl.getInstance();
    }

    private static class ServiceFactoryHolder{
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance(){
        return ServiceFactory.ServiceFactoryHolder.INSTANCE;
    }

    public UserService getUserService(){
        return userService;
    }
    public AdvertisementService getAdvertisementService(){
        return advertisementService;
    }
    public RequestService getRequestService(){return  requestService; }
}
