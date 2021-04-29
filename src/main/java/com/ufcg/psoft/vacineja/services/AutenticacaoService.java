package com.ufcg.psoft.vacineja.services;

import com.ufcg.psoft.vacineja.models.Usuario;
import com.ufcg.psoft.vacineja.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioServices;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioServices.findByEmail(login);
        if(usuarioOptional.isPresent()) return usuarioOptional.get();

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
