package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
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

public class BanUserCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private UserService userService;
    public BanUserCommand(){
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        int banUsersId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        try {
            LOG.debug(banUsersId);
            userService.banUser(banUsersId);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(DisPathType.FORWARD,PagePath.ADMIN_PROFILE);
    }
}
