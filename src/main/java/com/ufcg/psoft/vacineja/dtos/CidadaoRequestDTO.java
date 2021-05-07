package com.ufcg.psoft.vacineja.dtos;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CidadaoRequestDTO {
    private String email;
    private String senha;
	private String nome;
	private String cpf;
	private String endereco;
	private String sus;
	private String telefone;
	private String profissao;
	private Set<String> comorbidades;
	private LocalDate nascimento;
}
