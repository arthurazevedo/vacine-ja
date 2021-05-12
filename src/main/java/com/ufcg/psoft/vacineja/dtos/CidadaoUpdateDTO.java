package com.ufcg.psoft.vacineja.dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CidadaoUpdateDTO {
	private String nome;
	private String endereco;
	private String telefone;
	private String profissao;
	private Set<String> comorbidades;
}
