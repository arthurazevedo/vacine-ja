package com.ufcg.psoft.vacineja.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinaAtualizaDTO {

    private String fabricante;

    private boolean precisaSegundaDose;

    private int intervaloEntreDoses;
}