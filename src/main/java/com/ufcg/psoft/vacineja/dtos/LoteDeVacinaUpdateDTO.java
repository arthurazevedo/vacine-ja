package com.ufcg.psoft.vacineja.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoteDeVacinaUpdateDTO {
    @NotNull(message = "Informe o id da vacina.")
    private Long vacinaId;

    private Long numDoses;

    private LocalDate dataDeValidade;
}
