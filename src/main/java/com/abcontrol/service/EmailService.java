package com.abcontrol.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class EmailService {

    public EmailService() {
    }

    public void enviarEmail(String emailFornecedor, String msg) throws MessagingException {

        Properties prop = new Properties();

        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "25");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abcontrol.contato@gmail.com", "Teste123");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("abcontrol.contato@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(emailFornecedor));
        message.setSubject("Requisição de Compras - Empresa ABControl");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setText(msg, "utf-8", "html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        Transport.send(message);

    }

}
