package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.dao.AdvertisementDao;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.entity.user.UserRole;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import by.prokhorenko.rentservice.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfilePageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;
    private RequestService requestService;
    public ProfilePageCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Attribute.USER);
            int usersId = user.getId();
            List<Advertisement> usersAdvertisements = advertisementService.findAdvertisementsByUserId(usersId);
            List<Request> usersRequests = requestService.findAllUsersRequests(usersId);
            List<Request> requestsOnUsersAdvertisements = requestService.findRequestsOnUsersAdvertisement(usersId);
            session.setAttribute(Attribute.USERS_ADVERTISEMENT_LIST,usersAdvertisements);
            session.setAttribute(Attribute.USERS_REQUEST_LIST,usersRequests);
            session.setAttribute(Attribute.REQUESTS_ON_USERS_ADVERTISEMENTS_LIST,requestsOnUsersAdvertisements);
            session.removeAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE);
        } catch (ServiceException e) {
            LOG.error(e);
        }

        return new Router(PagePath.USER_PROFILE);
    }
}
