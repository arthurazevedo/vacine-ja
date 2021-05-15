package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.service.PerfilVacinacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/perfil-vacinacao")
public class PerfilVacinacaoController {
    @Autowired
    private PerfilVacinacaoService perfilVacinacaoService;

    @GetMapping(value = "/profissoes", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Set<String> listarProfissoes() {
        return perfilVacinacaoService.listarProfissoes();
    }

    @GetMapping(value = "/comorbidades", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Set<String> listarComorbidades() {
        return perfilVacinacaoService.listarComorbidades();
    }

    @GetMapping(value = "/idade", produces = { MediaType.APPLICATION_JSON_VALUE })
    public int listarIdadeMinima() {
        return perfilVacinacaoService.listarIdadeMinima();
    }

    @PutMapping(value = "/profissoes", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarProfissao(@RequestBody String novaProfissao) {
        perfilVacinacaoService.adicionarProfissao(novaProfissao);
    }

    @PutMapping(value = "/comorbidades", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarComorbidade(@RequestBody String novaComorbidade) {
        perfilVacinacaoService.adicionarComorbidade(novaComorbidade);
    }

    @PutMapping(value = "/idade", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarIdadeMinima(@RequestBody int novaIdade) {
        perfilVacinacaoService.alterarIdadeMinima(novaIdade);
    }
}
