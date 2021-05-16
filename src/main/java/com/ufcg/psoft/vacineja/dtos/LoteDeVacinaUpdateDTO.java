package com.ufcg.psoft.vacineja.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoteDeVacinaUpdateDTO {
    @NotNull(message = "Informe o fabricante da vacina")
    @Size(min = 3, message = "Informe um fabricante válido.")
    @NotBlank(message = "Informe o fabricante da vacina.")
    private String fabricanteDaVacina;

    private Long numDoses;

    private LocalDate dataDeValidade;
}
