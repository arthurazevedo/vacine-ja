package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.RegistrosRequestDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.LoteDeVacina;
import com.ufcg.psoft.vacineja.model.RegistroVacina;
import com.ufcg.psoft.vacineja.repository.RegistroVacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroVacinaService {

    @Autowired
    private RegistroVacinaRepository registroVacinaRepository;

    @Autowired
    private CidadaoService cidadaoService;

    @Autowired
    private LoteDeVacinaService loteDeVacinaService;

    public RegistroVacina cadastrar(RegistrosRequestDTO registro) {
    	
    	LoteDeVacina lote = loteDeVacinaService.getLoteById(registro.getLote());
        
        Cidadao cidadao = cidadaoService.vacinaCidadao(registro.getCpf(), lote.getVacina().getIntervaloEntreDoses(), lote.getVacina().isPrecisaSegundaDose());
        
        loteDeVacinaService.removeUnidadesDoLote(registro.getLote());

        RegistroVacina registroData = new RegistroVacina(cidadao, registro.getData(), lote, lote.getVacina(),
                registro.getNumeroDose());

        registroVacinaRepository.save(registroData);

        return registroData;
    }
}
