package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;

@Getter
public class EstadoCidadaoResponseDTO {
	private String estado;

	public EstadoCidadaoResponseDTO(String estado) {
		this.estado = estado;
	}
}
