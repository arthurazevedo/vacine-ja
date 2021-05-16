package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.AprovarDTO;
import com.ufcg.psoft.vacineja.dtos.FuncionarioCadastroDTO;
import com.ufcg.psoft.vacineja.dtos.FuncionarioResponseDTO;
import com.ufcg.psoft.vacineja.dtos.MensagemDTO;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody @Valid FuncionarioCadastroDTO body) {
        funcionarioService.cadastrarFuncionario(body);

        return ResponseEntity.status(201).body("Requisição para se tornar funcionário realizada com sucesso!");
    }

    @GetMapping(value = "/pendentes", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> listarCadastrosPendentes() {
        List<Funcionario> funcionariosPendentes =  funcionarioService.getFuncionariosPendentes();
        List<FuncionarioResponseDTO> response = new ArrayList<>();
        for(Funcionario funcionario: funcionariosPendentes) {
            response.add(new FuncionarioResponseDTO(funcionario));
        }
        return new ResponseEntity<List<FuncionarioResponseDTO>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/aprovar", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> aprovarCadastro(@RequestBody @Valid AprovarDTO aprovarDTO) {
        funcionarioService.aprovarCadastroFuncionario(aprovarDTO.getCpf());
        MensagemDTO mensagem = new MensagemDTO("Cadastro aprovado com sucesso!");
        return new ResponseEntity<MensagemDTO>(mensagem, HttpStatus.OK);
    }
}
