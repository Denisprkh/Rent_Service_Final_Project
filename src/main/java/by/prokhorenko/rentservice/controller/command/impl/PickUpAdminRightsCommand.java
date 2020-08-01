package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PickUpAdminRightsCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private UserService userService;
    public PickUpAdminRightsCommand(){
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        int usersId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        try {
            userService.pickUpAdminRightsById(usersId);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(PagePath.ALL_USERS);
    }
}
