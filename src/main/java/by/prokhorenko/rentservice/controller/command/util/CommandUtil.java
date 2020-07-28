package by.prokhorenko.rentservice.controller.command.util;

import by.prokhorenko.rentservice.controller.command.ResourceBundleErrorMessageKey;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class CommandUtil {

    private static final String RESOURCE_BUNDLE_NAME = "pagecontent.pagecontent";
    private static final String DEFAULT_LOCALE = "en";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL_IS_UNIQUE = "emailIsUnique";
    private static final String PHONE_IS_UNIQUE = "phoneIsUnique";

    public static String extractLocalizedMessage(String lang, String key){
        Locale locale;
        if(lang != null){
            locale = new Locale(lang);
        }else{
            locale = new Locale(DEFAULT_LOCALE);
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME,locale);
        String message = resourceBundle.getString(key);
        return message;
    }

    public static void defineErrorMessageFromValidations(HttpServletRequest request, Map<String,Boolean> usersDataValidations){
        HttpSession session = request.getSession();
        String falseKey = defineFalseKey(usersDataValidations);
        switch (falseKey){
            case EMAIL: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleErrorMessageKey.EMAIL_INCORRECT_ERROR_MESSAGE);
                break;
            case FIRST_NAME: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleErrorMessageKey.FIRST_NAME_INCORRECT_ERROR_MESSAGE);
                break;
            case LAST_NAME: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleErrorMessageKey.LAST_NAME_INCORRECT_ERROR_MESSAGE);
                break;
            case PASSWORD: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleErrorMessageKey.PASSWORD_INCORRECT_ERROR_MESSAGE);
                break;
            case PHONE_NUMBER: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleErrorMessageKey.PHONE_INCORRECT_ERROR_MESSAGE);
                break;
            case EMAIL_IS_UNIQUE: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleErrorMessageKey.EMAIL_IS_NOT_UNIQUE_ERROR_MESSAGE);
                break;
            case PHONE_IS_UNIQUE: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleErrorMessageKey.PHONE_IS_NOT_UNIQUE_ERROR_MESSAGE);
                break;
        }
    }

    private static String defineFalseKey(Map<String,Boolean> map){
        Optional<String> falseKey = map.entrySet()
                .stream()
                .filter(entry -> Boolean.FALSE.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
        return falseKey.get();
    }


}
