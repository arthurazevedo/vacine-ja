package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.CidadaoRequestDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoResponseDTO;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.service.UsuarioService;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CidadaoService cidadaoService;
	
	@Value("${hash.forca}")
    private int forcaHash;
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> cadastraCidadao(@RequestBody CidadaoRequestDTO cidadaoRequestDTO){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(forcaHash);
		
		if(usuarioService.contemUsuario(cidadaoRequestDTO.getEmail())) {
			return ResponseEntity.badRequest().body(ErroCidadao.erroEmailJaCadastrado(cidadaoRequestDTO.getEmail()));
		}
		
		cidadaoRequestDTO.setSenha(encoder.encode(cidadaoRequestDTO.getSenha()));
		CidadaoResponseDTO cidadaoResponseDTO = new CidadaoResponseDTO(usuarioService.adicionaCidadao(cidadaoRequestDTO));
		
		return new ResponseEntity<CidadaoResponseDTO>(cidadaoResponseDTO, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> mostrarEstadoCidadao(@RequestBody String cpf) {
		String estado = cidadaoService.encontrarCidadaoPorCpf(cpf);
		return new ResponseEntity<String>(estado, HttpStatus.ACCEPTED);
	}
}
