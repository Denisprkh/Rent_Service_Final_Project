package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.builder.UserBuilder;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.Attribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements Command {

    private static final String PARAM_FIRST_NAME = "firstName";
    private static final String PARAM_LAST_NAME = "lastName";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_PHONE = "phone";
    private static final Logger LOG = LogManager.getLogger();

    private UserService userService;

    public SignUpCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            User user = new UserBuilder()
                    .buildFirstName(request.getParameter(PARAM_FIRST_NAME))
                    .buildLastName(request.getParameter(PARAM_LAST_NAME))
                    .buildEmail(request.getParameter(PARAM_EMAIL))
                    .buildPassword(request.getParameter(PARAM_PASSWORD))
                    .buildPhone(request.getParameter(PARAM_PHONE))
                    .buildUser();
            user = userService.signUp(user);
            LOG.info("User " + user + " signed up");
            return PagePath.MAIN;
        } catch (ServiceException e) {
            LOG.info(e);
            return PagePath.SIGN_UP;
        }
    }
}

