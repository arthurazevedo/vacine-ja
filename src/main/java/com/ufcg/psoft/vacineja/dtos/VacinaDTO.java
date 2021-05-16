package com.ufcg.psoft.vacineja.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinaDTO {
    @NotNull(message = "Informe o fabricante da vacina.")
    @Size(min = 3, message = "Informe um fabricante válido.")
    private String fabricante;

    @NotNull(message = "Informe se a vacina precisa de segunda dose.")
    private Boolean precisaSegundaDose;

    @Min(0)
    private int intervaloEntreDoses;
}
