package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestsForMyAdsPageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private RequestService requestService;
    public RequestsForMyAdsPageCommand(){
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Attribute.USER);
        int usersId = user.getId();
        String page;
        try {
            List<Request> requestsOnUsersAds = requestService.findRequestsOnUsersAdvertisement(usersId);
            request.setAttribute(Attribute.REQUESTS_ON_USERS_ADVERTISEMENTS_LIST,requestsOnUsersAds);
            page = PagePath.REQUESTS_FOR_MY_ADS_PAGE;
        } catch (ServiceException e) {
            LOG.error(e);
            page = PagePath.SERVER_ERROR_PAGE;
        }
        return new Router(DisPathType.FORWARD, page);
    }
}
