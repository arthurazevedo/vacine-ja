package com.ufcg.psoft.vacineja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Funcionario extends Cidadao {
    @Id
    @GeneratedValue
    private Long id;

    @PrimaryKeyJoinColumn(name="idCidadao")
    @Column(unique = true)
    private Long idCidadao;
    private String cargo;
    private String localTrabalho;
    private boolean aprovado;
}
