package com.ufcg.psoft.vacineja.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CidadaoDTO {
    private String email;
    private String senha;
	private String nome;
	private String cpf;
	private String endereco;
	private String sus;
	private String telefone;
	private String profissao;
	private LocalDate nascimento;
	private List<String> comorbidades;
}
