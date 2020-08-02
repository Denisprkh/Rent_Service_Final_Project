package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.builder.UserBuilder;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandName;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class SignUpCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private UserService userService;

    public SignUpCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            String firstName = request.getParameter(RequestParameter.USER_FIRST_NAME);
            String lastName = request.getParameter(RequestParameter.USER_LAST_NAME);
            String email = request.getParameter(RequestParameter.USER_EMAIL);
            String password = request.getParameter(RequestParameter.USER_PASSWORD);
            String phone = request.getParameter(RequestParameter.USER_PHONE);
            Map<String,Boolean> usersDataValidations = userService.defineUsersIncorrectData(email,firstName,lastName,
                    password,phone);
            if(!usersDataValidations.containsValue(Boolean.FALSE)){
                User user = buildUser(firstName,lastName,email,password,phone);
                userService.signUp(user);
                String redirectUrl = buildRedirectUrl(request, CommandName.ACTIVATION_INFO_PAGE.getCommandName());
                router.setPage(redirectUrl);
                session.removeAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE);
            }else{
               CommandUtil.defineErrorMessageFromValidations(request,usersDataValidations);
               router.setForward();
               router.setPage(PagePath.SIGN_UP);
            }
        } catch (ServiceException e) {
            LOG.error(e);
            router.setForward();
            router.setPage(PagePath.SIGN_UP);
        }
        return router;
    }

    private User buildUser(String firstName,String lastName,String email,String password,String phone){
        User user = new UserBuilder()
                .buildFirstName(firstName)
                .buildLastName(lastName)
                .buildEmail(email)
                .buildPassword(password)
                .buildPhone(phone)
                .buildUser();
        return user;
    }
}

