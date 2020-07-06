package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {
    private UserService userService;
    private static final Logger LOG = LogManager.getLogger();

    public SignInCommand(){
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Signing in");
        try {
            String email = request.getParameter(JspParameter.PARAM_EMAIL);
            String password = request.getParameter(JspParameter.PARAM_PASSWORD);
            User user = userService.signIn(email,password);
            HttpSession session = request.getSession();
            session.setAttribute(JspParameter.PARAM_USER,user);
            return PagePath.MAIN;
        } catch (ServiceException e) {
           LOG.error(e);
           return PagePath.SIGN_IN;
        }
    }
}
