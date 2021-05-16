package com.ufcg.psoft.vacineja.dtos;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import com.ufcg.psoft.vacineja.utils.anotacoes.IsNullOrNotBlank;
import com.ufcg.psoft.vacineja.utils.anotacoes.isValidPhoneNum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CidadaoUpdateDTO {
	@IsNullOrNotBlank(message = "Informe um nome que não esteja em branco")
	private String nome;
	@IsNullOrNotBlank(message = "Informe um endereço que não esteja em branco")
	private String endereco;
	@isValidPhoneNum
	private String telefone;
	@IsNullOrNotBlank(message = "Informe uma profissão que não esteja em branco")
	private String profissao;
	private Set<@NotBlank(message = "Informe comorbidades não nulas nem em branco") String> comorbidades;
}
