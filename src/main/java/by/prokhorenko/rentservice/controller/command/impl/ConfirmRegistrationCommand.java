package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.ResourceBundleMessageKey;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
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
        String page = null;
        try {
            int id = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID));
            if (userService.activateUser(id)) {
                page = PagePath.SIGN_IN;
                request.setAttribute(Attribute.ACTIVATION_INFO, ResourceBundleMessageKey.ACCOUNT_WAS_ACTIVATED);
            } else {
                page = PagePath.SIGN_UP;
            }
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(DisPathType.FORWARD,page);
    }
}
