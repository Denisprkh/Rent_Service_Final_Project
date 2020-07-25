package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.flat.FlatPhoto;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AdvertisementPageCommand implements Command {
    private AdvertisementService advertisementService;
    private static final Logger LOG = LogManager.getLogger();
    public AdvertisementPageCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        router.setForward();
        try {
            int advertisementsId = Integer.parseInt(request.getParameter(RequestParameter.ADVERTISEMENT_ID));
            Advertisement advertisement = advertisementService.findAdvertisementById(advertisementsId);
            request.getSession().setAttribute(Attribute.ADVERTISEMENT,advertisement);
            LOG.debug(advertisement);
            router.setPage(PagePath.ADVERTISEMENT);
        } catch (ServiceException e) {
           LOG.error(e);
           if(e.getCause() instanceof DaoException){
               router.setPage(PagePath.SERVER_ERROR_PAGE);
           }
        }
        return router;
    }


}
