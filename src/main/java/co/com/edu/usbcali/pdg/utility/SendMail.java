package co.com.edu.usbcali.pdg.utility;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class SendMail {

	@Autowired
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String to, String subject, String msg) throws Exception {
		
		MimeMessage message = mailSender.createMimeMessage();
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setContent(msg, "text/html");
		
		mailSender.send(message);
	}
	
	public void sendNewPassword(String email, String contraseña) throws Exception {
		
		String subject = "Notificación de nueva contraseña";
		String message = "Hemos generado la nueva contraseña: " + contraseña;
		
		sendMail(email, subject, message);
		
	}

}