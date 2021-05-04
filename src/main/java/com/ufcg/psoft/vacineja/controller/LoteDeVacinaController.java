package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.loteDeVacina.LoteDeVacinaCreateDTO;
import com.ufcg.psoft.vacineja.dtos.loteDeVacina.LoteDeVacinaResponseDTO;
import com.ufcg.psoft.vacineja.dtos.loteDeVacina.LoteDeVacinaUpdateDTO;
import com.ufcg.psoft.vacineja.model.LoteDeVacina;
import com.ufcg.psoft.vacineja.service.LoteDeVacinaService;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import java.util.List;

@RestController
@RequestMapping("/lotes")
@CrossOrigin
public class LoteDeVacinaController {
    @Autowired
    private LoteDeVacinaService loteDeVacinaService;
    @Autowired
    private MapperUtil mapper;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public LoteDeVacinaResponseDTO cadastrarLote(@RequestBody LoteDeVacinaCreateDTO loteDeVacinaCreateDTO) {
        return loteDeVacinaService.cadastrarLote(loteDeVacinaCreateDTO, mapper);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<LoteDeVacinaResponseDTO> listarLotes(@RequestParam Long vacinaId) {
        return loteDeVacinaService.listarLotesPorVacina(vacinaId, mapper);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public LoteDeVacinaResponseDTO getLote(@PathVariable Long id) {
        return loteDeVacinaService.buscarPorId(id, mapper);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public LoteDeVacina editarLote(@PathVariable Long id, @RequestBody LoteDeVacinaUpdateDTO loteDeVacinaUpdateDTO){
        return loteDeVacinaService.editarLote(id, loteDeVacinaUpdateDTO, mapper);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerLote(@PathVariable Long id) {
        loteDeVacinaService.removerLote(id);
    }
}
