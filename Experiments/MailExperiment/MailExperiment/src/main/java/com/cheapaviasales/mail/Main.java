package com.cheapaviasales.mail;

/*
 public final class Main {

 public static void main(String[] args) {

 }

 }
 */

/*
 import java.util.*;
 import javax.mail.*;
 import javax.mail.internet.*;
 import javax.activation.*;

 // Send a simple, single part, text/plain e-mail
 public class Main {

 public static void main(String[] args) {

 // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
 String to = "aleks-say@mail.ru";
 String from = "cheapaviasales@gmail.com";
 // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
 //String host = "mail.google.com";
 String host = "smtp.gmail.com";

 // Create properties, get Session
 Properties props = new Properties();

 // If using static Transport.send(),
 // need to specify which host to send it to
 props.put("mail.smtp.host", host);
 props.put("mail.smtp.port", "465");
 // To see what is going on behind the scene
 props.put("mail.debug", "true");
 Session session = Session.getInstance(props);

 try {
 // Instantiatee a message
 Message msg = new MimeMessage(session);

 // Set message attributes
 msg.setFrom(new InternetAddress(from));
 InternetAddress[] address = { new InternetAddress(to) };
 msg.setRecipients(Message.RecipientType.TO, address);
 msg.setSubject("Test E-Mail through Java");
 msg.setSentDate(new Date());

 // Set message content
 msg.setText("This is a test of sending a "
 + "plain text e-mail through Java.\n" + "Here is line 2.");

 // Send the message
 Transport.send(msg);
 } catch (MessagingException mex) {
 // Prints all nested (chained) exceptions as well
 mex.printStackTrace();
 }
 }
 }
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws MessagingException {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "25");
		// props.setProperty("mail.debug", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(props,
				new CheapAviasalesAuthenticator());
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("cheapaviasales"));
		msg.setRecipient(RecipientType.TO, new InternetAddress(
				"aleks-say@mail.ru"));
		msg.setSubject("test");
		msg.setText("Hello world");
		msg.setSentDate(new Date());

		Transport.send(msg);
	}
}

final class CheapAviasalesAuthenticator extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("cheapaviasales", "cheapaviasalesapp");
	}
}