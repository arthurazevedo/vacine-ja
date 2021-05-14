package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.RegistrosRequestDTO;
import com.ufcg.psoft.vacineja.dtos.RegistrosResponseDTO;
import com.ufcg.psoft.vacineja.model.RegistroVacina;
import com.ufcg.psoft.vacineja.service.RegistroVacinaService;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/registros")
@RestController
public class RegistroVacinaController {

    @Autowired
    private RegistroVacinaService registroVacinaService;

    @Autowired
    private MapperUtil mapper;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegistrosRequestDTO body) {
        RegistroVacina registro = registroVacinaService.cadastrar(body);

        RegistrosResponseDTO registroResponse = mapper.toDTO(registro, RegistrosResponseDTO.class);

        registroResponse.getCidadao().setEmail(registro.getCidadao().getUsuario().getEmail());

        return ResponseEntity.status(201).body(registroResponse);
    }

}
