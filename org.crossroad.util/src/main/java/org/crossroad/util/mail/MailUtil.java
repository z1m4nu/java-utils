package org.crossroad.util.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.crossroad.util.AbstractLogger;

public class MailUtil extends AbstractLogger {
	private String body = null;
	private String sender = null;
	private String receiver = null;
	private String subject = null;
	private String smtpHostname = null;
	private String smtpPort = null;
	private Session session = null;

	public MailUtil(String smtpHost, String smtpPort) {
		this.smtpHostname = smtpHost;
		this.smtpPort = smtpPort;
	}

	public void sendHTMLMail() throws Exception {

		try {
			startTime();
			this.openSession();
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(this.sender));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.receiver));

			// Set Subject: header field
			message.setSubject(this.subject);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			MimeBodyPart htmlPart = new MimeBodyPart();

			htmlPart.setContent(this.body, "text/html; charset=utf-8");
			multipart.addBodyPart(htmlPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			log.info("Mail sent....");
		} catch (Exception e) {
			log.error("Unable to send mail", e);
			throw e;
		} finally {
			stopTime("SENDING_MAIL",true);
		}
	}

	private void openSession() throws Exception {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", smtpHostname);
		props.put("mail.smtp.port", smtpPort);

		session = Session.getInstance(props);

	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
