package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import by.prokhorenko.rentservice.service.flat.FlatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetFlatIsInRentCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private FlatService flatService;
    private AdvertisementService advertisementService;
    public SetFlatIsInRentCommand(){
        this.flatService = ServiceFactory.getInstance().getFlatService();
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int flatsId = Integer.parseInt(request.getParameter(RequestParameter.FLAT_ID));
        User user = (User) session.getAttribute(Attribute.USER);
        int usersId = user.getId();
        int advertisementId = Integer.parseInt(request.getParameter(RequestParameter.ADVERTISEMENT_ID));
        try {
            Advertisement advertisement = advertisementService.findAdvertisementById(advertisementId);
            if(advertisement.getAuthor().getId() == usersId) {
                flatService.setFlatIsInRent(flatsId);
            }
            List<Advertisement> usersAdvertisements = advertisementService.findAdvertisementsByUserId(usersId);
            session.setAttribute(Attribute.USERS_ADVERTISEMENT_LIST,usersAdvertisements);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(PagePath.USER_PROFILE);
    }
}
