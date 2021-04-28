package com.ufcg.psoft.vacineja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioServices usuarioServices;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioServices.findByLogin(login);
        if(usuarioOptional.isPresent()) return usuarioOptional.get();

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
