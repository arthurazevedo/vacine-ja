package com.ufcg.psoft.vacineja.service.notificacao;

import com.ufcg.psoft.vacineja.model.Cidadao;
import org.springframework.stereotype.Component;

@Component
public interface Notificacao {
    public void notificar(Cidadao cidadao, String assunto, String mensagem);
}
