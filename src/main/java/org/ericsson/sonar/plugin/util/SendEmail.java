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
		// Recipient's email ID list
		String toList = settings.getProperties().get(
				"sonar.custom.report.emailids");
		// Sender's email ID
		String from = settings.getProperties().get("email.from");
		//SMTP server host
		String host = settings.getProperties().get("email.smtp_host.secured");
		//SMTP server port
		String port = settings.getProperties().get("email.smtp_port.secured");
		// Email Subject
		String subject = settings.getProperties().get(
				"sonar.custom.report.subject");

		// Hard coded values for testing
		// toList = "ekta.chawla@ericsson.com";
		// from = "No Reply <noreply@ericsson.com>";//change accordingly
		// host = "smtp.internal.ericsson.com";
		// port = "25";
		// subject = "sub";
		
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toList));

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			message.setText("This is message body");
			LOG.info("here here");
			// TBD: For Attachment
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
			LOG.error("MessagingException occured in sendEmail() : "
					+ mex.getMessage());
			mex.printStackTrace();
		}
	}

	/*public static void main(String[] args) {		
		SendEmail se = new SendEmail(new Settings());
		se.sendEmail();
		System.out.println("Mail sent Successfully");

	}*/

}