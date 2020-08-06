package by.prokhorenko.rentservice.controller.command.util;

import by.prokhorenko.rentservice.controller.command.ResourceBundleMessageKey;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public static final int RECORDS_PER_PAGE = 10;
    public static final String REFERER_HEADER = "referer";
    private static final Logger LOG = LogManager.getLogger();

    public static void defineErrorMessageFromUsersDataValidations(HttpServletRequest request,
                                                                  Map<String,Boolean> usersDataValidations){
        String falseKey = defineFalseKey(usersDataValidations);
        HttpSession session = request.getSession();
        switch (falseKey){
            case EMAIL: request.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleMessageKey.EMAIL_INCORRECT_ERROR_MESSAGE);
                break;
            case FIRST_NAME: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleMessageKey.FIRST_NAME_INCORRECT_ERROR_MESSAGE);
                break;
            case LAST_NAME: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleMessageKey.LAST_NAME_INCORRECT_ERROR_MESSAGE);
                break;
            case PASSWORD: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleMessageKey.PASSWORD_INCORRECT_ERROR_MESSAGE);
                break;
            case PHONE_NUMBER: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleMessageKey.PHONE_INCORRECT_ERROR_MESSAGE);
                break;
            case EMAIL_IS_UNIQUE: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleMessageKey.EMAIL_IS_NOT_UNIQUE_ERROR_MESSAGE);
                break;
            case PHONE_IS_UNIQUE: session.setAttribute(Attribute.INCORRECT_DATA_ERROR_MESSAGE,
                    ResourceBundleMessageKey.PHONE_IS_NOT_UNIQUE_ERROR_MESSAGE);
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

    public static void definePaginationContext(HttpServletRequest request, int fullRecordsQuantity) {
        int currentPage = Integer.parseInt(request.getParameter(RequestParameter.PAGINATION_CURRENT_PAGE));
        int allPagesAmount = fullRecordsQuantity / RECORDS_PER_PAGE;
        if((fullRecordsQuantity-(allPagesAmount * RECORDS_PER_PAGE)) % RECORDS_PER_PAGE > 0){
            allPagesAmount++;
        }
        LOG.debug(fullRecordsQuantity);
        LOG.debug(allPagesAmount);
        request.setAttribute(Attribute.PAGINATION_PAGES_QUANTITY,allPagesAmount);
        request.setAttribute(Attribute.PAGINATION_CURRENT_PAGE,currentPage);
    }

    public static int defineStartOfRecords(HttpServletRequest request){
        int currentPage = Integer.parseInt(request.getParameter(RequestParameter.PAGINATION_CURRENT_PAGE));
        int start = (currentPage-1) * RECORDS_PER_PAGE;
        return start;
    }

}
