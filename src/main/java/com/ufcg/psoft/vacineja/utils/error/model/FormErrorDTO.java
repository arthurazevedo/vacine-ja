package com.ufcg.psoft.vacineja.utils.error.model;

public class FormErrorDTO {
    private String campo;
    private String mensagem;

    public FormErrorDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public FormErrorDTO(String mensagem) {
        this.campo = "";
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
