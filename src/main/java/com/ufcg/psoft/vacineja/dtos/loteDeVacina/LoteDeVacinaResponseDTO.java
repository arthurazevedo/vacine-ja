package com.ufcg.psoft.vacineja.dtos.loteDeVacina;

import com.ufcg.psoft.vacineja.dtos.vacina.VacinaDTO;
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
