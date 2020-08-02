package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandName;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {
    private UserService userService;
    private static final Logger LOG = LogManager.getLogger();

    public SignInCommand(){
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            String email = request.getParameter(RequestParameter.USER_EMAIL);
            String password = request.getParameter(RequestParameter.USER_PASSWORD);
            User user = userService.signIn(email,password);
            session.setAttribute(Attribute.USER,user);
            session.setAttribute(Attribute.USER_ROLE,user.getUserRole());
            String redirectUrl = buildRedirectUrl(request, CommandName.MAIN_PAGE.getCommandName());
            router.setPage(redirectUrl);
            LOG.debug(request.getServerName()+request.getServerPort()+request.getContextPath()+request.getServletPath());
        } catch (ServiceException e) {
            LOG.error(e);
            request.setAttribute(Attribute.SIGN_IN_ERROR_MESSAGE,e.getMessage());
            router.setForward();
            router.setPage(PagePath.SIGN_IN);
        }
        return router;
    }
}
