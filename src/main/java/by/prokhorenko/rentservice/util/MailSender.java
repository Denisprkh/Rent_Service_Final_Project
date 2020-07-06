package by.prokhorenko.rentservice.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class MailSender {

    private String username;
    private String password;
    private Properties props;
    private static final String MAIL_PROPERTIES = "mail/mail.properties";

    public MailSender() {
        props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream(MAIL_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String subject, String text, String toEmail){
        username = props.getProperty("mail.user.name");
        password = props.getProperty("mail.user.password");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
