package com.ufcg.psoft.vacineja.dtos.vacina;

public class VacinaDTO {
    private String fabricante;
    private int dosesRequeridas;
    private int intervaloEntreDoses;

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
