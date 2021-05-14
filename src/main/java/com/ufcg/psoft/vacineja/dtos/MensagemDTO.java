package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;

@Getter
public class MensagemDTO {
    private String mensagem;

    public MensagemDTO(String mensagem) {
        this.mensagem = mensagem;
    }
}
