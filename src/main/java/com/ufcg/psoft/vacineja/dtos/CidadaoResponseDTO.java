package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadaoResponseDTO {
	private String email;
	private String nome;
	private String cpf;

	public CidadaoResponseDTO() {

	}
}
