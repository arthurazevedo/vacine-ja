package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.FuncionarioCadastroDTO;
import com.ufcg.psoft.vacineja.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody FuncionarioCadastroDTO body) {
        funcionarioService.cadastrarFuncionario(body);

        return ResponseEntity.status(201).body("Requisição para se tornar funcionário realizada com sucesso!");
    }
}
