package com.ufcg.psoft.vacineja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistroVacina {
    @Id
    private Long id;

    @OneToOne
    private Cidadao cidadao;

    private Date data;

    @OneToOne
    private LoteDeVacina loteDeVacina;

    @OneToOne
    private Vacina vacina;

    private String tipo_vacina;

    private int numero_dose; // 1 ou 2

}
