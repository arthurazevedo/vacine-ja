package com.ufcg.psoft.vacineja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PerfilVacinacao {
    @Id
    private Long id;

    private int idade;

    private String comorbidade;

    private String profissão;

    private int quantidadeDoses;

}
