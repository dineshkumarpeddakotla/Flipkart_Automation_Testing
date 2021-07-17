/*
 *Purpose : Class is implemented for sending the mail
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 12-07-2021
 */
package com.flipkartapplication.utility;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class MailUtil {
    private static final String USER_NAME = "dineshkumar.icon.dk@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static final String PASSWORD = "1324124318"; // GMail password
    private static final String RECIPIENT = "dineshkumar.icon.dk@gmail.com";

    //this method is used to send mail by giving the details of body subject, to address, attachment path
    public static void sendMail() {

        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = "Test Report";
        String body = "Flipkart Testing Report";
        String reportPath = "C:\\Users\\dinnu\\Testing\\FlipkartAutomationTesting\\TestReport\\Test-Automaton-Report.html";

        sendFromGMail(to, subject, body,reportPath);
    }

    private static void sendFromGMail(String[] to, String subject, String body, String reportPath) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME,PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(MailUtil.USER_NAME));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress address : toAddress) {
                message.addRecipient(Message.RecipientType.TO, address);
            }
            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("My multipart text");

            //Attachment body part.
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(reportPath);

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(attachment);
            message.setText(subject);
            message.setText(body);

            Transport.send(message, message.getAllRecipients());
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}