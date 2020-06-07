package by.prokhorenko.rentservice.validator;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private UserValidator(){

    }

    private static class UserValidatorHolder{
        private static UserValidator INSTANCE = new UserValidator();
    }

    public static UserValidator getInstance(){
        return UserValidator.UserValidatorHolder.INSTANCE;
    }

    private static final String EMAIL_REGEX = "[a-zA-z0-9_.-]{1,40}@[a-zA-z0-9_-]{2,40}\\.[a-z]{2,10}";
    private static final String FIRST_NAME_REGEX = "^[a-zA-Zа-яА-Я]{2,45}$";
    private static final String LAST_NAME_REGEX = "^[a-zA-Zа-яА-Я-]{2,45}$";
    private static final String PASSWORD_REGEX = ".{1,30}";
    private static final String PHONE_REGEX = "^(\\+375[0-9]{2}[0-9]{7})$";
    private static final Logger LOG = LogManager.getLogger();

    public boolean userIsCorrectForSignUp(User user){
        boolean isCorrect = false;
        if(user != null){
            isCorrect = emailIsCorrect(user.getEmail()) &&
                    firstNameIsCorrect(user.getFirstName()) &&
                    lastNameIsCorrect(user.getLastName()) &&
                    passwordIsCorrect(user.getPassword()) &&
                    phoneIsCorrect(user.getPhone()) &&
                    emailIsNotInUse(user.getEmail());
        }
        return isCorrect;
    }

    public boolean userIsCorrectForSignIn(User user){
        boolean isCorrect = false;
        if(user != null){
            isCorrect = emailIsCorrect(user.getEmail()) &&
                        passwordIsCorrect(user.getPassword());
        }
        return isCorrect;
    }

    public boolean emailIsCorrect(String email){
        return (email != null && isStringMatchesRegex(email,EMAIL_REGEX));
    }

    private boolean firstNameIsCorrect(String firstName){
        return (firstName != null && isStringMatchesRegex(firstName,FIRST_NAME_REGEX));
    }

    private boolean lastNameIsCorrect(String lastName){
        return (lastName != null && isStringMatchesRegex(lastName,LAST_NAME_REGEX));
    }

    public boolean passwordIsCorrect(String password){
        return (password != null && isStringMatchesRegex(password,PASSWORD_REGEX));
    }

    private boolean phoneIsCorrect(String phone){
        return (phone != null && isStringMatchesRegex(phone,PHONE_REGEX));
    }

    private boolean isStringMatchesRegex(String string, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean emailIsNotInUse(String email){
        UserService userService = ServiceFactory.getInstance().getUserService();
        boolean emailIsNotInUse = false;
        try {
           emailIsNotInUse = userService.findUserByEmail(email) == null;
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return emailIsNotInUse;
    }
}
