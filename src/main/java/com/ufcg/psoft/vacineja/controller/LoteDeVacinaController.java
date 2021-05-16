package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.LoteDeVacinaCreateDTO;
import com.ufcg.psoft.vacineja.dtos.LoteDeVacinaResponseDTO;
import com.ufcg.psoft.vacineja.dtos.LoteDeVacinaUpdateDTO;
import com.ufcg.psoft.vacineja.model.LoteDeVacina;
import com.ufcg.psoft.vacineja.service.LoteDeVacinaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/lotes")
@CrossOrigin
public class LoteDeVacinaController {
    @Autowired
    private LoteDeVacinaService loteDeVacinaService;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public LoteDeVacinaResponseDTO cadastrarLote(@RequestBody @Valid LoteDeVacinaCreateDTO loteDeVacinaCreateDTO) {
        return loteDeVacinaService.cadastrarLote(loteDeVacinaCreateDTO);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<LoteDeVacinaResponseDTO> listarLotes(@RequestParam String fabricante) {
        return loteDeVacinaService.listarLotesPorVacina(fabricante);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public LoteDeVacinaResponseDTO getLote(@PathVariable Long id) {
        return loteDeVacinaService.geraResponseDTOPorId(id);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public LoteDeVacina editarLote(@PathVariable Long id, @RequestBody @Valid LoteDeVacinaUpdateDTO loteDeVacinaUpdateDTO){
        return loteDeVacinaService.editarLote(id, loteDeVacinaUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerLote(@PathVariable Long id) {
        loteDeVacinaService.removerLote(id);
    }
}
