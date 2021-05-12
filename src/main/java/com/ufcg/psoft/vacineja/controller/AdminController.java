package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.FuncionarioResponseDTO;
import com.ufcg.psoft.vacineja.dtos.MensagemDTO;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(value = "/cadastros-pendentes", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> listarCadastrosPendentes() {
        List<Funcionario> funcionariosPendentes =  funcionarioService.getFuncionariosPendentes();
        List<FuncionarioResponseDTO> response = new ArrayList<>();
        for(Funcionario funcionario: funcionariosPendentes) {
            response.add(new FuncionarioResponseDTO(funcionario));
        }
        return new ResponseEntity<List<FuncionarioResponseDTO>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/aprovar-cadastro", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> aprovarCadastro(@RequestBody String cpfFuncionario) {
        funcionarioService.aprovarCadastroFuncionario(cpfFuncionario);
        MensagemDTO mensagem = new MensagemDTO("Cadastro aprovado com sucesso!");
        return new ResponseEntity<MensagemDTO>(mensagem, HttpStatus.OK);
    }
}