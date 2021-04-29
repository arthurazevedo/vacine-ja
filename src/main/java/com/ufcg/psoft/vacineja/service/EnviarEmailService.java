package com.ufcg.psoft.vacineja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmailService {

    @Autowired
    private JavaMailSender enviarEmail;

    public void enviar(String emailUsuario, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailUsuario);
        email.setSubject(assunto);
        email.setText(mensagem);
        enviarEmail.send(email);
    }
}
