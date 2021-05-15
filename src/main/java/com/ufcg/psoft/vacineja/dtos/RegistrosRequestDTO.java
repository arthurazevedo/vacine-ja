package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class RegistrosRequestDTO {
    @NotBlank(message = "Informe o cpf do cidadão.")
    @Size(min = 11, max = 14, message = "Informe um cpf válido.")
    @NotNull(message = "Informe o cpf.")
    private String cpf;

    @NotNull(message = "Informe o número do lote de vacinas.")
    private Long lote;

    @NotNull(message = "Informe o tipo de vacina.")
    private String tipoVacina;

    @NotNull(message = "Informe qual a dose tomada pelo cidadão.")
    private int numeroDose;

    @NotNull(message = "Informe a data de vacinação.")
    private Date data;
}
