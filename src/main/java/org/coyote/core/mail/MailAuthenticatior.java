package org.coyote.core.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticatior {

	public static Authenticator getAuthentication() {
		return new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfiguration.getInstance().getUsername(), MailConfiguration.getInstance().getPassword());
			}
		};
	}

}
