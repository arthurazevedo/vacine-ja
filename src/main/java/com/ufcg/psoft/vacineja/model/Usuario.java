package com.ufcg.psoft.vacineja.model;

import com.ufcg.psoft.vacineja.dtos.CidadaoRequestDTO;
import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidadao_id")
    private Cidadao tipo;
    
    public Usuario(CidadaoRequestDTO dto) {
		this.email = dto.getEmail();
    	this.senha = dto.getSenha();
    	this.tipo = new Cidadao(dto.getNome(), dto.getCpf(), dto.getEndereco(), dto.getSus(), dto.getTelefone(), dto.getProfissao(), dto.getComorbidades(), dto.getNascimento());
    	this.perfil = TipoUsuarioEnum.CIDADAO.getValue();
	}
    
    public void tornaFuncionario() {
    	//aqui se transforma cidadão em funcionário
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<PerfilUsuario> perfis = new ArrayList<>();

        PerfilUsuario perfilUsuario = new PerfilUsuario(TipoUsuarioEnum.getEnum(this.perfil));

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
