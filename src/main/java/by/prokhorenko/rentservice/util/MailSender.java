package by.prokhorenko.rentservice.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;
    public MailSender(String sendToEmail, String mailSubject, String mailText,
                      Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }
    public void send() {
        try {
            initMessage();
            // sending mail
            Transport.send(message);
        } catch (AddressException e) {
            System.err.print("Invalid address: " + sendToEmail + "  " + e);
            // in log file
        } catch (MessagingException e) {
            System.err.print("Error generating or sending message: " + e);
            // in log file
        }
    }
    private void initMessage() throws MessagingException {
        // mail session object
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        // create a mailing object
        message = new MimeMessage(mailSession);
        // loading parameters into the mail message object
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

}
