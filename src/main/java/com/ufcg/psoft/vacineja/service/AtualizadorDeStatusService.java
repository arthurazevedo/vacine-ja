package com.ufcg.psoft.vacineja.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @EnableScheduling
public class AtualizadorDeStatusService {
    private static final String TIME_ZONE = "America/Sao_Paulo";

    /**
     * Todos os dias de meia noite será chamado o método para atualizar o status de todos
     * os cidadãos.
     */
    @Scheduled(cron = "0 0 0 * * *", zone = TIME_ZONE)
    public void atualizaStatus() {
        //TODO: Pegar todos os cidadaos e chamar o método que atualiza o status dele
        System.out.println("O status dos cidadãos que podem ser vacinados foram atualizados!");
    }
}
