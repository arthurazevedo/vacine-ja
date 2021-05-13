package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.CidadaoRequestDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoResponseDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.dtos.EstadoCidadaoResponseDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.service.UsuarioService;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CidadaoService cidadaoService;

	@Autowired
	private MapperUtil mapperUtil;
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> cadastraCidadao(@RequestBody CidadaoRequestDTO cidadaoRequestDTO){

		Cidadao cidadao = cidadaoService.salvarCidadao(cidadaoRequestDTO);

		CidadaoResponseDTO cidadaoResponseDTO = mapperUtil.toDTO(cidadao, CidadaoResponseDTO.class);
		cidadaoResponseDTO.setEmail(cidadaoRequestDTO.getEmail());

		return new ResponseEntity<>(cidadaoResponseDTO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{cpf}/estado", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> mostrarEstadoCidadao(@PathVariable String cpf) {
		String estado = cidadaoService.pegarEstadoCidadao(cpf);
		EstadoCidadaoResponseDTO estadoCidadao = new EstadoCidadaoResponseDTO(estado);
		return new ResponseEntity<EstadoCidadaoResponseDTO>(estadoCidadao, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{cpf}", method = RequestMethod.PUT, produces="application/json")
	public ResponseEntity<?> mudaDadosCidadao(@PathVariable String cpf, @RequestBody CidadaoUpdateDTO cidadaoUpdateDTO) {
		return new ResponseEntity<Cidadao>(cidadaoService.atualizaCidadao(cpf, cidadaoUpdateDTO), HttpStatus.OK);
	}
}
