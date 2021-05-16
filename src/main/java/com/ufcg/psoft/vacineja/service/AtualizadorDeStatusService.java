package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @EnableScheduling
public class AtualizadorDeStatusService {
    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    CidadaoRepository cidadaoRepository;

    /**
     * Todos os dias de meia noite será chamado o método para atualizar o status de todos
     * os cidadãos.
     */
    @Scheduled(cron = "0 0 0 * * *", zone = TIME_ZONE)
    public void atualizaStatus() {
        List<Cidadao> cidadaos = cidadaoRepository.findAllCidadaoByEstadoNomeDoEstado("TomouPrimeiraDose");
        for(Cidadao cidadao: cidadaos) {
            cidadao.atualiza();
            cidadaoRepository.save(cidadao);
        }
    }
}
