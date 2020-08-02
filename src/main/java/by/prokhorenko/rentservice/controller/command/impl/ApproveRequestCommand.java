package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ApproveRequestCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private RequestService requestService;
    public ApproveRequestCommand(){
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int requestsId = Integer.parseInt(request.getParameter(RequestParameter.REQUEST_ID));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        int usersId = user.getId();
        String page = null;
        try {
            if(requestService.ApproveRequestById(requestsId)){
                page = request.getHeader(CommandUtil.REFERER_HEADER);
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
