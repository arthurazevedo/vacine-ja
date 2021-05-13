package com.ufcg.psoft.vacineja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacineja.dtos.AgendamentoDTO;
import com.ufcg.psoft.vacineja.service.AgendamentoService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@RequestMapping(value = "/{email}", method = RequestMethod.POST)
	public ResponseEntity<?> agendaHorario(@RequestBody AgendamentoDTO agendamentoDTO, @PathVariable String email){
		agendamentoService.salvaAgendamento(agendamentoDTO, email);
		return new ResponseEntity<AgendamentoDTO>(agendamentoDTO, HttpStatus.CREATED);
	}
}
