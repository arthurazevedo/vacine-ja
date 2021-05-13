package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CidadaoResponseDTO {
	private String email;
	private String nome;
	private String cpf;
}
