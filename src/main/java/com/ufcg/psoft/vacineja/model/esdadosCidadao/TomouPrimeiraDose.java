package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;

import com.ufcg.psoft.vacineja.model.Cidadao;

@Entity
public class TomouPrimeiraDose extends Estado {
	
	private LocalDate dataDaVacina;
	private int diasEntreDoses;
	
	public TomouPrimeiraDose(int diasEntreDoses) {
		this.dataDaVacina = LocalDate.now();
		this.diasEntreDoses = diasEntreDoses;
	}

	@Override
	public void atualiza(Cidadao cidadao) {
		if(ChronoUnit.DAYS.between(this.dataDaVacina, LocalDate.now()) >= diasEntreDoses) {
			cidadao.mudaEstado(new HabilitadoSegundaDose());
		}
	}

	@Override
	public boolean vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		return false;
	}
	
	@Override
	public String toString() {
		return "Tomou 1ª dose";
	}

}
