package com.ufcg.psoft.vacineja.dtos;

import java.time.LocalDate;
import java.util.Set;

import com.ufcg.psoft.vacineja.utils.anotacoes.IsValidCpf;
import com.ufcg.psoft.vacineja.utils.anotacoes.IsValidEmail;
import com.ufcg.psoft.vacineja.utils.anotacoes.isValidPhoneNum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class CidadaoRequestDTO {
	
	@NotNull(message = "Informe o seu email.")
	@IsValidEmail
    private String email;

	@Size(min = 6, max = 15, message = "Informe uma senha que contenha de 6 a 15 caracteres.")
	@NotNull(message = "Informe sua senha.")
    private String senha;

	@NotBlank(message = "Informe seu nome.")
	@Size(min = 6, max = 50, message = "Informe um nome válido.")
	private String nome;

	@NotNull(message = "Informe o seu cpf.")
	@IsValidCpf
	private String cpf;

	@NotBlank(message = "Informe seu endereço.")
	@Size(min = 8, max = 50, message = "Informe um endereço válido.")
	private String endereco;

	@NotBlank(message = "Informe o seu número do cartão sus.")
	@Size(min = 15, max = 15, message = "Informe um número de sus válido.")
	private String sus;

	@NotNull(message = "Informe um número de telefone")
	@isValidPhoneNum
	private String telefone;

	@NotBlank(message = "Informe a profissão do cidadão (ou desempregado).")
	private String profissao;

	private Set<@NotBlank(message = "Informe comorbidades não nulas nem em branco") String> comorbidades;

	@NotNull(message = "Informe a data de nascimento.")
	@PastOrPresent(message = "Informe uma data no passado ou presente")
	private LocalDate nascimento;
}
