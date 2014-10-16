package org.coyote.core.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	private Session getSession() {
		return Session.getDefaultInstance(MailConfiguration.getInstance().getProperties(), MailAuthenticatior.getAuthentication());
	}

	public void send(SimpleMail simpleMail) {

		Session session = this.getSession();

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(simpleMail.getSender()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(simpleMail.getRecipient()));
			message.setSubject(simpleMail.getSubject());
			message.setText(simpleMail.getMessage());

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}