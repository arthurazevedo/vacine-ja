package com.ufcg.psoft.vacineja.dtos;

import java.time.LocalDate;
import java.util.Set;

import com.ufcg.psoft.vacineja.utils.anotacoes.IsValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class CidadaoRequestDTO {
	@NotBlank(message = "Informe o email do cidadão.")
	@Size(min = 8, message = "Informe um email válido.")
	@NotNull(message = "Informe o seu email.")
	@IsValidEmail
    private String email;

	@NotBlank(message = "Informe a senha do cidadão.")
	@Size(min = 6, max = 15, message = "Informe uma senha que contenha de 6 a 15 caracteres.")
	@NotNull(message = "Informe sua senha.")
    private String senha;

	@NotBlank(message = "Informe o nome do cidadão.")
	@Size(min = 6, max = 50, message = "Informe um nome válido.")
	@NotNull(message = "Informe seu nome.")
	private String nome;

	@NotBlank(message = "Informe o cpf do cidadão.")
	@Size(min = 11, max = 14, message = "Informe um cpf válido.")
	@NotNull(message = "Informe o cpf.")
	private String cpf;

	@NotBlank(message = "Informe o endereço do cidadão.")
	@Size(min = 8, max = 50, message = "Informe um endereço válido.")
	@NotNull(message = "Informe o endereço.")
	private String endereco;

	@NotBlank(message = "Informe o nuúmero do cartão sus do cidadão.")
	@Size(min = 15, max = 15, message = "Informe um número de sus válido.")
	@NotNull(message = "Informe o número do sus.")
	private String sus;

	private String telefone;

	private String profissao;

	private Set<String> comorbidades;

	@NotNull(message = "Informe a data de nascimento.")
	private LocalDate nascimento;
}
