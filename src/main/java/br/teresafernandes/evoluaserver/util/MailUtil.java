/**
 * Data: 26 de ago de 2019
 */
package br.teresafernandes.evoluaserver.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;

/**
 * @author Teresa Fernandes
 *
 */
public class MailUtil {

	public static String host = "smtp.gmail.com";
	public static String port = "587";
	public static String user = "appevolua@gmail.com";
	public static String pass = "evolua1234";
	public static void enviarEmail(String assunto, String mensagem, String destinatarios) throws ServiceBusinessException {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
//	        props.put("mail.debug", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", port);
	        props.put("mail.smtp.ssl.trust", host);
	        
			Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, pass);
	            }
	          });

	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios));
	        message.setSubject(assunto);
	        
	        MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mensagem, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            
	        Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new ServiceBusinessException("Erro ao enviar o email");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new ServiceBusinessException("Erro ao enviar o email");
		}
	}
	
}
