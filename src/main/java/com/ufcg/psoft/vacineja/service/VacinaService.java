package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.vacina.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import com.ufcg.psoft.vacineja.utils.CustomError;
import com.ufcg.psoft.vacineja.utils.ErroVacina;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class VacinaService {
    @Autowired
    private VacinaRepository vacinaRepository;

    public ResponseEntity<?> cadastrarVacina(VacinaDTO vacinaDTO, MapperUtil mapper) {
        final var validacaoDTO = validarDtoCadastroDeVacina(vacinaDTO);
        if(nonNull(validacaoDTO)) {
            return validacaoDTO;
        }
        final var vacina = mapper.toEntity(vacinaDTO, Vacina.class);
        vacinaRepository.save(vacina);
        return new ResponseEntity<>(vacina, HttpStatus.CREATED);
    }

    public ResponseEntity<?> buscarPorId(Long id) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);

        return optionalVacina.isPresent() ?
                new ResponseEntity<>(optionalVacina.get(), HttpStatus.OK) :
                ErroVacina.erroVacinaNaoEncontrada(id);
    }

    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();
    }

    public ResponseEntity<?> editarVacina(Long id, VacinaDTO vacinaDTO) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);

        if(optionalVacina.isEmpty()) {
            return ErroVacina.erroVacinaNaoEncontrada(id);
        }

        Vacina vacina = optionalVacina.get();

        if(!vacinaDTO.getFabricante().isBlank()) {
            vacina.setFabricante(vacinaDTO.getFabricante());
        }

        if(vacinaDTO.getDosesRequeridas() <= 2 || vacinaDTO.getDosesRequeridas() >= 1) {
            vacina.setDosesRequeridas(vacinaDTO.getDosesRequeridas());
        }

        if(vacinaDTO.getIntervaloEntreDoses() > 0 && vacinaDTO.getDosesRequeridas() == 2) {
            vacina.setIntervaloEntreDoses(vacinaDTO.getIntervaloEntreDoses());
        }

        vacinaRepository.save(vacina);
        return new ResponseEntity<>(vacina, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> removerVacinaPorId(Long id) {

        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);
        if(optionalVacina.isEmpty()) {
            return ErroVacina.erroVacinaNaoEncontrada(id);
        }
        vacinaRepository.delete(optionalVacina.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<CustomError> validarDtoCadastroDeVacina(VacinaDTO vacinaDTO) {
        if(vacinaDTO.getFabricante().isBlank()) {
            return ErroVacina.erroFabricanteNulo();
        }

        if(vacinaDTO.getDosesRequeridas() > 2 || vacinaDTO.getDosesRequeridas() < 0) {
            return ErroVacina.erroQuantidadeDeDosesInvalida();
        }

        if(vacinaDTO.getDosesRequeridas() > 1 && vacinaDTO.getIntervaloEntreDoses() == 0) {
            return ErroVacina.erroVacinaSemIntervaloEntreDoses();
        } else if(vacinaDTO.getDosesRequeridas() == 1 && vacinaDTO.getIntervaloEntreDoses() > 0) {
            return ErroVacina.erroVacinaDeDoseUnica();
        }

        return null;
    }
}
