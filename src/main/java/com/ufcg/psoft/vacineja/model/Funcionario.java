package com.ufcg.psoft.vacineja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="idCidadao")
@Getter
@Setter
@NoArgsConstructor
public class Funcionario extends Cidadao {

    private String cargo;
    private String localTrabalho;
    private boolean aprovado;
}
