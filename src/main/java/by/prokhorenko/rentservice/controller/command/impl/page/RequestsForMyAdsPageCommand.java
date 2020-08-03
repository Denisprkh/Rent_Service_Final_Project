package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.request.RequestService;
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
        try {
            List<Request> requestsOnUsersAds = requestService.findRequestsOnUsersAdvertisement(usersId);
            request.setAttribute(Attribute.REQUESTS_ON_USERS_ADVERTISEMENTS_LIST,requestsOnUsersAds);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(DisPathType.FORWARD, PagePath.REQUESTS_FOR_MY_APS_PAGE);
    }
}
