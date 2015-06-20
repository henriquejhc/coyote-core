package org.coyote.core.mail;

public class MailTest {

	public static void main(String[] args) {

		SimpleMail mailPojo = new SimpleMail();
		mailPojo.setSubject("Assunto lalala");
		mailPojo.setSender("henriquejhc@gmail.com");
		mailPojo.setRecipient("henriquejhc@gmail.com");
		mailPojo.setMessage("Mensagem lalalalala");

		new MailService().send(mailPojo);
	}

}