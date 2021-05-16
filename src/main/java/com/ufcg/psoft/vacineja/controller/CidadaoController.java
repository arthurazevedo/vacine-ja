package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.CidadaoRequestDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoResponseDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.dtos.EstadoCidadaoResponseDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/cidadao")
public class CidadaoController {

	@Autowired
	private CidadaoService cidadaoService;

	@Autowired
	private MapperUtil mapperUtil;
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> cadastraCidadao(@RequestBody @Valid CidadaoRequestDTO cidadaoRequestDTO){

		Cidadao cidadao = cidadaoService.salvarCidadao(cidadaoRequestDTO);

		CidadaoResponseDTO cidadaoResponseDTO = mapperUtil.toDTO(cidadao, CidadaoResponseDTO.class);
		cidadaoResponseDTO.setEmail(cidadaoRequestDTO.getEmail());

		return new ResponseEntity<>(cidadaoResponseDTO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/estado", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> mostrarEstadoCidadao() {
		String estado = cidadaoService.pegarEstadoCidadao();
		EstadoCidadaoResponseDTO estadoCidadao = new EstadoCidadaoResponseDTO(estado);
		return new ResponseEntity<EstadoCidadaoResponseDTO>(estadoCidadao, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces="application/json")
	public ResponseEntity<?> mudaDadosCidadao(@RequestBody @Valid CidadaoUpdateDTO cidadaoUpdateDTO) {
		Cidadao cidadaoAtualizado = cidadaoService.atualizaCidadao(cidadaoUpdateDTO);
		CidadaoResponseDTO cidadaoResponseDTO = mapperUtil.toDTO(cidadaoAtualizado, CidadaoResponseDTO.class);
		cidadaoResponseDTO.setEmail(cidadaoAtualizado.getUsuario().getEmail());
		return new ResponseEntity<CidadaoResponseDTO>(cidadaoResponseDTO, HttpStatus.OK);
	}
}
