package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandName;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ApproveRequestCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private RequestService requestService;

    public ApproveRequestCommand() {
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int requestsId = Integer.parseInt(request.getParameter(RequestParameter.REQUEST_ID));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        int usersId = user.getId();
        Router router;
        try {
            Request approvingRequest = requestService.findRequestById(requestsId);
            if (approvingRequest.getAdvertisement().getAuthor().getId() == usersId) {
                requestService.approveRequestById(requestsId);
                String redirectUrl = buildRedirectUrl(request, CommandName.REQUESTS_FOR_MY_ADS_PAGE.getCommandName());
                router = new Router(redirectUrl);
                List<Request> requestsOnUsersAdvertisements = requestService.findRequestsOnUsersAdvertisement(usersId);
                session.setAttribute(Attribute.REQUESTS_ON_USERS_ADVERTISEMENTS_LIST, requestsOnUsersAdvertisements);
            } else {
                router = new Router(DisPathType.FORWARD, PagePath.WRONG_REQUEST);
            }
        } catch (ServiceException e) {
            LOG.error(e);
            router = new Router(DisPathType.FORWARD, PagePath.SERVER_ERROR_PAGE);
        }
        return router;
    }
}
