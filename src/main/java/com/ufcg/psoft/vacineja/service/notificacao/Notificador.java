package com.ufcg.psoft.vacineja.service.notificacao;

import com.ufcg.psoft.vacineja.model.Cidadao;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Notificador {
    private Set<Notificacao> listeners;

    public Notificador() {
        listeners = new HashSet<>();
        listeners.add(new NotificacaoEmail());
        listeners.add(new NotificacaoSms());
    }

    public void inscrever(Notificacao notificacao) {
        listeners.add(notificacao);
    }

    public void desinscrever(Notificacao notificacao) {
        listeners.remove(notificacao);
    }

    public void notificar(Cidadao cidadao, String assunto, String mensagem) {
        for(Notificacao notificacao : listeners) {
            try {
                notificacao.notificar(cidadao, assunto, mensagem);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
