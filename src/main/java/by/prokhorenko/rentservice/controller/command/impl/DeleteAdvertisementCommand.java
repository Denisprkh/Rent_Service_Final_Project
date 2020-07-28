package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.AppenderRef;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteAdvertisementCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;
    public DeleteAdvertisementCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        int usersId = user.getId();

        int advertisementsId = Integer.parseInt(request.getParameter(RequestParameter.ADVERTISEMENT_ID));
        try {
            List<Advertisement> allUsersAdvertisements = advertisementService.findAdvertisementsByUserId(usersId);
            Advertisement advertisement = advertisementService.findAdvertisementById(advertisementsId);
            if(allUsersAdvertisements.contains(advertisement)){
                advertisementService.deleteAdvertisement(advertisementsId);
            }
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(PagePath.INDEX);
    }
}
