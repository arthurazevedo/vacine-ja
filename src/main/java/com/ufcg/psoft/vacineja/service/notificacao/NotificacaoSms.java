package com.ufcg.psoft.vacineja.service.notificacao;

import com.ufcg.psoft.vacineja.model.Cidadao;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoSms implements Notificacao {
    @Override
    public void notificar(Cidadao cidadao, String assunto, String mensagem) {
        String telefone = cidadao.getTelefone();
        System.out.println(String.format("SMS enviado para o número %s:\nAssunto: '%s\nMensagem: %s", telefone, assunto, mensagem));
    }
}
