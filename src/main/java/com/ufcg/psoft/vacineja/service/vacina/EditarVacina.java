package com.ufcg.psoft.vacineja.service.vacina;

import com.ufcg.psoft.vacineja.dtos.vacina.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import com.ufcg.psoft.vacineja.utils.ErroVacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditarVacina {
    @Autowired
    private VacinaRepository vacinaRepository;

    public ResponseEntity<?> executar(Long id, VacinaDTO vacinaDTO) {
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
}
