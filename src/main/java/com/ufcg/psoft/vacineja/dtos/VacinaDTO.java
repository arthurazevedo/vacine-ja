package com.ufcg.psoft.vacineja.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VacinaDTO {
    private String fabricante;
    private int dosesRequeridas;
    private int intervaloEntreDoses;
}
