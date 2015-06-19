package org.ericsson.sonar.plugin.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.config.Settings;

public class SendEmail {
	private static final Logger LOG = LoggerFactory.getLogger(SendEmail.class);
	private Settings settings;

	public SendEmail(Settings settings) {
		this.settings = settings;
	}

	public void sendEmail() {
		//TBD: All these properties are to be read from settings
		// Recipient's email ID needs to be mentioned.
		String to = "ekta.chawla@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "ekta.chawla@gmail.com";

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "587");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		LOG.info("here");
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			message.setText("This is message body");
			LOG.info("here here");
			//TBD: For Attachment
			// Create a multipar message
			// Multipart multipart = new MimeMultipart();
			//
			// // Set text message part
			// multipart.addBodyPart(messageBodyPart);
			//
			// // Part two is attachment
			// messageBodyPart = new MimeBodyPart();
			// String filename = "file.txt";
			// DataSource source = new FileDataSource(filename);
			// messageBodyPart.setDataHandler(new DataHandler(source));
			// messageBodyPart.setFileName(filename);
			// multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			// message.setContent(multipart );

			LOG.info("here here here");
			// Send message
			Transport.send(message);
			LOG.info("Sent message successfully....");
		} catch (MessagingException mex) {
			LOG.error("MessagingException occured in sendEmail() : " + mex.getMessage());
			mex.printStackTrace();
		}
	}
}