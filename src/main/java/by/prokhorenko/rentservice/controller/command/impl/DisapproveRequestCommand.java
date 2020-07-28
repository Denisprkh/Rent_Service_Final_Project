package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

public class DisapproveRequestCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private RequestService requestService;
    public DisapproveRequestCommand(){
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        int requestId = Integer.parseInt(request.getParameter(RequestParameter.REQUEST_ID));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        int usersId = user.getId();
        String page = null;
        try {
            if(requestService.disApproveRequestById(requestId)){
                page = PagePath.USER_PROFILE;
                List<Request> requestsOnUsersAdvertisements = requestService.findRequestsOnUsersAdvertisement(usersId);
                session.setAttribute(Attribute.REQUESTS_ON_USERS_ADVERTISEMENTS_LIST,requestsOnUsersAdvertisements);
            }else{
                page = PagePath.SERVER_ERROR_PAGE;
            }
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(page);
    }
}
