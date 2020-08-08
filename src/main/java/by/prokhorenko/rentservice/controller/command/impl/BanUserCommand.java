package by.prokhorenko.rentservice.controller.command.impl;


import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class BanUserCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private UserService userService;

    public BanUserCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int banUsersId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        Router router;
        try {
            userService.banUser(banUsersId);
            String redirectUrl = request.getHeader(CommandUtil.REFERER_HEADER);
            router = new Router(redirectUrl);
        } catch (ServiceException e) {
            LOG.error(e);
            router = new Router(DisPathType.FORWARD, PagePath.SERVER_ERROR_PAGE);
        }
        return router;
    }
}
