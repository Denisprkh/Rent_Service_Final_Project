package by.prokhorenko.rentservice.factory;

import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;

public class ServiceFactory {

    private final UserService userService;

    private ServiceFactory(){
        userService = new UserServiceImpl();
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
}
