package com.ufcg.psoft.vacineja.dtos;

import javax.validation.constraints.Min;

import com.ufcg.psoft.vacineja.utils.anotacoes.IsNullOrNotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinaAtualizaDTO {
	@IsNullOrNotBlank(message = "Informe um fabricante que não esteja em branco.")
    private String fabricante;

    private Boolean precisaSegundaDose;

    @Min(1)
    private Integer intervaloEntreDoses;
}