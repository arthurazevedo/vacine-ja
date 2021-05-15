package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.RegistrosRequestDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.LoteDeVacina;
import com.ufcg.psoft.vacineja.model.RegistroVacina;
import com.ufcg.psoft.vacineja.repository.RegistroVacinaRepository;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
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
    	
    	validacoes(registro);

        LoteDeVacina lote = loteDeVacinaService.removeUnidadesDoLote(registro.getLote());
        
        Cidadao cidadao = cidadaoService.vacinaCidadao(registro.getCpf(), lote.getVacina().getIntervaloEntreDoses(), lote.getVacina().isPrecisaSegundaDose());

        RegistroVacina registroData = new RegistroVacina(cidadao, registro.getData(), lote, lote.getVacina(),
                registro.getNumeroDose());

        registroVacinaRepository.save(registroData);

        return registroData;
    }

    private void validacoes(RegistrosRequestDTO registro) {
        if (registro.getCpf() == null) {
            throw new ValidacaoException(
                    new ErroDeSistema("CPF do Cidadao precisa ser informado.")
            );
        }

        if (registro.getLote() == null) {
            throw new ValidacaoException(
                    new ErroDeSistema("Lote precisa ser informado.")
            );
        }

        if (registro.getData() == null) {
            throw new ValidacaoException(
                    new ErroDeSistema("Data precisa ser informada.")
            );
        }

        if (registro.getNumeroDose() > 2 || registro.getNumeroDose() < 1) {
            throw new ValidacaoException(
                    new ErroDeSistema("Informe o numero da dose.")
            );
        }
    }

}
