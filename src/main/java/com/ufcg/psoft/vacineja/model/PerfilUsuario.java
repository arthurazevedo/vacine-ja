package com.ufcg.psoft.vacineja.model;

import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
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
