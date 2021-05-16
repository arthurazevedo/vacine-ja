package com.ufcg.psoft.vacineja.dtos;

import com.ufcg.psoft.vacineja.utils.anotacoes.IsValidCpf;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AprovarDTO {
    @IsValidCpf
    private String cpf;
}
