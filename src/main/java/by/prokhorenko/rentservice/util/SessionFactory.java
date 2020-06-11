package by.prokhorenko.rentservice.util;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionFactory {
    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty("mail.user.name");
        String userPassword = configProperties.getProperty("mail.user.password");
        return Session.getDefaultInstance(configProperties,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }

}
