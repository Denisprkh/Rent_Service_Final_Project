package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MyAdsPageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;

    public MyAdsPageCommand() {
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Attribute.USER);
        int usersId = user.getId();
        String page;
        try {
            List<Advertisement> usersAdvertisements = advertisementService.findAdvertisementsByUserId(usersId);
            request.setAttribute(Attribute.USERS_ADVERTISEMENT_LIST, usersAdvertisements);
            page = PagePath.MY_ADS_PAGE;
        } catch (ServiceException e) {
            LOG.error(e);
            page = PagePath.SERVER_ERROR_PAGE;
        }
        return new Router(DisPathType.FORWARD, page);
    }
}
