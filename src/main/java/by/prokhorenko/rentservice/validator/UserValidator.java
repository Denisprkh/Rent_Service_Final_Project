package by.prokhorenko.rentservice.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String EMAIL_REGEX = "[a-zA-z0-9_.-]{1,40}@[a-zA-Z0-9_-]{2,40}\\.[a-z]{2,10}";
    private static final String FIRST_NAME_REGEX = "^[a-zA-Zа-яА-Я]{2,45}$";
    private static final String LAST_NAME_REGEX = "^[a-zA-Zа-яА-Я]{2,45}$";
    private static final String PASSWORD_REGEX = ".{1,32}";
    private static final String PHONE_REGEX = "^(\\+375\\([\\d]{2}\\)[\\d]{3}\\-[\\d]{2}\\-[\\d]{2})$";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";

    private UserValidator() {

    }

    private static class UserValidatorHolder {
        private static UserValidator INSTANCE = new UserValidator();
    }

    public static UserValidator getInstance() {
        return UserValidator.UserValidatorHolder.INSTANCE;
    }

    public Map<String, Boolean> validateDataForSignUp(String email, String firstName, String lastName, String password,
                                                      String phoneNumber) {
        Map<String, Boolean> validations = validateDataForUpdate(email,firstName,lastName,phoneNumber);
        validations.put(PASSWORD,passwordIsCorrect(password));
        return validations;
    }

    public Map<String, Boolean> validateDataForUpdate(String email,String firstName,String lastName,String phoneNumber) {
        Map<String, Boolean> validations = new HashMap<>();
        validations.put(EMAIL, emailIsCorrect(email));
        validations.put(FIRST_NAME, firstNameIsCorrect(firstName));
        validations.put(LAST_NAME, lastNameIsCorrect(lastName));
        validations.put(PHONE_NUMBER, phoneIsCorrect(phoneNumber));

        return validations;
    }


    public boolean emailIsCorrect(String email) {
        return !(isNullOrEmpty(email) || !isStringMatchesRegex(email,EMAIL_REGEX));
    }

    private boolean firstNameIsCorrect(String firstName) {
        return !(isNullOrEmpty(firstName) || !isStringMatchesRegex(firstName,FIRST_NAME_REGEX));
    }

    private boolean lastNameIsCorrect(String lastName) {
        return !(isNullOrEmpty(lastName) || !isStringMatchesRegex(lastName,LAST_NAME_REGEX));
    }

    public boolean passwordIsCorrect(String password) {
        return !(isNullOrEmpty(password) || !isStringMatchesRegex(password,PASSWORD_REGEX));
    }

    private boolean phoneIsCorrect(String phone) {
        return !(isNullOrEmpty(phone) || !isStringMatchesRegex(phone,PHONE_REGEX));
    }

    private boolean isStringMatchesRegex(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean isNullOrEmpty(String data) {
        boolean isNullOrEmpty = data == null || data.isEmpty();
        return isNullOrEmpty;
    }

}
