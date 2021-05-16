package com.ufcg.psoft.vacineja.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;

import com.ufcg.psoft.vacineja.utils.anotacoes.IsNullOrNotBlank;

@Data
@NoArgsConstructor
public class LoteDeVacinaUpdateDTO {
	@IsNullOrNotBlank(message = "Informe um fabricante que não esteja em branco")
    private String fabricanteDaVacina;

	@Min(value = 0, message = "Informe uma quantidade válida de doses")
    private Long numDoses;

	@Future(message = "Informe uma data no futuro")
    private LocalDate dataDeValidade;
}
