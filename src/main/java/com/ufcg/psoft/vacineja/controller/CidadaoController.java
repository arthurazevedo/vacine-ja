package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.CidadaoRequestDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoResponseDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.dtos.EstadoCidadaoResponseDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.service.UsuarioService;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CidadaoService cidadaoService;

	@Autowired
	private MapperUtil mapperUtil;

	@Value("${hash.forca}")
    private int forcaHash;
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> cadastraCidadao(@RequestBody CidadaoRequestDTO cidadaoRequestDTO){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(forcaHash);
		
		if(usuarioService.contemUsuario(cidadaoRequestDTO.getEmail())) {
			throw new ValidacaoException(new ErroDeSistema(
				ErroCidadao.erroEmailJaCadastrado(cidadaoRequestDTO.getEmail()), HttpStatus.BAD_REQUEST)
			);
		}

		if(cidadaoService.contemCidadao(cidadaoRequestDTO.getCpf())) {
			throw new ValidacaoException(new ErroDeSistema(
				ErroCidadao.erroCpfJaCadastrado(cidadaoRequestDTO.getCpf()), HttpStatus.BAD_REQUEST)
			);
		}

		cidadaoRequestDTO.setSenha(encoder.encode(cidadaoRequestDTO.getSenha()));

		Usuario usuario = new Usuario(cidadaoRequestDTO);

		usuarioService.salvaUsuario(usuario);

		Cidadao cidadao = mapperUtil.toEntity(cidadaoRequestDTO, Cidadao.class);
		cidadao.setUsuario(usuario);
		cidadaoService.salvarCidadao(cidadao);

		CidadaoResponseDTO cidadaoResponseDTO = mapperUtil.toDTO(cidadao, CidadaoResponseDTO.class);
		cidadaoResponseDTO.setEmail(usuario.getEmail());

		return new ResponseEntity<CidadaoResponseDTO>(cidadaoResponseDTO, HttpStatus.CREATED);
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
