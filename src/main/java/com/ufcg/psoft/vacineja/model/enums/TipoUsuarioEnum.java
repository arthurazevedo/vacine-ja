package com.ufcg.psoft.vacineja.model.enums;

import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;

public enum TipoUsuarioEnum {
    FUNCIONARIO("F"),
    CIDADAO("C"),
    ADMINISTRADOR("A");

    private String value;

    TipoUsuarioEnum(String value) {
        this.value = value;
    };

    public String getValue() {
        return value;
    }

    public static TipoUsuarioEnum getEnum(String value) {
        switch (value) {
            case "F": return TipoUsuarioEnum.FUNCIONARIO;
            case "C": return TipoUsuarioEnum.CIDADAO;
            case "A": return TipoUsuarioEnum.ADMINISTRADOR;
            default:
                throw new ValidacaoException(
                        new ErroDeSistema(String.format("%s não é um tipo de usuário válido", value))
                );
        }
    }
}
