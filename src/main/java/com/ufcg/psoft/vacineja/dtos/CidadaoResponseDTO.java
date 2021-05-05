package com.ufcg.psoft.vacineja.dtos;

import com.ufcg.psoft.vacineja.model.Usuario;

import lombok.Getter;

@Getter
public class CidadaoResponseDTO {
	private String email;
	private String nome;

	public CidadaoResponseDTO(Usuario usuario) {
		this.email = usuario.getEmail();
		this.nome = usuario.getTipo().getNome();
	}
}
