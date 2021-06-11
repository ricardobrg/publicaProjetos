package augusto.publica;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.mail.PasswordAuthentication;

public class EnviarEmail {
	
	public static void sendMail(String destinatario) throws Exception {
		System.out.println("Preparando para o envio!");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String minhaConta = "prowaypublicateste@gmail.com",
			   minhaSenha = "prowaypublica123";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(minhaConta, minhaSenha);
			};
		});
		
		Message message = prepareMessage(session, minhaConta, destinatario);
		
		Transport.send(message);
		System.out.println("Email enviado com sucesso!");
	}

	private static Message prepareMessage(Session session, String minhaConta, String destinatario) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(minhaConta));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject("Testando recuperação de senha");
			message.setText("Clique no link para redefinir senha");
			return message;
		} catch (Exception ex) {
			Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
		}	
		return null;
	}
}
