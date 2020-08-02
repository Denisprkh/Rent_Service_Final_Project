package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.builder.UserBuilder;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class UpdateUserInfoCommand implements Command {

    private static final String FULL_NAME_SPLITTER = "\\s";
    private static final int FIRST_NAME_INDEX = 0;
    private static final int LAST_NAME_INDEX = 1;
    private static final String REFERER_HEADER = "referer";
    private static final Logger LOG = LogManager.getLogger();
    private UserService userService;
    public UpdateUserInfoCommand(){
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        int usersId = user.getId();
        Router router = new Router();
        String previousPage = request.getHeader(REFERER_HEADER);
        router.setPage(previousPage);
        String updatedFullName = request.getParameter(RequestParameter.UPDATED_USER_FULL_NAME);
        String updatedEmail = request.getParameter(RequestParameter.UPDATED_USER_EMAIL);
        String updatedPhone = request.getParameter(RequestParameter.UPDATED_USER_PHONE);
        String[] firstNameAndLastName = splitFullName(updatedFullName);
        String updatedFirstName = firstNameAndLastName[FIRST_NAME_INDEX].trim();
        String updatedLastName = firstNameAndLastName[LAST_NAME_INDEX].trim();
        try {
            Map<String,Boolean> usersDataValidations = userService.defineUsersIncorrectDataForUpdate(updatedEmail,
                    updatedFirstName,updatedLastName,updatedPhone,usersId);
            if(!usersDataValidations.containsValue(Boolean.FALSE)){
                User updatedUser = buildUser(usersId,updatedFirstName,updatedLastName,
                        updatedEmail,updatedPhone);
                updatedUser = userService.updateUserInfo(updatedUser);
               session.setAttribute(Attribute.USER,updatedUser);
            }else{
                CommandUtil.defineErrorMessageFromValidations(request,usersDataValidations);
            }
        } catch (ServiceException e) {
            if(e.getCause() instanceof DaoException){
                router.setForward();
                router.setPage(PagePath.SERVER_ERROR_PAGE);
            }
        }
        return router;
    }

    private String[] splitFullName(String fullName){
        String[] firstAndLastName = fullName.split(FULL_NAME_SPLITTER);
        return firstAndLastName;
    }

    private User buildUser(int id,String firstName,String lastName,String email,String phone){
        User user = new UserBuilder()
                .buildId(id)
                .buildFirstName(firstName)
                .buildLastName(lastName)
                .buildEmail(email)
                .buildPhone(phone)
                .buildUser();
        return user;
    }
}
