package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.dao.AdvertisementDao;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.entity.user.UserRole;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfilePageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;
    public ProfilePageCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Attribute.USER);
            int usersId = user.getId();
            List<Advertisement> usersAdvertisements = advertisementService.findAdvertisementsByUserId(usersId);
            request.setAttribute(Attribute.ADVERTISEMENT_LIST,usersAdvertisements);
        } catch (ServiceException e) {
            LOG.error(e);
        }

        return new Router(DisPathType.FORWARD,PagePath.USER_PROFILE);
    }
}
