/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaMail;

import config.Config;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author gaurav
 */
public class JavaMail implements Config {

    /**
     *
     * @param to
     * @param subject
     * @param body
     * @return
     */
    public String sendMail(String to, String subject, String body) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");//
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);//
        props.setProperty("mail.smtp.port", "465"); //
        props.setProperty("mail.smtp.socketFactory.port", "465");//
        props.put("mail.smtp.auth", "true");//

        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM, EMAIL_PASSWORD);
                }
            });

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
            return "Successful";
        } catch (MessagingException e) {
            return e.toString();
        }
    }
}
