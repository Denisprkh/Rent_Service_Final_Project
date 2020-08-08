package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.*;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ConfirmRegistrationCommand implements Command {

    private UserService userService;
    private static final Logger LOG = LogManager.getLogger();

    public ConfirmRegistrationCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int activationUsersId = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID));
        int usersId = (Integer) request.getSession().getAttribute(Attribute.ACTIVATION_USERS_ID);
        try {
            if (usersId == activationUsersId) {
                userService.activateUser(activationUsersId);
                String redirectUrl = buildRedirectUrl(request, CommandName.SIGN_IN_PAGE.getCommandName());
                router = new Router(redirectUrl);
                request.setAttribute(Attribute.ACTIVATION_INFO, ResourceBundleMessageKey.ACCOUNT_WAS_ACTIVATED);
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
