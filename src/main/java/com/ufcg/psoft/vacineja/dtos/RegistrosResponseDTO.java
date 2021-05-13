package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;

import java.util.Date;

@Getter
public class RegistrosResponseDTO {
    private CidadaoResponseDTO cidadao;
    private LoteDeVacinaResponseDTO lote;
    private VacinaDTO vacina;
    private Date data;
}
