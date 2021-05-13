package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistrosRequestDTO {
    private String cpf;
    private Long lote;
    private String tipoVacina;
    private int numeroDose;
    private Date data;
}
