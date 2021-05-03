package com.ufcg.psoft.vacineja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacineja.dtos.CidadaoDTO;
import com.ufcg.psoft.vacineja.service.UsuarioService;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/cadastra_cidadao", method = RequestMethod.GET)
	public ResponseEntity<?> login(@RequestBody CidadaoDTO cidadaoDTO){
		if(usuarioService.contemUsuario(cidadaoDTO.getEmail())) {
			return ResponseEntity.badRequest().body("E-mail já cadastrado.");
		}
		
		usuarioService.adicionaCidadao(cidadaoDTO.getEmail(), 
				cidadaoDTO.getSenha(), 
				cidadaoDTO.getNome(), 
				cidadaoDTO.getCpf(), 
				cidadaoDTO.getEndereco(), 
				cidadaoDTO.getSus(), 
				cidadaoDTO.getTelefone(), 
				cidadaoDTO.getProfissao(), 
				cidadaoDTO.getComorbidades(), 
				cidadaoDTO.getNascimento());
		
		return ResponseEntity.ok().body(usuarioService.loadUserByUsername(cidadaoDTO.getEmail()));
	}
}
