package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class UnBanUserCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();
    private UserService userService;
    public UnBanUserCommand(){
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int banUsersId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        String page = request.getHeader(CommandUtil.REFERER_HEADER);
        try {
            userService.unBanUser(banUsersId);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(page);
    }
}
