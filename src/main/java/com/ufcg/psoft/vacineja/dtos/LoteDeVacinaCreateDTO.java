package com.ufcg.psoft.vacineja.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoteDeVacinaCreateDTO {
    @NonNull
    private Long vacinaId;
    @NonNull
    private Long numDoses;
    @NonNull
    private LocalDate dataDeValidade;
}
