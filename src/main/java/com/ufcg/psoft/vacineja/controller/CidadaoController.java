package com.ufcg.psoft.vacineja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacineja.dtos.CidadaoDTO;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.service.UsuarioService;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.MapperUtil;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private MapperUtil mapper;
	
	@Value("${hash.forca}")
    private int forcaHash;
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> login(@RequestBody CidadaoDTO cidadaoDTO){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(forcaHash);
		
		if(usuarioService.contemUsuario(cidadaoDTO.getEmail())) {
			return ResponseEntity.badRequest().body(ErroCidadao.erroEmailJaCadastrado(cidadaoDTO.getEmail()));
		}
		
		cidadaoDTO.setSenha(encoder.encode(cidadaoDTO.getSenha()));
		Usuario usuario = usuarioService.adicionaCidadao(cidadaoDTO, mapper);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
}
