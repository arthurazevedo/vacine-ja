package com.ufcg.psoft.vacineja.service.vacina;

import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import com.ufcg.psoft.vacineja.utils.ErroVacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarVacinaPorId {
    @Autowired
    private VacinaRepository vacinaRepository;

    public ResponseEntity<?> executar(Long id) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);

        return optionalVacina.isPresent() ?
                new ResponseEntity<>(optionalVacina.get(), HttpStatus.OK) :
                ErroVacina.erroVacinaNaoEncontrada(id);
    }
}
