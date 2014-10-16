package org.coyote.core.mail;

import java.util.Properties;

import org.coyote.core.application.CoyoteConfiguration;

public class MailConfiguration {

	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String COYOTE_MAIL_USERNAME = "coyote.mail.username";
	private static final String COYOTE_MAIL_PASSWORD = "coyote.mail.password";
	private static final String COYOTE_MAIL_CONNECTION = "coyote.mail.connection";

	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
	private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";

	private static final String COYOTE_MAIL_CONNECTION_TYPE = "SSL";

	private Properties properties;
	private String username;
	private String password;
	private String connectionType;


	private static MailConfiguration INSTANCE = new MailConfiguration();

	private MailConfiguration() {
		init();
	}

	private void init() {
		username = CoyoteConfiguration.getInstance().getString(COYOTE_MAIL_USERNAME);
		password = CoyoteConfiguration.getInstance().getString(COYOTE_MAIL_PASSWORD);
		connectionType = CoyoteConfiguration.getInstance().getString(COYOTE_MAIL_CONNECTION);
		configureProperties();
	}

	private void configureProperties() {

		properties = new Properties();

		properties.put(MAIL_SMTP_HOST, CoyoteConfiguration.getInstance().getString(MAIL_SMTP_HOST));
		properties.put(MAIL_SMTP_PORT, CoyoteConfiguration.getInstance().getString(MAIL_SMTP_PORT));
		properties.put(MAIL_SMTP_AUTH, CoyoteConfiguration.getInstance().getString(MAIL_SMTP_AUTH));

		if (connectionType.equals(COYOTE_MAIL_CONNECTION_TYPE)) {
			properties.put(MAIL_SMTP_SOCKET_FACTORY_PORT, CoyoteConfiguration.getInstance().getString(MAIL_SMTP_SOCKET_FACTORY_PORT));
			properties.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, CoyoteConfiguration.getInstance().getString(MAIL_SMTP_SOCKET_FACTORY_CLASS));
		} else {
			properties.put(MAIL_SMTP_STARTTLS_ENABLE, CoyoteConfiguration.getInstance().getString(MAIL_SMTP_STARTTLS_ENABLE));
		}
	}

	public static MailConfiguration getInstance() {
		return INSTANCE;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}