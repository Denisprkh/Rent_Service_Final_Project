package by.prokhorenko.rentservice.util;

import by.prokhorenko.rentservice.controller.command.ResourceBundleMessageKey;
import by.prokhorenko.rentservice.entity.User;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for building mail.
 */
public class MailBodyBuilder {
    private static final String RESOURCE_NAME = "pagecontent.pagecontent";
    private static final Locale DEFAULT_LOCALE = new Locale("en");

    /**
     * Link by which user will pass to activate his account.
     */
    private static final String LINK_FOR_CONFIRMATION = "http://localhost:8080/RentSetviceProkhorenkoDM_war" +
            "/controller?command=confirmRegistration&id=";

    private MailBodyBuilder() {
    }

    /**
     * Returns localized text from resource.
     * @param language locale name
     * @param key resource bundle name
     * @return localized {@code String}
     */
    private static String getLocalizedMessageFromResources(String language, String key) {
        Locale locale;
        if (language != null) {
            locale = new Locale(language);
        } else {
            locale = DEFAULT_LOCALE;
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
        return resourceBundle.getString(key);
    }

    /**
     * Builds mails subject.
     * @param locale locale
     * @return localized mail subject
     */
    public static String buildEmailSubject(String locale) {
        String emailSubject = getLocalizedMessageFromResources(locale, ResourceBundleMessageKey.EMAIL_SUBJECT);
        return emailSubject;
    }

    /**
     * Builds mail body.
     * @param user {@link User} who will receive the mail
     * @param locale locale name
     * @return localized mail body
     */
    public static String buildEmailBody(User user, String locale) {
        String emailBody = String.format(getLocalizedMessageFromResources(locale, ResourceBundleMessageKey.EMAIL_BODY),
                user.getFirstName(), LINK_FOR_CONFIRMATION + user.getId());
        return emailBody;
    }


}
