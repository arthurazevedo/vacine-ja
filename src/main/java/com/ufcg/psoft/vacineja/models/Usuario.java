package com.ufcg.psoft.vacineja.models;

import com.ufcg.psoft.vacineja.models.enums.TipoUsuarioEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;
    private String senha;
    private String perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<PerfilUsuario> perfis = new ArrayList<>();

        PerfilUsuario perfilUsuario = (this.perfil.equals(TipoUsuarioEnum.CIDADAO.getValue()))
                ? new PerfilUsuario(TipoUsuarioEnum.CIDADAO)
                : new PerfilUsuario(TipoUsuarioEnum.FUNCIONARIO);

        perfis.add(perfilUsuario);

        return perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
