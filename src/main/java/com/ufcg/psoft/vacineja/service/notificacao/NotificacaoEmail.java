package com.ufcg.psoft.vacineja.service.notificacao;

import com.ufcg.psoft.vacineja.model.Cidadao;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoEmail implements Notificacao {

    EnviarEmail enviarEmail = new EnviarEmailService();

    @Override
    public void notificar(Cidadao cidadao, String assunto, String mensagem) {
        enviarEmail.enviar(cidadao.getUsuario().getEmail(), assunto, mensagem);
    }
}
