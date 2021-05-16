package com.ufcg.psoft.vacineja.service.notificacao;

import com.ufcg.psoft.vacineja.model.Cidadao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoEmail implements Notificacao {

    @Autowired
    EnviarEmailService enviarEmail;

    @Override
    public void notificar(Cidadao cidadao, String assunto, String mensagem) {
        enviarEmail.enviar(cidadao.getUsuario().getEmail(), assunto, mensagem);
    }
}
