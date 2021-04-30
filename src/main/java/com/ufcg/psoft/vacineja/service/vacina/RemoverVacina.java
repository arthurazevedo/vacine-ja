package com.ufcg.psoft.vacineja.service.vacina;

import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverVacina {
    @Autowired
    private VacinaRepository vacinaRepository;

    public void executar(Long id) {
        vacinaRepository.deleteById(id);
    }
}
