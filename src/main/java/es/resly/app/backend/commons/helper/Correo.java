package es.resly.app.backend.commons.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Correo {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(String email, String subject, String text){
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject(subject);
        emailMessage.setText(text);
        mailSender.send(emailMessage);
    }
}
