/*
 *Purpose : Class is implemented for sending the mail
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 12-07-2021
 */
package com.flipkartapplication.utility;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
        String reportPath = "C:\\Users\\dinnu\\Testing\\FlipkartAutomationTesting\\TestReport\\flipkartTestingReport.html";

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
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(body);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "flipkart Test Report";
            DataSource source = new FileDataSource(reportPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);


            Transport.send(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}