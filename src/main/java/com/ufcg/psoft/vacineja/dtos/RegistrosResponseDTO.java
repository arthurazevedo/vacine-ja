package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistrosResponseDTO {
    private CidadaoResponseDTO cidadao;
    private LoteDeVacinaResponseDTO lote;
    private Date data;
}
