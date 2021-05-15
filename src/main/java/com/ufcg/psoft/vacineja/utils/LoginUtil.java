package com.ufcg.psoft.vacineja.utils;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import com.ufcg.psoft.vacineja.service.factory.TipoUsuarioFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {

    @Autowired
    private TipoUsuarioFactory tipoUsuarioFactory;

    public Cidadao pegarCidadaoLogado() {
        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) autenticacao.getPrincipal();
        Cidadao cidadaoLogado;

        if(usuario.getPerfil().equals(TipoUsuarioEnum.FUNCIONARIO.getValue())) {
            Funcionario funcionario = (Funcionario) tipoUsuarioFactory.get(usuario);
            cidadaoLogado = funcionario.getCidadao();
        } else {
            cidadaoLogado = (Cidadao) tipoUsuarioFactory.get(usuario);
        }

        return cidadaoLogado;
    }

}
