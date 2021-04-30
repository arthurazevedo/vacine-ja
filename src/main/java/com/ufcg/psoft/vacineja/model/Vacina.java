package com.ufcg.psoft.vacineja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacinas")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fabricante;
    private int dosesRequeridas;
    private int intervaloEntreDoses;

    public Long getId() {
        return id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getDosesRequeridas() {
        return dosesRequeridas;
    }

    public void setDosesRequeridas(int dosesRequeridas) {
        this.dosesRequeridas = dosesRequeridas;
    }

    public int getIntervaloEntreDoses() {
        return intervaloEntreDoses;
    }

    public void setIntervaloEntreDoses(int intervaloEntreDoses) {
        this.intervaloEntreDoses = intervaloEntreDoses;
    }
}
