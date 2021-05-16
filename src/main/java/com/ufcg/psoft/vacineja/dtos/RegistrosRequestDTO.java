package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import com.ufcg.psoft.vacineja.utils.anotacoes.IsValidCpf;

import java.util.Date;

@Getter
@Setter
public class RegistrosRequestDTO {
	@NotNull(message = "Informe o cpf do cidadão.")
	@IsValidCpf
    private String cpf;

    @NotNull(message = "Informe o número do lote de vacinas.")
    private Long lote;

    @NotBlank(message = "Informe o tipo de vacina.")
    private String tipoVacina;

    @Min(message = "Informe qual a dose tomada pelo cidadão.", value = 1)
    @Max(message = "O valor da dose não pode maior que 2.", value = 2)
    private int numeroDose;

    @NotNull(message = "Informe a data de vacinação.")
    @PastOrPresent(message = "Data precisa estar no passado ou presente")
    private Date data;
}
