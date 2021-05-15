package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class FuncionarioCadastroDTO {
    @NotBlank(message = "Informe o seu cargo como funcionário público.")
    @NotNull(message = "Informe o seu cargo como funcionário público.")
    @Size(min = 4)
    private String cargo;

    @NotBlank(message = "Informe o seu local de trabalho como funcionário público.")
    @NotNull(message = "Informe o seu local de trabalho como funcionário público.")
    @Size(min = 4)
    private String localTrabalho;

}
