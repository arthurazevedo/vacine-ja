package com.ufcg.psoft.vacineja.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AgendamentoDTO {
	@NotNull(message = "Informe o horário do agendamento.")
	@FutureOrPresent(message = "informe uma data no futuro ou presente")
	private Date horario;
}
