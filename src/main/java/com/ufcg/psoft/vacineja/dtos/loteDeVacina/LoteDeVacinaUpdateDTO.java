package com.ufcg.psoft.vacineja.dtos.loteDeVacina;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoteDeVacinaUpdateDTO {
    private Long vacinaId;
    private Long numDoses;
    private LocalDate dataDeValidade;
}
