package com.ufcg.psoft.vacineja.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AgendamentoDTO {
	@NotNull(message = "Informe o horário do agendamento.")
	private Date horario;
}
