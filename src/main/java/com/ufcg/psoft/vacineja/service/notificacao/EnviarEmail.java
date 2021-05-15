package com.ufcg.psoft.vacineja.service.notificacao;

import org.springframework.stereotype.Component;

@Component
public interface EnviarEmail {
    public void enviar(String emailUsuario, String assunto, String mensagem);
}
