package com.ufcg.psoft.vacineja.models;

import com.ufcg.psoft.vacineja.models.enums.TipoUsuarioEnum;
import org.springframework.security.core.GrantedAuthority;

public class PerfilUsuario implements GrantedAuthority {
    private String tipoUsuario;

    public PerfilUsuario(TipoUsuarioEnum tipoUsuarioEnum) {
        this.tipoUsuario = tipoUsuarioEnum.getValue();
    }

    @Override
    public String getAuthority() {
        return this.tipoUsuario;
    }
}
