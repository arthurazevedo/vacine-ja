package com.ufcg.psoft.vacineja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistroVacina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Cidadao cidadao;

    private Date data;

    @OneToOne
    private LoteDeVacina loteDeVacina;

    @OneToOne
    private Vacina vacina;

    private int numeroDose; // 1 ou 2

    public RegistroVacina(Cidadao cidadao, Date data, LoteDeVacina loteDeVacina, Vacina vacina, int numeroDose) {
        this.cidadao = cidadao;
        this.data = data;
        this.loteDeVacina = loteDeVacina;
        this.vacina = vacina;
        this.numeroDose = numeroDose;
    }
}
