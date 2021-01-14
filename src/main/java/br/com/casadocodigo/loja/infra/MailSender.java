package br.com.casadocodigo.loja.infra;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ApplicationScoped
public class MailSender {

	@Resource(mappedName = "java:/jboss/mail")
	private Session session;
	
	public void send(String from, String to, String subject, String body) {
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setRecipients(RecipientType.TO, InternetAddress.parse(to));
			msg.setFrom(new InternetAddress(from));
			msg.setContent(body, "text/html");
			msg.setSubject(subject);
			
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
