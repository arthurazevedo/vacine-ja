package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadaoService {
    @Autowired
    private CidadaoRepository cidadaoRepository;

    public String pegarEstadoCidadao(String cpf) {
        Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByCpf(cpf);
        if(cidadaoOptional.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoExiste(cpf), HttpStatus.NOT_FOUND)
            );
        }

        return cidadaoOptional.get().exibeEstado();
    }

    public void salvarCidadao(Cidadao cidadao) {
        cidadaoRepository.save(cidadao);
    }
}
