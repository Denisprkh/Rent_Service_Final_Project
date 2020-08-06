package by.prokhorenko.rentservice.factory;

import by.prokhorenko.rentservice.service.AdvertisementService;
import by.prokhorenko.rentservice.service.impl.AdvertisementServiceImpl;
import by.prokhorenko.rentservice.service.FlatService;
import by.prokhorenko.rentservice.service.impl.FlatServiceImpl;
import by.prokhorenko.rentservice.service.RequestService;
import by.prokhorenko.rentservice.service.impl.RequestServiceImpl;
import by.prokhorenko.rentservice.service.UserService;
import by.prokhorenko.rentservice.service.impl.UserServiceImpl;

public class ServiceFactory {

    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final RequestService requestService;
    private final FlatService flatService;

    private ServiceFactory(){
        this.userService = UserServiceImpl.getInstance();
        this.advertisementService = AdvertisementServiceImpl.getInstance();
        this.requestService = RequestServiceImpl.getInstance();
        this.flatService = FlatServiceImpl.getInstance();
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

    public FlatService getFlatService(){return flatService;}
}
