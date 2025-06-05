package utilities;

import javax.mail.*;
//import javax.mail.internet.*;
//
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.*;
//import java.io.File;
//
//import org.apache.hc.core5.http.Message;
//
//import jakarta.mail.Authenticator;
//import jakarta.mail.PasswordAuthentication;
//import jakarta.mail.Session;
//import jakarta.mail.Transport;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeBodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;

//import jakarta.mail.internet.MimeBodyPart;

import java.util.Date;
import javax.mail.Multipart;
//import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Date;
import java.util.Properties;
public class EmailUtils {

	
	 public static void sendReport(String reportPath) {
	        String from = ConfigReader.getProperty("email.from");
	        String pass = ConfigReader.getProperty("email.password");
	        String to = ConfigReader.getProperty("email.to");

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", ConfigReader.getProperty("smtp.host"));
	        props.put("mail.smtp.port", ConfigReader.getProperty("smtp.port"));

	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, pass);
	            }
	        });

	        try {
	            MimeMessage message = new javax.mail.internet.MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            message.setSubject("Test Report - " + new Date());
	            message.setSentDate(new Date());

	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText("Hi,\n\nPlease find the attached test execution report.\n\nRegards,\nAutomation Team");

	            MimeBodyPart attachmentPart = new MimeBodyPart();
	            attachmentPart.attachFile(new File(reportPath));

	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBodyPart);
	            multipart.addBodyPart(attachmentPart);

	            message.setContent(multipart);
	            Transport.send(message);

	            System.out.println("Email sent successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
