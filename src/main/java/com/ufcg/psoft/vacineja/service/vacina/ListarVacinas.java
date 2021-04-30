package com.ufcg.psoft.vacineja.service.vacina;

import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarVacinas {
    @Autowired
    private VacinaRepository vacinaRepository;

    public List<Vacina> executar() {
        return vacinaRepository.findAll();
    }
}
