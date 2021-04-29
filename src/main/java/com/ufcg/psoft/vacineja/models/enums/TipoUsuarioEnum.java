package com.ufcg.psoft.vacineja.models.enums;

public enum TipoUsuarioEnum {
    FUNCIONARIO("F"),
    CIDADAO("C");

    private String value;

    TipoUsuarioEnum(String value) {
        this.value = value;
    };

    public String getValue() {
        return value;
    }
}
