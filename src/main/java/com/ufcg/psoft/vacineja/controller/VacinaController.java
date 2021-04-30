package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.vacina.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.service.vacina.BuscarVacinaPorId;
import com.ufcg.psoft.vacineja.service.vacina.CadastrarVacina;
import com.ufcg.psoft.vacineja.service.vacina.EditarVacina;
import com.ufcg.psoft.vacineja.service.vacina.ListarVacinas;
import com.ufcg.psoft.vacineja.service.vacina.RemoverVacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vacinas")
public class VacinaController {

    @Autowired
    private CadastrarVacina cadastrarVacina;
    @Autowired
    private BuscarVacinaPorId buscarVacinaPorId;
    @Autowired
    private ListarVacinas listarVacinas;
    @Autowired
    private EditarVacina editarVacina;
    @Autowired
    private RemoverVacina removerVacina;

    private static final ModelMapper mapper = new ModelMapper();

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> cadastrarVacina(@RequestBody VacinaDTO vacinaDTO) {
        return cadastrarVacina.executar(vacinaDTO, mapper);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Vacina> listarVacinas() {
        return listarVacinas.executar();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> listarVacinas(@PathVariable Long id) {
        return buscarVacinaPorId.executar(id);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> editarVacina(@PathVariable Long id, @RequestBody VacinaDTO vacinaDTO) {
        return editarVacina.executar(id, vacinaDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletarVacina(@PathVariable Long id) {
        return removerVacina.executar(id);
    }
}
