package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.VacinaAtualizaDTO;
import com.ufcg.psoft.vacineja.dtos.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.service.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/vacinas")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Vacina cadastrarVacina(@RequestBody @Valid VacinaDTO vacinaDTO) {
        return vacinaService.cadastrarVacina(vacinaDTO);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Vacina> listarVacinas() {
        return vacinaService.listarVacinas();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Vacina buscarVacinaPorId(@PathVariable Long id) {
        return vacinaService.buscarPorId(id);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Vacina editarVacina(@PathVariable Long id, @RequestBody @Valid VacinaAtualizaDTO vacinaDTO) {
        return vacinaService.editarVacina(id, vacinaDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarVacina(@PathVariable Long id) {
        vacinaService.removerVacinaPorId(id);
    }
}
