package com.ufcg.psoft.vacineja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Funcionario extends Cidadao {

    @PrimaryKeyJoinColumn(name="idCidadao")
    private Long idCidadao;
    private String cargo;
    private String localTrabalho;
    private boolean aprovado;
}
