package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.builder.UserBuilder;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;
import by.prokhorenko.rentservice.util.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.Attribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

public class SignUpCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();

    private UserService userService;

    public SignUpCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new UserBuilder()
                    .buildFirstName(request.getParameter(JspParameter.PARAM_FIRST_NAME))
                    .buildLastName(request.getParameter(JspParameter.PARAM_LAST_NAME))
                    .buildEmail(request.getParameter(JspParameter.PARAM_EMAIL))
                    .buildPassword(request.getParameter(JspParameter.PARAM_PASSWORD))
                    .buildPhone(request.getParameter(JspParameter.PARAM_PHONE))
                    .buildUser();
            String page;
            if(userService.signUp(user).isPresent()) {
                page = PagePath.ACTIVATION_INFO;
                MailSender mailSender = new MailSender();

                mailSender.send("Confirm registration","<a href = http://localhost:8080/RentSetviceProkhorenkoDM_war/controller?command=CONFIRM_REGISTRATION&" +
                        "id="+user.getId() +">Confirm</a>", user.getEmail());
            }else{
                page = PagePath.SIGN_UP;
            }
            return page;
        } catch (ServiceException e) {
            LOG.error(e);
            return PagePath.SIGN_UP;
        }
    }
}

