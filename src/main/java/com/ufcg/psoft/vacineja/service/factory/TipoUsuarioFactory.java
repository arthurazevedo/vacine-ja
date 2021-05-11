package com.ufcg.psoft.vacineja.service.factory;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.TipoUsuario;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TipoUsuarioFactory {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CidadaoRepository cidadaoRepository;

    public TipoUsuario get(Usuario usuario) {
        switch (usuario.getPerfil()) {
            case "F":
                Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByCidadaoUsuario(usuario);
                return funcionarioOptional.orElse(null);
            case "C":
                Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByUsuario(usuario);
                return cidadaoOptional.orElse(null);
        }

        return null;
    }
}
