package com.LM.api.utilityclasses;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	public String sendmail(String toEmail, String subject, String body) {
		String msg = null;
		String fromEmail = "noreplywebdev007@gmail.com";
		String password = "jhumra#1";
		javax.mail.Session session = null;

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		session = javax.mail.Session.getInstance(props, auth);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromEmail));

			// Set To: header field of the header.
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

//			message.setContent(body, "text/html");
			// Set Subject: header field
			message.setSubject(subject);
			message.setSentDate(new Date());

			// Now set the actual message
			message.setText(body);

			// creates a new e-mail message
//	        Message msg1 = new MimeMessage(session);
//	 
//	        msg1.setFrom(new InternetAddress(fromEmail));
//	        InternetAddress[] toAddresses = { new InternetAddress(toEmail) };
//	        msg1.setRecipients(Message.RecipientType.TO, toAddresses);
//	        msg1.setSubject(subject);
//	        msg1.setSentDate(new Date());
//	     // set plain text message
//	        msg1.setContent(body, "text/html");

//	        // sends the e-mail
//	        Transport.send(msg1);

			System.out.println("sending...");
			msg = "problem in sending messages..";
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
			msg = "Sent message successfully....";
		} catch (Exception e) {
			msg = "problem in sending messages.. ||" + e.getLocalizedMessage();
			e.printStackTrace();
		}
		return msg;

	}
}
