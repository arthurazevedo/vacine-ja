package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.RegistrosRequestDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.LoteDeVacina;
import com.ufcg.psoft.vacineja.model.RegistroVacina;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.repository.LoteRepository;
import com.ufcg.psoft.vacineja.repository.RegistroVacinaRepository;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.ErroLote;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroVacinaService {

    @Autowired
    private RegistroVacinaRepository registroVacinaRepository;

    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Autowired
    private LoteRepository loteRepository;

    public RegistroVacina cadastrar(RegistrosRequestDTO registro) {
        if (registro.getCpf() == null) {
            throw new ValidacaoException(
                    new ErroDeSistema("CPF do Cidadao precisa ser informado.")
            );
        }

        Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByCpf(registro.getCpf());

        if (cidadaoOptional.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoExiste(registro.getCpf()), HttpStatus.NOT_FOUND)
            );
        }

        Cidadao cidadao = cidadaoOptional.get();

        if (registro.getLote() == null) {
            throw new ValidacaoException(
                    new ErroDeSistema("Lote precisa ser informado.")
            );
        }

        Optional<LoteDeVacina> loteOptional = loteRepository.findById(registro.getLote());

        if (loteOptional.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroLote.erroLoteNaoEcontrado(registro.getLote()), HttpStatus.NOT_FOUND)
            );
        }

        LoteDeVacina lote = loteOptional.get();

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

        RegistroVacina registroData = new RegistroVacina(cidadao, registro.getData(), lote, lote.getVacina(),
                registro.getNumeroDose());

        registroVacinaRepository.save(registroData);


        return registroData;
    }

}
