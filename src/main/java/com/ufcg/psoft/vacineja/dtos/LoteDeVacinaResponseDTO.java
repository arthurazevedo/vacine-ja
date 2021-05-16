package com.ufcg.psoft.vacineja.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoteDeVacinaResponseDTO {
    private Long id;
    private VacinaDTO vacina;
    private Long numDoses;
    private LocalDate dataDeValidade;
}
