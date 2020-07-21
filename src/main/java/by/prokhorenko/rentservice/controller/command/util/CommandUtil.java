package by.prokhorenko.rentservice.controller.command.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommandUtil {

    private static final String RESOURCE_BUNDLE_NAME = "pagecontent.pagecontent";
    private static final String DEFAULT_LOCALE = "en";

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
}
