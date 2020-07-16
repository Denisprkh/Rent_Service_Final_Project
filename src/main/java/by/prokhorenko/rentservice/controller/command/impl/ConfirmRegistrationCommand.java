package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmRegistrationCommand implements Command {

    private UserService userService;
    private static final Logger LOG = LogManager.getLogger();

    public ConfirmRegistrationCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        try {
            int id = Integer.parseInt(request.getParameter(JspParameter.PARAM_ID));
            String page;
            if (userService.activateUser(id)) {
                page = PagePath.SIGN_IN;
            }else {
                page = PagePath.SIGN_UP;
            }
            router.setPage(page);
            router.setRedirect();
        }catch (ServiceException e){
            LOG.error(e);
        }
        return router;
    }
}
