package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.builder.UserBuilder;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.ResourceBundleErrorMessage;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;
import by.prokhorenko.rentservice.util.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.HttpConstraintElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.Map;
import java.util.Optional;

public class SignUpCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL_IS_UNIQUE = "emailIsUnique";
    private static final String PHONE_IS_UNIQUE = "phoneIsUnique";
    private UserService userService;

    public SignUpCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        router.setRedirect();
        try {
            User user = buildUserFromRequest(request);
            Map<String,Boolean> usersDataValidations = userService.defineUsersIncorrectData(user);
            LOG.debug(usersDataValidations.values());
            if(!usersDataValidations.containsValue(Boolean.FALSE)){
                userService.signUp(user);
                router.setPage(PagePath.ACTIVATION_INFO);
            }else{
               defineErrorMessageFromValidations(request,usersDataValidations);
               router.setPage(PagePath.SIGN_UP);
            }
        } catch (ServiceException e) {
            LOG.error(e);
            router.setPage(PagePath.SIGN_UP);
        }
        return router;
    }

    private String defineFalseKey(Map<String,Boolean> map){
        Optional<String> falseKey = map.entrySet()
                .stream()
                .filter(entry -> Boolean.FALSE.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
        return falseKey.get();
    }

    private void defineErrorMessageFromValidations(HttpServletRequest request,Map<String,Boolean> usersDataValidations){
        HttpSession session = request.getSession();
        String falseKey = defineFalseKey(usersDataValidations);
        switch (falseKey){
            case EMAIL: session.setAttribute(Attribute.ERROR_MESSAGE,
                    ResourceBundleErrorMessage.EMAIL_INCORRECT_ERROR_MESSAGE);
                break;
            case FIRST_NAME: session.setAttribute(Attribute.ERROR_MESSAGE,
                    ResourceBundleErrorMessage.FIRST_NAME_INCORRECT_ERROR_MESSAGE);
                break;
            case LAST_NAME: session.setAttribute(Attribute.ERROR_MESSAGE,
                    ResourceBundleErrorMessage.LAST_NAME_INCORRECT_ERROR_MESSAGE);
                break;
            case PASSWORD: session.setAttribute(Attribute.ERROR_MESSAGE,
                    ResourceBundleErrorMessage.PASSWORD_INCORRECT_ERROR_MESSAGE);
                break;
            case PHONE_NUMBER: session.setAttribute(Attribute.ERROR_MESSAGE,
                    ResourceBundleErrorMessage.PHONE_INCORRECT_ERROR_MESSAGE);
                break;
            case EMAIL_IS_UNIQUE: session.setAttribute(Attribute.ERROR_MESSAGE,
                    ResourceBundleErrorMessage.EMAIL_IS_NOT_UNIQUE_ERROR_MESSAGE);
                break;
            case PHONE_IS_UNIQUE: session.setAttribute(Attribute.ERROR_MESSAGE,
                    ResourceBundleErrorMessage.PHONE_IS_NOT_UNIQUE_ERROR_MESSAGE);
                break;
        }
    }

    private User buildUserFromRequest(HttpServletRequest request){
        User user = new UserBuilder()
                .buildFirstName(request.getParameter(JspParameter.PARAM_FIRST_NAME))
                .buildLastName(request.getParameter(JspParameter.PARAM_LAST_NAME))
                .buildEmail(request.getParameter(JspParameter.PARAM_EMAIL))
                .buildPassword(request.getParameter(JspParameter.PARAM_PASSWORD))
                .buildPhone(request.getParameter(JspParameter.PARAM_PHONE))
                .buildUser();
        return user;
    }
}

