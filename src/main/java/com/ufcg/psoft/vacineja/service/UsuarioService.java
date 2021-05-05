package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.CidadaoDTO;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.UsuarioRepository;
import com.ufcg.psoft.vacineja.utils.MapperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(login);
        if(usuarioOptional.isPresent()) return usuarioOptional.get();

        throw new UsernameNotFoundException("Dados inválidos!");
    }
    
    public boolean contemUsuario(String email) {
    	return usuarioRepository.existsByEmail(email);
    }
    
    public void removeUsuario(String email) {
    	usuarioRepository.deleteByEmail(email);
    }
    
    public void salvaUsuario(Usuario usuario) {
    	usuarioRepository.save(usuario);
    }

	public Usuario adicionaCidadao(CidadaoDTO cidadaoDTO, MapperUtil mapper) {
		final var usuario = mapper.toEntity(cidadaoDTO, Usuario.class);
		usuarioRepository.save(usuario);
		return usuario;
	}
}
