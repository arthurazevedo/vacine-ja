package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ufcg.psoft.vacineja.model.Cidadao;

public class Tomou1Dose implements Estado {
	
	private LocalDate dataDaVacina;
	private int diasEntreDoses;
	
	public Tomou1Dose(int diasEntreDoses) {
		this.dataDaVacina = LocalDate.now();
		this.diasEntreDoses = diasEntreDoses;
	}

	@Override
	public void atualiza(Cidadao cidadao, int idadeMinima) {
		if(ChronoUnit.DAYS.between(this.dataDaVacina, LocalDate.now()) >= diasEntreDoses) {
			cidadao.mudaEstado(new Habilitado2Dose());
		}
	}

	@Override
	public void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {}
	
	@Override
	public String toString() {
		return "Tomou 1ª dose";
	}

}
