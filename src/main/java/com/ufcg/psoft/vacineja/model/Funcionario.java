package com.ufcg.psoft.vacineja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Funcionario extends Cidadao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PrimaryKeyJoinColumn(name="idCidadao")
    private Long idCidadao;
    private String cargo;
    private String localTrabalho;
    private boolean aprovado;
}
