package com.ufcg.psoft.vacineja.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoteDeVacinaCreateDTO {
    @NotNull(message = "Informe o fabricante da vacina.")
    @Size(min = 3, message = "Informe um fabricante válido.")
    private String fabricanteDaVacina;

    @NotNull(message = "Informe quantas doses são necessárias para completar a vacinação.")
    private Long numDoses;

    @NotNull(message = "Informe a data de validade da vacina.")
    private LocalDate dataDeValidade;
}
